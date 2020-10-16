package com.onehippo.cms7.jaxrs.services;

/**
 * [eforms:singleselect] > eforms:field
 * - eforms:type (string) = "radiogroup" autocreated < "radiogroup","select"
 * - eforms:value (string) multiple
 * - eforms:text (string) multiple
 */
public class SingleSelectBeanResponse extends AbstractFieldBeanResponse {
    private String value;
    private String text;

    public SingleSelectBeanResponse(final String fieldName, final boolean mandatory, final String hint,
                                    final String autocomplete, final boolean expandHint, final String label,
                                    final String validationRuleId, final String validationRuleLabel,
                                    final String validationRuleClass, final String validExpression,
                                    final boolean validCaseSensitive, final String fieldClass,
                                    final String extraCssClass, final boolean conditional, final boolean conditionNegate,
                                    final String conditionField, final String conditionValue,
                                    final String value, final String text, final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId, validationRuleLabel,
                validationRuleClass, validExpression, validCaseSensitive, fieldClass, extraCssClass, conditional,
                conditionNegate, conditionField, conditionValue, type);
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}