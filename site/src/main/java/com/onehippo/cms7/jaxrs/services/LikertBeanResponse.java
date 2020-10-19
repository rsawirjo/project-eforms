package com.onehippo.cms7.jaxrs.services;

import java.util.List;

/**
 * [eforms:likert] > eforms:field
 * - eforms:options (string)  = "Option 1" multiple autocreated
 * - eforms:queries (string)  = "Question One"  multiple autocreated
 */
public class LikertBeanResponse extends AbstractFieldBeanResponse {
private List<String> options;
private List<String> questions;

    public LikertBeanResponse(final String fieldName, final boolean mandatory, final String hint, final String autocomplete,
                              final boolean expandHint, final String label, final String validationRuleId,
                              final String validationRuleLabel, final String validationRuleClass, final String validExpression,
                              final boolean validCaseSensitive, final String fieldClass, final String extraCssClass,
                              final boolean conditional, final boolean conditionNegate, final String conditionField,
                              final String conditionValue,
                              final List<String> options,
                              final List<String> questions, final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId,
                validationRuleLabel, validationRuleClass, validExpression, validCaseSensitive,
                fieldClass, extraCssClass, conditional, conditionNegate, conditionField, conditionValue, type);
        this.options = options;
        this.questions = questions;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(final List<String> options) {
        this.options = options;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(final List<String> questions) {
        this.questions = questions;
    }
}