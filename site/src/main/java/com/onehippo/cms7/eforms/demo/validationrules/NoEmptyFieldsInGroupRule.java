/*
 * Copyright 2013 Hippo B.V. (http://www.onehippo.com)
 *
 */
package com.onehippo.cms7.eforms.demo.validationrules;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.onehippo.cms7.eforms.hst.model.ErrorMessage;
import com.onehippo.cms7.eforms.hst.validation.rules.BaseRule;
import com.onehippo.cms7.eforms.hst.validation.rules.RuleType;

public class NoEmptyFieldsInGroupRule extends BaseRule {

    public static final String MESSAGE_KEY = "validation.message.noemptyfieldsingroup";

    @Override
    public boolean validate(Map<String, String[]> fieldValuesMap) {

        if (fieldValuesMap.isEmpty()) {
            return false;
        }
        
        Iterator<String[]> it = fieldValuesMap.values().iterator();
        while (it.hasNext()) {
            String[] values = it.next();
            
            if (ArrayUtils.isEmpty(values)) {
                return false;
            }

            for (String value : values) {
                
                if (StringUtils.isBlank(value)) {
                    return false;
                }
            }    
        }

        return true;
    }

    @Override
    public RuleType getType() {
        return RuleType.CUSTOM_GROUP_VALIDATION_RULE;
    }

    @Override
    public ErrorMessage getMessage(String name, String label, String value) {
        return new ErrorMessage(MESSAGE_KEY, name, label, "Group " + label + " contains empty fields");
    }

    @Override
    protected String getResourceBundleBaseName() {
        return getClass().getCanonicalName();
    }

}
