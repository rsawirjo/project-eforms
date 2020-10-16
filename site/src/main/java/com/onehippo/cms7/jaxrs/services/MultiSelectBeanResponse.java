/*
 * Copyright 2010-2019 Hippo B.V. (http://www.onehippo.com)
 */

package com.onehippo.cms7.jaxrs.services;

import com.onehippo.cms7.eforms.hst.beans.SelectType;

/**
 * [eforms:multiselect] > eforms:field
 * - eforms:type (string)  = "checkboxes" autocreated < "checkboxes","multiselect"
 * - eforms:value (string) multiple
 * - eforms:text (string) multiple
 */
public class MultiSelectBeanResponse extends AbstractFieldBeanResponse {
    private SelectType selectType;
    private String value;
    private String text;

    public MultiSelectBeanResponse(final String fieldName, final boolean mandatory, final String hint,
                                   final String autocomplete, final boolean expandHint, final String label,
                                   final String validationRuleId, final String validationRuleLabel,
                                   final String validationRuleClass, final String validExpression,
                                   final boolean validCaseSensitive, final String fieldClass,
                                   final String extraCssClass, final boolean conditional,
                                   final boolean conditionNegate, final String conditionField,
                                   final String conditionValue, final SelectType selectType,
                                   final String value, final String text , final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId, validationRuleLabel, validationRuleClass, validExpression, validCaseSensitive, fieldClass, extraCssClass, conditional, conditionNegate, conditionField, conditionValue, type);
        this.selectType = selectType;
        this.value = value;
        this.text = text;
    }

    public SelectType getSelectType() {
        return selectType;
    }

    public void setSelectType(final SelectType selectType) {
        this.selectType = selectType;
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