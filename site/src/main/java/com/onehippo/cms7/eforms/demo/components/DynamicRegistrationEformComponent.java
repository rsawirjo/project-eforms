/*
 * Copyright 2015 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onehippo.cms7.eforms.demo.components;

import java.util.Arrays;

import com.onehippo.cms7.eforms.hst.beans.FormBean;
import com.onehippo.cms7.eforms.hst.components.FormStoringEformComponent;
import com.onehippo.cms7.eforms.hst.model.AbstractField;
import com.onehippo.cms7.eforms.hst.model.DateField;
import com.onehippo.cms7.eforms.hst.model.FieldGroup;
import com.onehippo.cms7.eforms.hst.model.Form;
import com.onehippo.cms7.eforms.hst.model.Page;
import com.onehippo.cms7.eforms.hst.model.RadioGroup;
import com.onehippo.cms7.eforms.hst.model.TextField;
import com.onehippo.cms7.eforms.hst.model.ctx.FormContext;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.core.component.HstRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DynamicRegistrationEformComponent to demonstrate how to add extra fields/groups/pages at runtime even if there's no
 * field definition in the associated form document.
 *
 * <P>
 * An example hst component which adds multiple pages, each to enter a person's information when the URL has a
 * query parameter, "people=$number", e.g. "people=3"
 * </P>
 */
public class DynamicRegistrationEformComponent extends FormStoringEformComponent {
    private static Logger log = LoggerFactory.getLogger(DynamicRegistrationEformComponent.class);

    @Override
    protected Form parse(final FormBean bean, final FormContext formContext, final HstRequest request) {
        Form form = super.parse(bean, formContext, request);

        long numberOfPeople = getRegisteringPeople(request);
        if (numberOfPeople > 0) {
            for(int i = 0; i < numberOfPeople; i++) {
                final String name = String.format("person-%d", i + 1);
                final String label = String.format("Person %d", i + 1);
                FieldGroup contactFieldGroup = createContactFieldGroup(form, name, label);
                Page page = new Page(label);
                page.addField(contactFieldGroup);

                form.getPages().add(page);
            }
        }

        return form;
    }

    private long getRegisteringPeople(final HstRequest request) {
        String numberOfPeopleValue = getPublicRequestParameter(request, "people");
        return StringUtils.isNotEmpty(numberOfPeopleValue) ? Long.parseLong(numberOfPeopleValue) : 0;
    }

    private FieldGroup createContactFieldGroup(final Form form, final String name, final String label) {
        FieldGroup fieldGroup = FieldGroup.builder(form, name, name).label(label).build();
        form.registerFieldGroup(fieldGroup);

        String[][] fieldNames = {
                {"name", "Name"},
                {"email", "Email"},
                {"street-addr", "Street Address"},
                {"city", "City"},
                {"postal-code", "Postal code"}
        };

        for(String[] fieldName : fieldNames) {

            TextField field = TextField.builder(form, fieldName[0])
                    .label(fieldName[1])
                    .length(40)
                    .minLength(1)
                    .maxLength(40)
                    .build();
            // Always add new field to the group to set the proper field group prefix before registering to the form.
            fieldGroup.addField(field);
            form.registerField(field);
        }
        DateField dobField = DateField.builder(form, "dob")
                .label("Date of Birth")
                .dateFormat("dd/MM/yyyy")
                .build();
        fieldGroup.addField(2, dobField);
        form.registerField(dobField);

        String[] genderValues = {"male", "female", "other"};
        String[] genderDisplayValues = {"Male", "Female", "Unspecified"};
        AbstractField genderField = RadioGroup.builder(form, "gender", Arrays.asList(genderValues))
                .displayValues(Arrays.asList(genderDisplayValues))
                .label("Gender")
                .hint("Select your gender")
                .build();
        fieldGroup.addField(3, genderField);
        form.registerField(genderField);

        return fieldGroup;
    }
}
