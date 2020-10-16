package com.onehippo.cms7.jaxrs.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.cms7.eforms.hst.beans.FormConfigurationBean;
import com.onehippo.cms7.eforms.hst.beans.PageBean;

/**
 * [eforms:form] > hippostd:publishable, hippostd:publishableSummary, hippo:document orderable
 * + * (eforms:page) multiple
 * - eforms:notificationemail (string) = "test@example.com" autocreated
 * - eforms:action (string) = "mail"  autocreated < "mail","post"
 * - eforms:actionurl (string) = "http://test.com/action.do" autocreated
 * - eforms:formname (string)
 * // custom field class FQN
 * - eforms:formclass (string)
 * // to hold all the 'free defined properties' which can be used by the custom field classes.
 * - * (string)
 */
public class FormBeanResponse{
    private String action;
    private String actionUrl;
    private String email;
    private String ccEmail;
    private String bccEmail;
    private List<FormConfigurationBean> formConfigurationBeans;
    private List<PageBean> pageBeans;
    private String formName;
    private String formClass;
    private String buttonLabel;
    private String nextButtonLabel;
    private String previousButtonLabel;

    private static Logger log = LoggerFactory.getLogger(FormBeanResponse.class);

    public FormBeanResponse(final String action, final String actionUrl, final String email, final String ccEmail,
                            final String bccEmail, final List<FormConfigurationBean> formConfigurationBeans,
                            final List<PageBean> pageBeans, final String formName,
                            final String formClass, final String buttonLabel, final String nextButtonLabel,
                            final String previousButtonLabel) {
        this.action = action;
        this.actionUrl = actionUrl;
        this.email = email;
        this.ccEmail = ccEmail;
        this.bccEmail = bccEmail;
        this.formConfigurationBeans = formConfigurationBeans;
        this.pageBeans = pageBeans;
        this.formName = formName;
        this.formClass = formClass;
        this.buttonLabel = buttonLabel;
        this.nextButtonLabel = nextButtonLabel;
        this.previousButtonLabel = previousButtonLabel;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(final String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getCcEmail() {
        return ccEmail;
    }

    public void setCcEmail(final String ccEmail) {
        this.ccEmail = ccEmail;
    }

    public String getBccEmail() {
        return bccEmail;
    }

    public void setBccEmail(final String bccEmail) {
        this.bccEmail = bccEmail;
    }

    public List<FormConfigurationBean> getFormConfigurationBeans() {
        return formConfigurationBeans;
    }

    public void setFormConfigurationBeans(final List<FormConfigurationBean> formConfigurationBeans) {
        this.formConfigurationBeans = formConfigurationBeans;
    }

    public List<PageBean> getPageBeans() {
        return pageBeans;
    }

    public void setPageBeans(final List<PageBean> pageBeans) {
        this.pageBeans = pageBeans;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(final String formName) {
        this.formName = formName;
    }

    public String getFormClass() {
        return formClass;
    }

    public void setFormClass(final String formClass) {
        this.formClass = formClass;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(final String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public String getNextButtonLabel() {
        return nextButtonLabel;
    }

    public void setNextButtonLabel(final String nextButtonLabel) {
        this.nextButtonLabel = nextButtonLabel;
    }

    public String getPreviousButtonLabel() {
        return previousButtonLabel;
    }

    public void setPreviousButtonLabel(final String previousButtonLabel) {
        this.previousButtonLabel = previousButtonLabel;
    }
}
