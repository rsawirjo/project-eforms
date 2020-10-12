/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 *
 */
package com.onehippo.cms7.eforms.demo.validationrules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.onehippo.cms7.eforms.hst.model.ErrorMessage;
import com.onehippo.cms7.eforms.hst.validation.rules.BaseRule;
import com.onehippo.cms7.eforms.hst.validation.rules.RuleType;

public class AgeEighteenPlusRule extends BaseRule {

    private static final String REQUEST_DATE_FORMAT = "MM/dd/yyyy";

    private static final String REQUEST_DATE_HOUR_MIN_FORMAT = "MM/dd/yyyy HH:mm";

    private static final String REQUEST_DATE_HOUR_MIN_SEC_FORMAT = "MM/dd/yyyy HH:mm:ss";

    private static final String MESSAGE_KEY_TOO_YOUNG = "validation.message.age.too.young";

    @Override
    public boolean validate(Map<String, String[]> fieldValuesMap) {

        if (fieldValuesMap.isEmpty()) {
            return false;
        }

        // assuming single item
        String[] values = fieldValuesMap.values().iterator().next();

        if (ArrayUtils.isEmpty(values)) {
            return false;
        }

        // assuming single value
        String value = StringUtils.trim(values[0]);

        if (StringUtils.isEmpty(value)) {
            return false;
        }

        final int valueLen = value.length();

        SimpleDateFormat dateFormat = null;

        if (valueLen == REQUEST_DATE_HOUR_MIN_SEC_FORMAT.length()) {
            dateFormat = new SimpleDateFormat(REQUEST_DATE_HOUR_MIN_SEC_FORMAT);
        } else if (valueLen == REQUEST_DATE_HOUR_MIN_FORMAT.length()) {
            dateFormat = new SimpleDateFormat(REQUEST_DATE_HOUR_MIN_FORMAT);
        } else if (valueLen == REQUEST_DATE_FORMAT.length()) {
            dateFormat = new SimpleDateFormat(REQUEST_DATE_FORMAT);
        } else {
            return false;
        }

        try {
            Calendar inputDate = Calendar.getInstance();
            inputDate.setTime(dateFormat.parse(value));

            Calendar currentDate = Calendar.getInstance();
            Calendar eighteenYearsAgo = Calendar.getInstance();
            eighteenYearsAgo.set(Calendar.YEAR, currentDate.get(Calendar.YEAR) - 18);

            if (eighteenYearsAgo.compareTo(inputDate) >= 0) {
                return true;
            }
        } catch (ParseException e) {
            // ignore...
        }

        return false;
    }

    @Override
    public RuleType getType() {
        return RuleType.CUSTOM_FIELD_VALIDATION_RULE;
    }

    @Override
    protected String getResourceBundleBaseName() {
        return getClass().getCanonicalName();
    }

    @Override
    public ErrorMessage getMessage(String name, String label, String value) {
        return new ErrorMessage(MESSAGE_KEY_TOO_YOUNG, name, label, "Field " + label + " is invalid");
    }

}
