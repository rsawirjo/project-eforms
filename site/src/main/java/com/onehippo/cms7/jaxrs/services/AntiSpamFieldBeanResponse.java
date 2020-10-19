package com.onehippo.cms7.jaxrs.services;

/**
 * [eforms:antispamfield] > eforms:field
 */
public class AntiSpamFieldBeanResponse extends AbstractFieldBeanResponse {
    private String antiSpamType;

    public AntiSpamFieldBeanResponse(final String fieldName, final boolean mandatory, final String hint,
                                     final String autocomplete, final boolean expandHint, final String label,
                                     final String validationRuleId, final String validationRuleLabel,
                                     final String validationRuleClass, final String validExpression,
                                     final boolean validCaseSensitive, final String fieldClass, final String extraCssClass,
                                     final boolean conditional, final boolean conditionNegate, final String conditionField,
                                     final String conditionValue, final String antiSpamType, final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId,
                validationRuleLabel, validationRuleClass, validExpression, validCaseSensitive,
                fieldClass, extraCssClass, conditional, conditionNegate, conditionField, conditionValue, type);
        this.antiSpamType = antiSpamType;
    }

    public String getAntiSpamType() {
        return antiSpamType;
    }

    public void setAntiSpamType(final String antiSpamType) {
        this.antiSpamType = antiSpamType;
    }
}
