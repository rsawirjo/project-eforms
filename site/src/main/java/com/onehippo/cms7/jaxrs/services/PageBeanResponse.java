package com.onehippo.cms7.jaxrs.services;

import com.onehippo.cms7.eforms.hst.beans.AbstractFieldBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 */
public class PageBeanResponse extends AbstractResponse{
    private static Logger log = LoggerFactory.getLogger(PageBeanResponse.class);
    public String label;
    public boolean conditional;
    public boolean conditionnegate;
    public String conditionfield;
    public String conditionvalue;
    public String validationruleid;
    public String validationrulelabel;
    public String validationruleclass;
    public List<AbstractFieldBean> fieldBeans;

    public PageBeanResponse(String label, boolean conditional, boolean conditionnegate, String conditionfield,
                            String conditionvalue, String validationruleid, String validationrulelabel,
                            String validationruleclass, List<AbstractFieldBean> fieldBeans) {
        this.label = label;
        this.conditional = conditional;
        this.conditionnegate = conditionnegate;
        this.conditionfield = conditionfield;
        this.conditionvalue = conditionvalue;
        this.validationruleid = validationruleid;
        this.validationrulelabel = validationrulelabel;
        this.validationruleclass = validationruleclass;
        this.fieldBeans = fieldBeans;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isConditional() {
        return conditional;
    }

    public void setConditional(boolean conditional) {
        this.conditional = conditional;
    }

    public boolean isConditionnegate() {
        return conditionnegate;
    }

    public void setConditionnegate(boolean conditionnegate) {
        this.conditionnegate = conditionnegate;
    }

    public String getConditionfield() {
        return conditionfield;
    }

    public void setConditionfield(String conditionfield) {
        this.conditionfield = conditionfield;
    }

    public String getConditionvalue() {
        return conditionvalue;
    }

    public void setConditionvalue(String conditionvalue) {
        this.conditionvalue = conditionvalue;
    }

    public String getValidationruleid() {
        return validationruleid;
    }

    public void setValidationruleid(String validationruleid) {
        this.validationruleid = validationruleid;
    }

    public String getValidationrulelabel() {
        return validationrulelabel;
    }

    public void setValidationrulelabel(String validationrulelabel) {
        this.validationrulelabel = validationrulelabel;
    }

    public String getValidationruleclass() {
        return validationruleclass;
    }

    public void setValidationruleclass(String validationruleclass) {
        this.validationruleclass = validationruleclass;
    }

    public List<AbstractFieldBean> getFieldBeans() {
        return fieldBeans;
    }

    public void setFieldBeans(List<AbstractFieldBean> fieldBeans) {
        this.fieldBeans = fieldBeans;
    }
}
