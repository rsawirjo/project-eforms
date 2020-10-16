package com.onehippo.cms7.jaxrs.services;

import org.hippoecm.hst.content.beans.standard.HippoDocument;

public abstract class AbstractFieldBeanResponse extends HippoDocument {
    private String fieldName;
    private boolean mandatory;
    private String hint;
    private String autocomplete;
    private boolean expandHint;
    private String label;
    private String validationRuleId;
    private String validationRuleLabel;
    private String validationRuleClass;
    private String validExpression;
    private boolean validCaseSensitive;
    private String fieldClass;
    private String extraCssClass;
    private boolean conditional;
    private boolean conditionNegate;
    private String conditionField;
    private String conditionValue;
    private String type;


    public AbstractFieldBeanResponse(final String fieldName, final boolean mandatory,
                                     final String hint, final String autocomplete,
                                     final boolean expandHint, final String label,
                                     final String validationRuleId, final String validationRuleLabel,
                                     final String validationRuleClass, final String validExpression,
                                     final boolean validCaseSensitive, final String fieldClass, final String extraCssClass,
                                     final boolean conditional, final boolean conditionNegate,
                                     final String conditionField, final String conditionValue,
                                     final String type) {
        this.fieldName = fieldName;
        this.mandatory = mandatory;
        this.hint = hint;
        this.autocomplete = autocomplete;
        this.expandHint = expandHint;
        this.label = label;
        this.validationRuleId = validationRuleId;
        this.validationRuleLabel = validationRuleLabel;
        this.validationRuleClass = validationRuleClass;
        this.validExpression = validExpression;
        this.validCaseSensitive = validCaseSensitive;
        this.fieldClass = fieldClass;
        this.extraCssClass = extraCssClass;
        this.conditional = conditional;
        this.conditionNegate = conditionNegate;
        this.conditionField = conditionField;
        this.conditionValue = conditionValue;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(final boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(final String hint) {
        this.hint = hint;
    }

    public String getAutocomplete() {
        return autocomplete;
    }

    public void setAutocomplete(final String autocomplete) {
        this.autocomplete = autocomplete;
    }

    public boolean isExpandHint() {
        return expandHint;
    }

    public void setExpandHint(final boolean expandHint) {
        this.expandHint = expandHint;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public String getValidationRuleId() {
        return validationRuleId;
    }

    public void setValidationRuleId(final String validationRuleId) {
        this.validationRuleId = validationRuleId;
    }

    public String getValidationRuleLabel() {
        return validationRuleLabel;
    }

    public void setValidationRuleLabel(final String validationRuleLabel) {
        this.validationRuleLabel = validationRuleLabel;
    }

    public String getValidationRuleClass() {
        return validationRuleClass;
    }

    public void setValidationRuleClass(final String validationRuleClass) {
        this.validationRuleClass = validationRuleClass;
    }

    public String getValidExpression() {
        return validExpression;
    }

    public void setValidExpression(final String validExpression) {
        this.validExpression = validExpression;
    }

    public boolean isValidCaseSensitive() {
        return validCaseSensitive;
    }

    public void setValidCaseSensitive(final boolean validCaseSensitive) {
        this.validCaseSensitive = validCaseSensitive;
    }

    public String getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(final String fieldClass) {
        this.fieldClass = fieldClass;
    }

    public String getExtraCssClass() {
        return extraCssClass;
    }

    public void setExtraCssClass(final String extraCssClass) {
        this.extraCssClass = extraCssClass;
    }

    public boolean isConditional() {
        return conditional;
    }

    public void setConditional(final boolean conditional) {
        this.conditional = conditional;
    }

    public boolean isConditionNegate() {
        return conditionNegate;
    }

    public void setConditionNegate(final boolean conditionNegate) {
        this.conditionNegate = conditionNegate;
    }

    public String getConditionField() {
        return conditionField;
    }

    public void setConditionField(final String conditionField) {
        this.conditionField = conditionField;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(final String conditionValue) {
        this.conditionValue = conditionValue;
    }
}
