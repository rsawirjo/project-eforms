package com.onehippo.cms7.jaxrs.services;

import java.util.List;

/**
 * [eforms:fileupload] > eforms:field
 * - eforms:length (long) = "40" autocreated
 * - eforms:maxuploadsize (long) = "1" autocreated
 * - eforms:fileextensions = ""
 */

public class FileuploadBeanResponse extends AbstractFieldBeanResponse {
private Long maxUploadSize;
private Long length;
private List<String> fileExtensions;

    public FileuploadBeanResponse(final String fieldName, final boolean mandatory, final String hint, final String autocomplete,
                                  final boolean expandHint, final String label, final String validationRuleId,
                                  final String validationRuleLabel, final String validationRuleClass,
                                  final String validExpression, final boolean validCaseSensitive, final String fieldClass,
                                  final String extraCssClass, final boolean conditional, final boolean conditionNegate,
                                  final String conditionField, final String conditionValue,
                                  final Long maxUploadSize, final Long length,
                                  final List<String> fileExtensions, final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId, validationRuleLabel, validationRuleClass, validExpression, validCaseSensitive, fieldClass, extraCssClass, conditional, conditionNegate, conditionField, conditionValue, type);
        this.maxUploadSize = maxUploadSize;
        this.length = length;
        this.fileExtensions = fileExtensions;
    }

    public Long getMaxUploadSize() {
        return maxUploadSize;
    }

    public void setMaxUploadSize(final Long maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(final Long length) {
        this.length = length;
    }

    public List<String> getFileExtensions() {
        return fileExtensions;
    }

    public void setFileExtensions(final List<String> fileExtensions) {
        this.fileExtensions = fileExtensions;
    }
}
