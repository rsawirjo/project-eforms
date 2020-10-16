package com.onehippo.cms7.jaxrs.services;

/**
 * * [eforms:textfield] > eforms:field
 * - eforms:length (long)
 */
public class TextFieldBeanResponse extends AbstractFieldBeanResponse {
    private Long length;
    private Long minLength;
    private Long maxLength;
    private String initialValue;

    public TextFieldBeanResponse(final String fieldName, final boolean mandatory, final String hint,
                                 final String autocomplete, final boolean expandHint, final String label,
                                 final String validationRuleId, final String validationRuleLabel,
                                 final String validationRuleClass, final String validExpression,
                                 final boolean validCaseSensitive, final String fieldClass, final String extraCssClass,
                                 final boolean conditional, final boolean conditionNegate, final String conditionField,
                                 final String conditionValue, final Long length, final Long minLength,
                                 final Long maxLength, final String initialValue, final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId,
                validationRuleLabel, validationRuleClass, validExpression, validCaseSensitive,
                fieldClass, extraCssClass, conditional, conditionNegate, conditionField, conditionValue, type);
        this.length = length;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.initialValue = initialValue;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(final Long length) {
        this.length = length;
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

    public String getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(final String initialValue) {
        this.initialValue = initialValue;
    }
}
