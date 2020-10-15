package com.onehippo.cms7.jaxrs.services;

/**
 * [eforms:textarea] > eforms:field
 * - eforms:rows (long) = "20" autocreated
 * - eforms:cols (long) = "40" autocreated
 */

public class TextAreaBeanResponse extends AbstractFieldBeanResponse {
    private Long rows;
    private Long cols;
    private Long minLength;
    private Long maxLength;

    public TextAreaBeanResponse(final String fieldName, final boolean mandatory, final String hint,
                                final String autocomplete, final boolean expandHint, final String label,
                                final String validationRuleId, final String validationRuleLabel,
                                final String validationRuleClass, final String validExpression,
                                final boolean validCaseSensitive, final String fieldClass, final String extraCssClass,
                                final boolean conditional, final boolean conditionNegate, final String conditionField,
                                final String conditionValue, final Long rows, final Long cols, final Long minLength,
                                final Long maxLength, final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId,
                validationRuleLabel, validationRuleClass, validExpression, validCaseSensitive,
                fieldClass, extraCssClass, conditional, conditionNegate, conditionField, conditionValue, type);
        this.rows = rows;
        this.cols = cols;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(final Long rows) {
        this.rows = rows;
    }

    public Long getCols() {
        return cols;
    }

    public void setCols(final Long cols) {
        this.cols = cols;
    }

    public Long getMinLength() {
        return minLength;
    }

    public void setMinLength(final Long minLength) {
        this.minLength = minLength;
    }

    public Long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(final Long maxLength) {
        this.maxLength = maxLength;
    }
}
