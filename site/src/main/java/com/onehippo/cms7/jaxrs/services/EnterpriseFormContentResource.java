package com.onehippo.cms7.jaxrs.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.onehippo.cms7.eforms.demo.util.ContextHelper;
import com.onehippo.cms7.eforms.hst.beans.AbstractFieldBean;
import com.onehippo.cms7.eforms.hst.beans.CheckBoxGroupBean;
import com.onehippo.cms7.eforms.hst.beans.DropdownBean;
import com.onehippo.cms7.eforms.hst.beans.FieldGroupBean;
import com.onehippo.cms7.eforms.hst.beans.FieldType;
import com.onehippo.cms7.eforms.hst.beans.RadioGroupBean;
import com.onehippo.cms7.eforms.hst.beans.SingleSelectBean;
import com.onehippo.cms7.eforms.hst.beans.TextAreaBean;
import com.onehippo.cms7.eforms.hst.beans.TextFieldBean;

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/eforms")
public class EnterpriseFormContentResource extends BaseRestResource {

    @GET
    @Path("/getAbstractFieldBean/{uuid}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public String getAbstractFieldBean(
            @PathParam("uuid") String uuid,
            @Context HttpServletRequest servletRequest,
            @Context HttpServletResponse servletResponse) throws JsonProcessingException {
        AbstractFieldBean abstractFieldBean;
        if (StringUtils.isNotEmpty(uuid)) {
            abstractFieldBean = getAbstractFieldBean(uuid);
            if (abstractFieldBean != null ) {
                Gson gson = new Gson();
                switch (abstractFieldBean.getType()) {
                    case FIELD_GROUP:
                        FieldGroupBean fieldGroupBean = (FieldGroupBean)abstractFieldBean;
                        FieldGroupBeanResponse fieldGroupBeanResponse = new FieldGroupBeanResponse(
                                fieldGroupBean.getName(), fieldGroupBean.isMandatory(), fieldGroupBean.getHint(),
                                fieldGroupBean.getAutocomplete(), fieldGroupBean.isExpandHint(),
                                fieldGroupBean.getLabel(), fieldGroupBean.getValidationRuleId(),
                                fieldGroupBean.getValidationRuleLabel(), fieldGroupBean.getValidationRuleClass(),
                                fieldGroupBean.getRegExp(), fieldGroupBean.isCaseSensitive(), fieldGroupBean.getFieldClass(),
                                fieldGroupBean.getExtraCssClass(), fieldGroupBean.isConditional(),
                                fieldGroupBean.isConditionNegate(), fieldGroupBean.getConditionFieldName(),
                                fieldGroupBean.getConditionFieldValue(), FieldType.FIELD_GROUP.toString(),
                                fieldGroupBean.getLabel(), fieldGroupBean.getFieldNamePrefix(),
                                fieldGroupBean.isOneline(), fieldGroupBean.getFieldBeans()
                        );
                        return gson.toJson(fieldGroupBeanResponse);
                    case TEXT_FIELD:
                        TextFieldBean textFieldBean = (TextFieldBean)abstractFieldBean;
                        TextFieldBeanResponse textFieldBeanResponse = new TextFieldBeanResponse(
                                textFieldBean.getName(), textFieldBean.isMandatory(), textFieldBean.getHint(),
                                textFieldBean.getAutocomplete(), textFieldBean.isExpandHint(),
                                textFieldBean.getLabel(), textFieldBean.getValidationRuleId(),
                                textFieldBean.getValidationRuleLabel(), textFieldBean.getValidationRuleClass(),
                                textFieldBean.getRegExp(), textFieldBean.isCaseSensitive(), textFieldBean.getFieldClass(),
                                textFieldBean.getExtraCssClass(), textFieldBean.isConditional(),
                                textFieldBean.isConditionNegate(), textFieldBean.getConditionFieldName(),
                                textFieldBean.getConditionFieldValue(),
                                textFieldBean.getLength(), textFieldBean.getMinLength(),textFieldBean.getMaxLength(),
                                textFieldBean.getInitialValue(), "TEXT_FIELD"
                        );
                        return gson.toJson(textFieldBeanResponse);
                    case TEXTAREA:
                        TextAreaBean textAreaBean = (TextAreaBean)abstractFieldBean;
                        TextAreaBeanResponse textAreaBeanResponse = new TextAreaBeanResponse(
                                textAreaBean.getName(), textAreaBean.isMandatory(), textAreaBean.getHint(),
                                textAreaBean.getAutocomplete(), textAreaBean.isExpandHint(),
                                textAreaBean.getLabel(), textAreaBean.getValidationRuleId(),
                                textAreaBean.getValidationRuleLabel(), textAreaBean.getValidationRuleClass(),
                                textAreaBean.getRegExp(), textAreaBean.isCaseSensitive(), textAreaBean.getFieldClass(),
                                textAreaBean.getExtraCssClass(), textAreaBean.isConditional(),
                                textAreaBean.isConditionNegate(), textAreaBean.getConditionFieldName(),
                                textAreaBean.getConditionFieldValue(),
                                textAreaBean.getRows(), textAreaBean.getCols(), textAreaBean.getMinLength(),
                                textAreaBean.getMaxLength(), "TEXTAREA"
                        );
                        return gson.toJson(textAreaBeanResponse);
                    case SELECT:
                        SingleSelectBean singleSelectBean = (SingleSelectBean)abstractFieldBean;
                        return gson.toJson(singleSelectBean);
                    case CHECKBOXGROUP:
                        CheckBoxGroupBean checkBoxGroupBean = (CheckBoxGroupBean)abstractFieldBean;
                        return gson.toJson(checkBoxGroupBean);
                    case RADIOGROUP:
                        RadioGroupBean radioGroupBean = (RadioGroupBean) abstractFieldBean;
                        RadioGroupBeanResponse radioGroupBeanResponse = new RadioGroupBeanResponse(
                                radioGroupBean.getName(), radioGroupBean.isMandatory(), radioGroupBean.getHint(),
                                radioGroupBean.getAutocomplete(), radioGroupBean.isExpandHint(),
                                radioGroupBean.getLabel(), radioGroupBean.getValidationRuleId(),
                                radioGroupBean.getValidationRuleLabel(), radioGroupBean.getValidationRuleClass(),
                                radioGroupBean.getRegExp(), radioGroupBean.isCaseSensitive(), radioGroupBean.getFieldClass(),
                                radioGroupBean.getExtraCssClass(), radioGroupBean.isConditional(),
                                radioGroupBean.isConditionNegate(), radioGroupBean.getConditionFieldName(),
                                radioGroupBean.getConditionFieldValue(),
                                radioGroupBean.getValues(), radioGroupBean.getDisplayValues(), radioGroupBean.getLength(),
                                radioGroupBean.getLength(), radioGroupBean.getLength(), radioGroupBean.getAllowOther(),
                                "RADIOGROUP"
                        );
                        return gson.toJson(radioGroupBeanResponse);
                    case DROPDOWN:
                        DropdownBean dropdownBean = (DropdownBean) abstractFieldBean;
                        DropdownBeanResponse dropdownBeanResponse = new DropdownBeanResponse(
                                dropdownBean.getName(), dropdownBean.isMandatory(), dropdownBean.getHint(),
                                dropdownBean.getAutocomplete(), dropdownBean.isExpandHint(),
                                dropdownBean.getLabel(), dropdownBean.getValidationRuleId(),
                                dropdownBean.getValidationRuleLabel(), dropdownBean.getValidationRuleClass(),
                                dropdownBean.getRegExp(), dropdownBean.isCaseSensitive(), dropdownBean.getFieldClass(),
                                dropdownBean.getExtraCssClass(), dropdownBean.isConditional(),
                                dropdownBean.isConditionNegate(), dropdownBean.getConditionFieldName(),
                                dropdownBean.getConditionFieldValue(),
                                dropdownBean.getValues(), dropdownBean.getDisplayValues(),dropdownBean.getInitialValueOption(),
                                dropdownBean.getInitialValue(),dropdownBean.getInitialText(), "DROPDOWN"
                        );
                        return gson.toJson(dropdownBeanResponse);
                    default:
                        return "not found";
            }

//                        RADIO,
//                        RADIOGROUP,
//                        CHECKBOX,
//                        DROPDOWN,
//                        RADIOBOX,
//                        SIMPLETEXTFIELD,
//                        LIKERT,
//                        TEXT_FIELD,
//                        FILEUPLOAD_FIELD,
//                        ,
//                        MULTISELECT_BEAN,
//                        MULTI_SELECT,
//                        SUBMITBUTTON,
//                        DATE_FIELD,
//                        ANTISPAM
            }


        }

        return "not found";
    }

    private AbstractFieldBean getAbstractFieldBean(final String uuidValue) {
        ContextHelper contextHelper = new ContextHelper();
        HstRequestContext hstRequestContext = contextHelper.getRequestContext();
        HippoBean scope = hstRequestContext.getSiteContentBaseBean();
        HippoFolderBean formsFolder = scope.getBean("forms");
        HippoBean hippoBean = formsFolder.getBeanByUUID(uuidValue, HippoBean.class);
        if (hippoBean instanceof AbstractFieldBean) {
            return (AbstractFieldBean) hippoBean;
        }
        return null;
    }
}
