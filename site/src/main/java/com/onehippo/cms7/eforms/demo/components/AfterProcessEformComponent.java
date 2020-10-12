/**
 * Copyright 2013 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.onehippo.cms7.eforms.demo.components;

import com.onehippo.cms7.eforms.hst.behaviors.StoreFormDataBehavior;
import com.onehippo.cms7.eforms.hst.components.AfterProcessComponent;
import com.onehippo.cms7.eforms.hst.model.ErrorMessage;
import com.onehippo.cms7.eforms.hst.model.Form;
import org.hippoecm.hst.component.support.forms.FormMap;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;

import java.util.Map;

/**
 * afterProcessDemoEformComponent
 */
public class AfterProcessEformComponent extends AfterProcessComponent {

    @Override
    public void onValidationError(HstRequest request, HstResponse response, Form form, FormMap map, Map<String, ErrorMessage> errors) {
    }

    @Override
    public boolean onValidationSuccess(HstRequest request, HstResponse response, Form form, FormMap map) {
        return true;
    }

    @Override
    protected void addConfiguredBehaviors(HstRequest request) {
        super.addConfiguredBehaviors(request);
        addBehavior(new StoreFormDataBehavior());
    }

}
