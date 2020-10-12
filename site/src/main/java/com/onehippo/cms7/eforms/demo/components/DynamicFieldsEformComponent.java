/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.eforms.demo.components;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.core.component.HstRequest;

import com.onehippo.cms7.eforms.hst.beans.FormBean;
import com.onehippo.cms7.eforms.hst.components.FormStoringEformComponent;
import com.onehippo.cms7.eforms.hst.model.Form;
import com.onehippo.cms7.eforms.hst.model.FormFieldFactory;
import com.onehippo.cms7.eforms.hst.model.TextField;
import com.onehippo.cms7.eforms.hst.model.ctx.FormContext;

/**
 * DynamicFieldsEformComponent to demonstrate how to add an extra field at runtime even if there's no field definition
 * in the associated form document.
 * 
 * <P>
 * An example hst component which adds a runtime field ('State') when the URL has a query parameter, "region=US".
 * </P>
 */
public class DynamicFieldsEformComponent extends FormStoringEformComponent {

    @Override
    protected Form parse(final FormBean bean, final FormContext formContext, final HstRequest request) {
        Form form = FormFieldFactory.createForm(bean, formContext, null, request);

        if (isStateFieldNecessary(request)) {
            final TextField stateField = createStateTextField(form);

            // First, register the field in the form so that the form becomes aware of what to store/retrieve.
            form.registerField(stateField);
            // Second, insert the state field into the specific position of a page in the form
            form.getPages().get(0).addField(3, stateField);
        }

        return form;
    }

    /**
     * Let's suppose this method is reponsible for collection user state information and determine whether
     * an extra field should be shown or not based on the underlyig business logic which might invoke a backend system as well.
     * <P>
     * For simplicity, this method simply determines it based on query string parameter.
     * If there's a query string parameter, "region=US", then it returns true.
     * </P>
     * @param request
     * @return
     */
    private boolean isStateFieldNecessary(final HstRequest request) {
        String region = getPublicRequestParameter(request, "region");
        return (StringUtils.equalsIgnoreCase("US", region));
    }

    /**
     * Creates an extra required field at runtime for 'State' input text field.
     * 
     * @param form
     * @return
     */
    private TextField createStateTextField(final Form form) {
        TextField stateTextField = TextField.builder(form, "state")
                .label("State")
                .length(40)
                .mandatory(true)
                .minLength(1)
                .maxLength(40).build();
        return stateTextField;
    }
}
