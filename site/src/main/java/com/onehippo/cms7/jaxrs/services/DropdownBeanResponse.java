package com.onehippo.cms7.jaxrs.services;

import java.util.List;

/**
 * [eforms:dropdown] > eforms:field
 * - eforms:values (string) = "Choice 1" multiple autocreated
 */
public class DropdownBeanResponse extends AbstractFieldBeanResponse {
    private List<String> values;
    private List<String> displayValues;
    private String initialOption;
    private String initialValue;
    private String initialDisplayText;

    public DropdownBeanResponse(final String fieldName, final boolean mandatory, final String hint, final String autocomplete,
                                final boolean expandHint, final String label, final String validationRuleId,
                                final String validationRuleLabel, final String validationRuleClass,
                                final String validExpression, final boolean validCaseSensitive, final String fieldClass,
                                final String extraCssClass, final boolean conditional, final boolean conditionNegate,
                                final String conditionField, final String conditionValue, final List<String> values,
                                final List<String> displayValues,
                                final String initialOption, final String initialValue, final String initialDisplayText,
                                final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId, validationRuleLabel,
                validationRuleClass, validExpression, validCaseSensitive, fieldClass, extraCssClass, conditional,
                conditionNegate, conditionField, conditionValue, type);
        this.values = values;
        this.displayValues = displayValues;
        this.initialOption = initialOption;
        this.initialValue = initialValue;
        this.initialDisplayText = initialDisplayText;
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

    public String getInitialOption() {
        return initialOption;
    }

    public void setInitialOption(final String initialOption) {
        this.initialOption = initialOption;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(final String initialValue) {
        this.initialValue = initialValue;
    }

    public String getInitialDisplayText() {
        return initialDisplayText;
    }

    public void setInitialDisplayText(final String initialDisplayText) {
        this.initialDisplayText = initialDisplayText;
    }
}