/*
 * Copyright 2012-2020 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.eforms.demo.behavior;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.component.support.forms.FormMap;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.ObjectBeanPersistenceException;
import org.hippoecm.hst.content.beans.manager.workflow.BaseWorkflowCallbackHandler;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.ComponentConfiguration;
import org.onehippo.repository.documentworkflow.DocumentWorkflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.cms7.eforms.demo.beans.SbiCodeMap;
import com.onehippo.cms7.eforms.demo.beans.SbiCodeRow;
import com.onehippo.cms7.eforms.demo.util.ContextHelper;
import com.onehippo.cms7.eforms.hst.beans.FormBean;
import com.onehippo.cms7.eforms.hst.behaviors.StoreFormDataBehavior;
import com.onehippo.cms7.eforms.hst.model.Form;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * This behavior stores a csv file which has been uploaded into tmp folder, from than on it is converted into
 * a jcr node structure.
 */
public class SbiCodeDataBehavior extends StoreFormDataBehavior {
    private static final String JAVA_IO_TMP_DIR = "java.io.tmpdir";
    private static final String EFORMS_TMP_DIR = "eforms.tmp.dir";

    private static final Logger log = LoggerFactory.getLogger(SbiCodeDataBehavior.class);

    @Override
    public void onValidationSuccess(HstRequest request, HstResponse response, ComponentConfiguration config,
                                    FormBean bean, Form form, FormMap formMap) {
        processUploads(request, config);
    }

    /**
     * Process uploaded files. Processing is saves uploads to disk or attaching them to a mail depending on this
     * component's configuration.
     *
     * @param request the current hst request
     * @param config  component configuration
     */
    protected void processUploads(final HstRequest request, ComponentConfiguration config) {
        @SuppressWarnings("unchecked")
        Map<String, byte[]> files = (Map<String, byte[]>) request.getAttribute("files");
        if (files != null) {
            for (Map.Entry<String, byte[]> upload : files.entrySet()) {
                if (!StringUtils.isBlank(upload.getKey())) {
                    processUploadedFile(request, config, upload.getKey(), upload.getValue());
                }
            }
        }
    }

    /**
     * Process an uploaded file. Store it on the filesystem.
     *
     * @param request  the current hst request
     * @param filename the uploaded file's filename
     * @param data     a byte array containing the data
     */
    protected void processUploadedFile(final HstRequest request, ComponentConfiguration config, final String filename, final byte[] data) {
        // prevent directory traversal
        if (isInvalidFileName(filename)) {
            return;
        }
        StringBuilder basePath = new StringBuilder(getTempFolder());
        basePath.append('/').append(request.getSession().getId());
        String baseTempFolder = basePath.toString();
        basePath.append('/');
        storeTemporaryFile(data, filename, basePath);
        readTempStoredFile(basePath.toString(), filename);
        deleteTempFolder(baseTempFolder);
    }

    protected String getTempFolder() {
        log.debug("Retrieving temp folder from system property {} '{}', else {} '{}'",
                EFORMS_TMP_DIR, System.getProperty(EFORMS_TMP_DIR), JAVA_IO_TMP_DIR, System.getProperty(JAVA_IO_TMP_DIR));
        return StringUtils.isNotEmpty(System.getProperty(EFORMS_TMP_DIR)) ? System.getProperty(EFORMS_TMP_DIR) :
                StringUtils.isNotEmpty(System.getProperty(JAVA_IO_TMP_DIR)) ? System.getProperty(JAVA_IO_TMP_DIR) : null;
    }

    private boolean isInvalidFileName(final String name) {
        final String fileName = FilenameUtils.normalize(name);
        return fileName.isEmpty() || fileName.startsWith(".") || fileName.contains("..") ||
                fileName.contains("/") || fileName.contains("\\") || fileName.contains("$") && fileName.endsWith(".csv");
    }

    /**
     * Store a FileItemStream on the filesystem.
     *
     * @param data     the bytes to save to the filesystem
     * @param filename the file already sanitized filename
     * @param basePath where to save the file
     * @return whether the file was successfully stored on the filesystem.
     */
    private boolean storeTemporaryFile(final byte[] data, final String filename, final StringBuilder basePath) {
        FileOutputStream file = null;
        try {
            new File(basePath.toString()).mkdirs();
            file = new FileOutputStream(basePath.append(filename).toString());
            file.write(data);
            return true;
        } catch (IOException e) {
            log.error("Error saving file {}", filename, e);
        } finally {
            IOUtils.closeQuietly(file);
        }
        return false;
    }

    private void deleteTempFolder(final String folderName) {
        try {
            FileUtils.deleteDirectory(new File(folderName));
        } catch (IOException e) {
            log.debug(String.format("%s Failed to delete the temp folder", folderName));
        }
    }

    private void readTempStoredFile(final String locationFile, final String filename) {
        try {
            List<SbiCodeRow> sbiCodeRows = new CsvToBeanBuilder(new FileReader(locationFile))
                    .withType(SbiCodeRow.class).build().parse();
            SbiCodeMap sbiCodeMap = new SbiCodeMap();
            sbiCodeMap.setFilename(filename);
            sbiCodeMap.setSbiCodeRows(sbiCodeRows);
            Session session;
            WorkflowPersistenceManager wpm = null;
            try {
                log.info("****** Start to process a document *******");
                ContextHelper contextHelper = new ContextHelper();
                session = contextHelper.getPersistableSession();
                wpm = contextHelper.getWorkflowPersistenceManager(session);
                wpm.setWorkflowCallbackHandler(
                        new BaseWorkflowCallbackHandler<DocumentWorkflow>() {
                            public void processWorkflow(DocumentWorkflow wf) throws Exception {
                                boolean publish = false;
                                for (java.util.Map.Entry<String, Serializable> hint : wf.hints().entrySet()) {
                                    if ("publish".equals(hint.getKey()) && Boolean.valueOf(hint.getValue().toString())) {
                                        publish = true;
                                    }
                                }
                                if (publish) {
                                    wf.publish();
                                }
                            }
                        });
                writeSbiCodeMapToRepo(sbiCodeMap, wpm, filename);
            } catch (Exception e) {
                log.error("Failed to persist document: {}", e.getMessage(), e);
                if (wpm != null) {
                    try {
                        wpm.refresh();
                    } catch (ObjectBeanPersistenceException e1) {
                        log.warn("Failed to refresh: ", e1);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to read the temp file while coverting csv to jcr node. {}", e.getMessage());
        }
    }

    private SbiCodeMap writeSbiCodeMapToRepo(final SbiCodeMap sbiCodeMap, final WorkflowPersistenceManager wpm,
                                             final String fileName) throws ObjectBeanManagerException {
        final String SbiCodeMapDocumentPath = wpm.createAndReturn("/content/documents/hippoaddoneformsdemo/sbicode-files",
                "hippoaddoneformsdemo:sbicodemap", fileName, true);
        final SbiCodeMap newSbiCodeMap = (SbiCodeMap) wpm.getObject(SbiCodeMapDocumentPath);
        if (newSbiCodeMap == null) {
            throw new HstComponentException("Failed to add SbiCodeMap");
        }
        newSbiCodeMap.setSbiCodeRows(sbiCodeMap.getSbiCodeRowList());
        newSbiCodeMap.setFilename(sbiCodeMap.getFilename());
        wpm.update(newSbiCodeMap);
        return newSbiCodeMap;
    }
}
