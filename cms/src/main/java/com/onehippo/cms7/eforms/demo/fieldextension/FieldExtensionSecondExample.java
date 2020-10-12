/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 */

package com.onehippo.cms7.eforms.demo.fieldextension;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.config.IPluginConfig;

import com.google.common.base.Strings;
import com.onehippo.cms7.eforms.cms.fieldextensions.AbstractFieldExtensionPlugin;
import com.onehippo.cms7.eforms.cms.fieldextensions.model.FieldExtensionModel;
import com.onehippo.cms7.eforms.cms.model.SingleValuePropertyModel;

/**
 * @version "$Id$"
 */
public class FieldExtensionSecondExample extends AbstractFieldExtensionPlugin {


    private static final long serialVersionUID = 1L;


    public FieldExtensionSecondExample(final String id, final FieldExtensionModel extensionModel, final IPluginConfig config, final IPluginContext context) {
        super(id, extensionModel, config, context);
        final SingleValuePropertyModel<String> stringModel = new SingleValuePropertyModel<>(extensionModel.getNodeModel(), "second");
        final Label label = new Label("label", "Second example");
        final CheckBox checkbox = new CheckBox("second", new IModel<Boolean>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Boolean getObject() {
                final String value = stringModel.getObject();
                return BooleanUtils.toBoolean(value);
            }

            @Override
            public void setObject(final Boolean object) {
                if (object == null || object.equals(Boolean.FALSE)) {
                    stringModel.setObject("false");
                } else {
                    stringModel.setObject("true");
                }
            }

            @Override
            public void detach() {

            }

        });
        checkbox.setEnabled(true);
        add(checkbox);
        add(label);
        setOutputMarkupId(true);
    }


}
