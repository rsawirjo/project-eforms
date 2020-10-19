package com.onehippo.cms7.jaxrs.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.cms7.eforms.hst.beans.AbstractFieldBean;

public class FieldGroupBeanResponse extends AbstractFieldBeanResponse{

    private static Logger log = LoggerFactory.getLogger(FieldGroupBeanResponse.class);
    private String groupName;
    private String fieldNamePrefix;
    private boolean oneLine;
    private List<AbstractFieldBean> fieldBeans;

    public FieldGroupBeanResponse(final String fieldName, final boolean mandatory, final String hint,
                                  final String autocomplete, final boolean expandHint, final String label,
                                  final String validationRuleId, final String validationRuleLabel,
                                  final String validationRuleClass, final String validExpression,
                                  final boolean validCaseSensitive, final String fieldClass,
                                  final String extraCssClass, final boolean conditional,
                                  final boolean conditionNegate, final String conditionField,
                                  final String conditionValue, final String type,
                                  final String groupName, final String fieldNamePrefix,
                                  final boolean oneLine, final List<AbstractFieldBean> fieldBeans) {
        super(fieldName, mandatory, hint, autocomplete, expandHint, label, validationRuleId, validationRuleLabel,
                validationRuleClass, validExpression, validCaseSensitive, fieldClass, extraCssClass, conditional,
                conditionNegate, conditionField, conditionValue, type);
        this.groupName = groupName;
        this.fieldNamePrefix = fieldNamePrefix;
        this.oneLine = oneLine;
        this.fieldBeans = fieldBeans;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    public String getFieldNamePrefix() {
        return fieldNamePrefix;
    }

    public void setFieldNamePrefix(final String fieldNamePrefix) {
        this.fieldNamePrefix = fieldNamePrefix;
    }

    public boolean isOneLine() {
        return oneLine;
    }

    public void setOneLine(final boolean oneLine) {
        this.oneLine = oneLine;
    }

    public List<AbstractFieldBean> getFieldBeans() {
        return fieldBeans;
    }

    public void setFieldBeans(final List<AbstractFieldBean> fieldBeans) {
        this.fieldBeans = fieldBeans;
    }
}
