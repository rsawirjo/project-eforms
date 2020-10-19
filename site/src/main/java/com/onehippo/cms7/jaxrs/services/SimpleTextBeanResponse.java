package com.onehippo.cms7.jaxrs.services;

/**
 * [eforms:simpletext] > eforms:field
 */
public class SimpleTextBeanResponse extends AbstractFieldBeanResponse {
    public SimpleTextBeanResponse(final String fieldName, final boolean mandatory, final String hint,
                                  final String autocomplete, final boolean expandHint, final String label,
                                  final String validationRuleId, final String validationRuleLabel,
                                  final String validationRuleClass, final String validExpression,
                                  final boolean validCaseSensitive, final String fieldClass,
                                  final String extraCssClass, final boolean conditional,
                                  final boolean conditionNegate, final String conditionField,
                                  final String conditionValue, final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId, validationRuleLabel,
                validationRuleClass, validExpression, validCaseSensitive, fieldClass, extraCssClass, conditional,
                conditionNegate, conditionField, conditionValue, type);
    }
}
