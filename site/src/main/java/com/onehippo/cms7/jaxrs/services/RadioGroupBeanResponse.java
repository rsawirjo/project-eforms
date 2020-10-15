package com.onehippo.cms7.jaxrs.services;

import java.util.List;

/**
 * [eforms:radiogroup] > eforms:field, eforms:condition
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
public class RadioGroupBeanResponse extends AbstractFieldBeanResponse {
    private List<String> values;
    private List<String> displayValues;
    private Long length;
    private Long minlength;
    private Long maxlength;
    private boolean allowother;

    public RadioGroupBeanResponse(final String fieldName, final boolean mandatory, final String hint,
                                  final String autocomplete, final boolean expandHint, final String label,
                                  final String validationRuleId, final String validationRuleLabel,
                                  final String validationRuleClass, final String validExpression,
                                  final boolean validCaseSensitive, final String fieldClass,
                                  final String extraCssClass, final boolean conditional, final boolean conditionNegate,
                                  final String conditionField, final String conditionValue, final List<String> values,
                                  final List<String> displayValues,
                                  final Long length, final Long minlength, final Long maxlength, final boolean allowother,
                                  final String type) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId, validationRuleLabel,
                validationRuleClass, validExpression, validCaseSensitive, fieldClass, extraCssClass, conditional,
                conditionNegate, conditionField, conditionValue ,type);
        this.values = values;
        this.displayValues = displayValues;
        this.length = length;
        this.minlength = minlength;
        this.maxlength = maxlength;
        this.allowother = allowother;
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

    public Long getMinlength() {
        return minlength;
    }

    public void setMinlength(final Long minlength) {
        this.minlength = minlength;
    }

    public Long getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(final Long maxlength) {
        this.maxlength = maxlength;
    }

    public boolean isAllowother() {
        return allowother;
    }

    public void setAllowother(final boolean allowother) {
        this.allowother = allowother;
    }
}
