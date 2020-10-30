package com.onehippo.cms7.jaxrs.services;

import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.repository.util.JcrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

public abstract class AbstractResponse {
    private static Logger log = LoggerFactory.getLogger(AbstractResponse.class);
    private String fieldExtensions;

    public String determineFieldExtensions(final HippoDocument hippoDocument) {
        try {
            Node node = hippoDocument.getNode();
            return JcrUtils.getStringProperty(node, "first", "");
        } catch (RepositoryException e) {
            log.error("Fail to get fieldextensions." +  e.getMessage());
        }
        return "";
    }

    public String getFieldExtensions() {
        return fieldExtensions;
    }

    public void setFieldExtensions(String fieldExtensions) {
        this.fieldExtensions = fieldExtensions;
    }
}
