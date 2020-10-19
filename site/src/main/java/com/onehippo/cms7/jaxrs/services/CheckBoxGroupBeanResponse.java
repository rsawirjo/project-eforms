/*
 * Copyright 2010-2019 Hippo B.V. (http://www.onehippo.com)
 */

package com.onehippo.cms7.jaxrs.services;

import java.util.List;

/**
 * [eforms:checkboxes] > eforms:field
 * - eforms:values (string) = "Choice 1" multiple autocreated
 * - eforms:displayvalues (string) = "Choice 1" multiple autocreated
 * - eforms:allowother (boolean) = "false" autocreated
 * - eforms:length (long) = "40" autocreated
 * - eforms:minlength (long)
 * - eforms:maxlength (long)
 * - eforms:validexpression (string)
 */

/**
 * @version $Id$
 */
public class CheckBoxGroupBeanResponse extends AbstractFieldBeanResponse {
private List<String> values;
private List<String> displayValues;
private Long length;
private Long minLength;
private Long maxLength;
private boolean allowOther;

    public CheckBoxGroupBeanResponse(final String fieldName, final boolean mandatory, final String hint,
                                     final String autocomplete, final boolean expandHint, final String label,
                                     final String validationRuleId, final String validationRuleLabel,
                                     final String validationRuleClass, final String validExpression,
                                     final boolean validCaseSensitive, final String fieldClass,
                                     final String extraCssClass, final boolean conditional,
                                     final boolean conditionNegate, final String conditionField,
                                     final String conditionValue, final List<String> values,
                                     final List<String> displayValues, final Long length, final Long minLength,
                                     final Long maxLength, final boolean allowOther, final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId,
                validationRuleLabel, validationRuleClass, validExpression, validCaseSensitive, fieldClass,
                extraCssClass, conditional, conditionNegate, conditionField, conditionValue, type);
        this.values = values;
        this.displayValues = displayValues;
        this.length = length;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.allowOther = allowOther;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(final List<String> values) {
        this.values = values;
    }

    public List<String> getDisplayValues() {
        return displayValues;
    }

    public void setDisplayValues(final List<String> displayValues) {
        this.displayValues = displayValues;
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

    public boolean isAllowOther() {
        return allowOther;
    }

    public void setAllowOther(final boolean allowOther) {
        this.allowOther = allowOther;
    }
}