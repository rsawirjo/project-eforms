/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 */

package com.onehippo.cms7.eforms.demo.fieldextension;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.config.IPluginConfig;

import com.onehippo.cms7.eforms.cms.fieldextensions.AbstractFieldExtensionPlugin;
import com.onehippo.cms7.eforms.cms.fieldextensions.model.FieldExtensionModel;
import com.onehippo.cms7.eforms.cms.model.SingleValuePropertyModel;

/**
 * @version "$Id$"
 */
public class FieldExtensionExample extends AbstractFieldExtensionPlugin {


    private static final long serialVersionUID = 1L;


    public FieldExtensionExample(final String id, final FieldExtensionModel extensionModel, final IPluginConfig config, final IPluginContext context) {
        super(id, extensionModel, config, context);
        final Label label = new Label("label", "First example");
        final TextField<String> textField = new TextField<>("first", new SingleValuePropertyModel<String>(extensionModel.getNodeModel(), "first"));
        textField.setEnabled(true);
        add(textField);
        add(label);
        setOutputMarkupId(true);
    }


}
