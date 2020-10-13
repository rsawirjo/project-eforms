/*
 * Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.eforms.demo.beans;

import java.util.Calendar;
import java.util.List;

import javax.jcr.RepositoryException;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.ContentNodeBinder;
import org.hippoecm.hst.content.beans.ContentNodeBindingException;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.onehippo.repository.util.JcrConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Node(jcrType = "hippoaddoneformsdemo:sbicodemap")
public class SbiCodeMap extends HippoDocument implements ContentNodeBinder {
    private static final Logger log = LoggerFactory.getLogger(SbiCodeMap.class);

    private List<SbiCodeRow> sbiCodeRows;
    private String filename;

    public void setSbiCodeRows(final List<SbiCodeRow> sbiCodeRows) {
        this.sbiCodeRows = sbiCodeRows;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public List<SbiCodeRow> getSbiCodeRowList() {
        return (sbiCodeRows.isEmpty() || sbiCodeRows == null) ? getLinkedBeans("hippoaddoneformsdemo:sbicoderow", SbiCodeRow.class) : sbiCodeRows;
    }

    public String getFilename() {
        return StringUtils.isEmpty(filename) ? getSingleProperty("hippoaddoneformsdemo:filename", "") : filename;
    }

    @Override
    public boolean bind(final Object content, final javax.jcr.Node node) throws ContentNodeBindingException {
        if (content instanceof SbiCodeMap) {
            try {
                SbiCodeMap sbiCodeMap = (SbiCodeMap) content;

                node.setPrimaryType("hippoaddoneformsdemo:sbicodemap");

                List<SbiCodeRow> sbiCodeRows = sbiCodeMap.getSbiCodeRowList();
                for (SbiCodeRow sbiCodeRow : sbiCodeRows) {
                    javax.jcr.Node newNode = node.addNode("hippoaddoneformsdemo:sbicoderow", "hippoaddoneformsdemo:sbicoderow");
                    sbiCodeRow.bind(sbiCodeRow, newNode);
                }
                node.setProperty(JcrConstants.JCR_LAST_MODIFIED, Calendar.getInstance());
                return true;
            } catch (RepositoryException e) {
                throw new ContentNodeBindingException();
            }
        }
        return false;
    }
}
