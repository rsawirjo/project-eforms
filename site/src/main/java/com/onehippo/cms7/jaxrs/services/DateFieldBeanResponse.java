package com.onehippo.cms7.jaxrs.services;

import java.util.Calendar;

/**
 * * [eforms:datefield] > eforms:field
 */
public class DateFieldBeanResponse extends AbstractFieldBeanResponse {
private Long length;
private String dateFormat;
private Boolean initialValueSet;
private Boolean initialValueDayOffsetMode;
private Calendar initialValue;
private Long initialValueDayOffset;
private Boolean initialValueRuleMode;
private String initialValueRule;

    public DateFieldBeanResponse(final String fieldName, final boolean mandatory, final String hint, final String autocomplete,
                                 final boolean expandHint, final String label, final String validationRuleId,
                                 final String validationRuleLabel, final String validationRuleClass,
                                 final String validExpression, final boolean validCaseSensitive, final String fieldClass,
                                 final String extraCssClass, final boolean conditional, final boolean conditionNegate,
                                 final String conditionField, final String conditionValue,
                                 final Long length, final String dateFormat, final Boolean initialValueSet,
                                 final Boolean initialValueDayOffsetMode, final Calendar initialValue,
                                 final Long initialValueDayOffset, final Boolean initialValueRuleMode,
                                 final String initialValueRule, final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label,
                validationRuleId, validationRuleLabel, validationRuleClass, validExpression,
                validCaseSensitive, fieldClass, extraCssClass, conditional, conditionNegate,
                conditionField, conditionValue, type);
        this.length = length;
        this.dateFormat = dateFormat;
        this.initialValueSet = initialValueSet;
        this.initialValueDayOffsetMode = initialValueDayOffsetMode;
        this.initialValue = initialValue;
        this.initialValueDayOffset = initialValueDayOffset;
        this.initialValueRuleMode = initialValueRuleMode;
        this.initialValueRule = initialValueRule;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(final Long length) {
        this.length = length;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(final String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Boolean getInitialValueSet() {
        return initialValueSet;
    }

    public void setInitialValueSet(final Boolean initialValueSet) {
        this.initialValueSet = initialValueSet;
    }

    public Boolean getInitialValueDayOffsetMode() {
        return initialValueDayOffsetMode;
    }

    public void setInitialValueDayOffsetMode(final Boolean initialValueDayOffsetMode) {
        this.initialValueDayOffsetMode = initialValueDayOffsetMode;
    }

    public Calendar getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(final Calendar initialValue) {
        this.initialValue = initialValue;
    }

    public Long getInitialValueDayOffset() {
        return initialValueDayOffset;
    }

    public void setInitialValueDayOffset(final Long initialValueDayOffset) {
        this.initialValueDayOffset = initialValueDayOffset;
    }

    public Boolean getInitialValueRuleMode() {
        return initialValueRuleMode;
    }

    public void setInitialValueRuleMode(final Boolean initialValueRuleMode) {
        this.initialValueRuleMode = initialValueRuleMode;
    }

    public String getInitialValueRule() {
        return initialValueRule;
    }

    public void setInitialValueRule(final String initialValueRule) {
        this.initialValueRule = initialValueRule;
    }
}
