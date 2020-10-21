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

import com.google.gson.Gson;
import com.onehippo.cms7.eforms.demo.util.ContextHelper;
import com.onehippo.cms7.eforms.hst.beans.AbstractFieldBean;
import com.onehippo.cms7.eforms.hst.beans.AntiSpamFieldBean;
import com.onehippo.cms7.eforms.hst.beans.CheckBoxBean;
import com.onehippo.cms7.eforms.hst.beans.CheckBoxGroupBean;
import com.onehippo.cms7.eforms.hst.beans.DateFieldBean;
import com.onehippo.cms7.eforms.hst.beans.DropdownBean;
import com.onehippo.cms7.eforms.hst.beans.FieldGroupBean;
import com.onehippo.cms7.eforms.hst.beans.FieldType;
import com.onehippo.cms7.eforms.hst.beans.FileuploadBean;
import com.onehippo.cms7.eforms.hst.beans.LikertBean;
import com.onehippo.cms7.eforms.hst.beans.MultiSelectBean;
import com.onehippo.cms7.eforms.hst.beans.RadioBoxBean;
import com.onehippo.cms7.eforms.hst.beans.RadioGroupBean;
import com.onehippo.cms7.eforms.hst.beans.SelectType;
import com.onehippo.cms7.eforms.hst.beans.SimpleTextBean;
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
            @Context HttpServletResponse servletResponse) {
        AbstractFieldBean abstractFieldBean;
        if (StringUtils.isNotEmpty(uuid)) {
            abstractFieldBean = getAbstractFieldBean(uuid);
            if (abstractFieldBean != null) {
                Gson gson = new Gson(); 
                String type = "";

                switch (abstractFieldBean.getType()) {
                    case FIELD_GROUP:
                        FieldGroupBean fieldGroupBean = (FieldGroupBean) abstractFieldBean;
                        FieldGroupBeanResponse fieldGroupBeanResponse = new FieldGroupBeanResponse(
                                fieldGroupBean.getFieldName(), fieldGroupBean.isMandatory(), fieldGroupBean.getHint(),
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
                        TextFieldBean textFieldBean = (TextFieldBean) abstractFieldBean;
                        TextFieldBeanResponse textFieldBeanResponse = new TextFieldBeanResponse(
                                textFieldBean.getFieldName(), textFieldBean.isMandatory(), textFieldBean.getHint(),
                                textFieldBean.getAutocomplete(), textFieldBean.isExpandHint(),
                                textFieldBean.getLabel(), textFieldBean.getValidationRuleId(),
                                textFieldBean.getValidationRuleLabel(), textFieldBean.getValidationRuleClass(),
                                textFieldBean.getRegExp(), textFieldBean.isCaseSensitive(), textFieldBean.getFieldClass(),
                                textFieldBean.getExtraCssClass(), textFieldBean.isConditional(),
                                textFieldBean.isConditionNegate(), textFieldBean.getConditionFieldName(),
                                textFieldBean.getConditionFieldValue(),
                                textFieldBean.getLength(), textFieldBean.getMinLength(), textFieldBean.getMaxLength(),
                                textFieldBean.getInitialValue(), "TEXT_FIELD"
                        );
                        return gson.toJson(textFieldBeanResponse);
                    case SIMPLETEXTFIELD:
                        SimpleTextBean simpleTextBean = (SimpleTextBean) abstractFieldBean;
                        SimpleTextBeanResponse simpleTextBeanResponse = new SimpleTextBeanResponse(
                                simpleTextBean.getFieldName(), simpleTextBean.isMandatory(), simpleTextBean.getHint(),
                                simpleTextBean.getAutocomplete(), simpleTextBean.isExpandHint(),
                                simpleTextBean.getLabel(), simpleTextBean.getValidationRuleId(),
                                simpleTextBean.getValidationRuleLabel(), simpleTextBean.getValidationRuleClass(),
                                simpleTextBean.getRegExp(), simpleTextBean.isCaseSensitive(), simpleTextBean.getFieldClass(),
                                simpleTextBean.getExtraCssClass(), simpleTextBean.isConditional(),
                                simpleTextBean.isConditionNegate(), simpleTextBean.getConditionFieldName(),
                                simpleTextBean.getConditionFieldValue(), "SIMPLETEXTFIELD"
                        );
                        return gson.toJson(simpleTextBeanResponse);
                    case TEXTAREA:
                        TextAreaBean textAreaBean = (TextAreaBean) abstractFieldBean;
                        TextAreaBeanResponse textAreaBeanResponse = new TextAreaBeanResponse(
                                textAreaBean.getFieldName(), textAreaBean.isMandatory(), textAreaBean.getHint(),
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
                        SingleSelectBean singleSelectBean = (SingleSelectBean) abstractFieldBean;
                        if (singleSelectBean.getSelectType().equals(SelectType.RADIOGROUP)) {
                            type = "RADIOGROUP";
                        } else {
                            type = "SELECT";
                        }
                        
                        SingleSelectBeanResponse singleSelectBeanResponse = new SingleSelectBeanResponse(
                                singleSelectBean.getFieldName(), singleSelectBean.isMandatory(), singleSelectBean.getHint(),
                                singleSelectBean.getAutocomplete(), singleSelectBean.isExpandHint(),
                                singleSelectBean.getLabel(), singleSelectBean.getValidationRuleId(),
                                singleSelectBean.getValidationRuleLabel(), singleSelectBean.getValidationRuleClass(),
                                singleSelectBean.getRegExp(), singleSelectBean.isCaseSensitive(), singleSelectBean.getFieldClass(),
                                singleSelectBean.getExtraCssClass(), singleSelectBean.isConditional(),
                                singleSelectBean.isConditionNegate(), singleSelectBean.getConditionFieldName(),
                                singleSelectBean.getConditionFieldValue(),
                                singleSelectBean.getValue(), singleSelectBean.getText(), type
                        );
                        return gson.toJson(singleSelectBeanResponse);
                    case CHECKBOXGROUP:
                        CheckBoxGroupBean checkBoxGroupBean = (CheckBoxGroupBean) abstractFieldBean;
                        CheckBoxGroupBeanResponse checkBoxGroupBeanResponse = new CheckBoxGroupBeanResponse(
                                checkBoxGroupBean.getFieldName(), checkBoxGroupBean.isMandatory(), checkBoxGroupBean.getHint(),
                                checkBoxGroupBean.getAutocomplete(), checkBoxGroupBean.isExpandHint(),
                                checkBoxGroupBean.getLabel(), checkBoxGroupBean.getValidationRuleId(),
                                checkBoxGroupBean.getValidationRuleLabel(), checkBoxGroupBean.getValidationRuleClass(),
                                checkBoxGroupBean.getRegExp(), checkBoxGroupBean.isCaseSensitive(), checkBoxGroupBean.getFieldClass(),
                                checkBoxGroupBean.getExtraCssClass(), checkBoxGroupBean.isConditional(),
                                checkBoxGroupBean.isConditionNegate(), checkBoxGroupBean.getConditionFieldName(),
                                checkBoxGroupBean.getConditionFieldValue(),
                                checkBoxGroupBean.getValues(), checkBoxGroupBean.getDisplayValues(),
                                checkBoxGroupBean.getLength(), checkBoxGroupBean.getMinLength(), checkBoxGroupBean.getMaxLength(),
                                checkBoxGroupBean.getAllowOther(),
                                "CHECKBOXGROUP"
                        );
                        return gson.toJson(checkBoxGroupBeanResponse);
                    case RADIOGROUP:
                        RadioGroupBean radioGroupBean = (RadioGroupBean) abstractFieldBean;
                        RadioGroupBeanResponse radioGroupBeanResponse = new RadioGroupBeanResponse(
                                radioGroupBean.getFieldName(), radioGroupBean.isMandatory(), radioGroupBean.getHint(),
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
                                dropdownBean.getFieldName(), dropdownBean.isMandatory(), dropdownBean.getHint(),
                                dropdownBean.getAutocomplete(), dropdownBean.isExpandHint(),
                                dropdownBean.getLabel(), dropdownBean.getValidationRuleId(),
                                dropdownBean.getValidationRuleLabel(), dropdownBean.getValidationRuleClass(),
                                dropdownBean.getRegExp(), dropdownBean.isCaseSensitive(), dropdownBean.getFieldClass(),
                                dropdownBean.getExtraCssClass(), dropdownBean.isConditional(),
                                dropdownBean.isConditionNegate(), dropdownBean.getConditionFieldName(),
                                dropdownBean.getConditionFieldValue(),
                                dropdownBean.getValues(), dropdownBean.getDisplayValues(), dropdownBean.getInitialValueOption(),
                                dropdownBean.getInitialValue(), dropdownBean.getInitialText(), "DROPDOWN"
                        );
                        return gson.toJson(dropdownBeanResponse);
                    case DATE_FIELD:
                        DateFieldBean dateFieldBean = (DateFieldBean) abstractFieldBean;
                        DateFieldBeanResponse dateFieldBeanResponse = new DateFieldBeanResponse(
                                dateFieldBean.getFieldName(), dateFieldBean.isMandatory(), dateFieldBean.getHint(),
                                dateFieldBean.getAutocomplete(), dateFieldBean.isExpandHint(),
                                dateFieldBean.getLabel(), dateFieldBean.getValidationRuleId(),
                                dateFieldBean.getValidationRuleLabel(), dateFieldBean.getValidationRuleClass(),
                                dateFieldBean.getRegExp(), dateFieldBean.isCaseSensitive(), dateFieldBean.getFieldClass(),
                                dateFieldBean.getExtraCssClass(), dateFieldBean.isConditional(),
                                dateFieldBean.isConditionNegate(), dateFieldBean.getConditionFieldName(),
                                dateFieldBean.getConditionFieldValue(),
                                dateFieldBean.getLength(), dateFieldBean.getDateformat(), dateFieldBean.isInitialValueSet(),
                                dateFieldBean.isInitialValueDayOffsetMode(), dateFieldBean.getInitialValue(),
                                dateFieldBean.getInitialValueDayOffset(), dateFieldBean.isInitialValueRuleMode(),
                                dateFieldBean.getInitialValueRule(), "DATE_FIELD"
                        );
                        return gson.toJson(dateFieldBeanResponse);
                    case CHECKBOX:
                    case RADIOBOX:
                        if (abstractFieldBean instanceof CheckBoxBean) {
                            type = "CHECKBOX";
                        }
                        if (abstractFieldBean instanceof RadioBoxBean) {
                            type = "RADIOBOX";
                        }
                        CommonBeanResponse commonBeanResponse = new CommonBeanResponse(
                                abstractFieldBean.getFieldName(), abstractFieldBean.isMandatory(), abstractFieldBean.getHint(),
                                abstractFieldBean.getAutocomplete(), abstractFieldBean.isExpandHint(),
                                abstractFieldBean.getLabel(), abstractFieldBean.getValidationRuleId(),
                                abstractFieldBean.getValidationRuleLabel(), abstractFieldBean.getValidationRuleClass(),
                                abstractFieldBean.getRegExp(), abstractFieldBean.isCaseSensitive(), abstractFieldBean.getFieldClass(),
                                abstractFieldBean.getExtraCssClass(), abstractFieldBean.isConditional(),
                                abstractFieldBean.isConditionNegate(), abstractFieldBean.getConditionFieldName(),
                                abstractFieldBean.getConditionFieldValue(), type
                        );
                        return gson.toJson(commonBeanResponse);
                    case LIKERT:
                        LikertBean likertBean = (LikertBean) abstractFieldBean;
                        LikertBeanResponse likertBeanResponse = new LikertBeanResponse(
                                likertBean.getFieldName(), likertBean.isMandatory(), likertBean.getHint(),
                                likertBean.getAutocomplete(), likertBean.isExpandHint(),
                                likertBean.getLabel(), likertBean.getValidationRuleId(),
                                likertBean.getValidationRuleLabel(), likertBean.getValidationRuleClass(),
                                likertBean.getRegExp(), likertBean.isCaseSensitive(), likertBean.getFieldClass(),
                                likertBean.getExtraCssClass(), likertBean.isConditional(),
                                likertBean.isConditionNegate(), likertBean.getConditionFieldName(),
                                likertBean.getConditionFieldValue(),
                                likertBean.getOptions(), likertBean.getQuestions(), "LIKERT"
                        );
                        return gson.toJson(likertBeanResponse);
                    case FILEUPLOAD_FIELD:
                        FileuploadBean fileuploadBean = (FileuploadBean)abstractFieldBean;
                        FileuploadBeanResponse fileuploadBeanResponse = new FileuploadBeanResponse(
                                fileuploadBean.getFieldName(), fileuploadBean.isMandatory(), fileuploadBean.getHint(),
                                fileuploadBean.getAutocomplete(), fileuploadBean.isExpandHint(),
                                fileuploadBean.getLabel(), fileuploadBean.getValidationRuleId(),
                                fileuploadBean.getValidationRuleLabel(), fileuploadBean.getValidationRuleClass(),
                                fileuploadBean.getRegExp(), fileuploadBean.isCaseSensitive(), fileuploadBean.getFieldClass(),
                                fileuploadBean.getExtraCssClass(), fileuploadBean.isConditional(),
                                fileuploadBean.isConditionNegate(), fileuploadBean.getConditionFieldName(),
                                fileuploadBean.getConditionFieldValue(),
                                fileuploadBean.getMaxUploadSize(), fileuploadBean.getLength(), fileuploadBean.getFileExtensions(),
                                "FILEUPLOAD_FIELD"
                        );
                        return gson.toJson(fileuploadBeanResponse);
                    case MULTISELECT_BEAN:
                        MultiSelectBean multiSelectBean = (MultiSelectBean) abstractFieldBean;

                        MultiSelectBeanResponse multiSelectBeanResponse = new MultiSelectBeanResponse(
                                multiSelectBean.getFieldName(), multiSelectBean.isMandatory(), multiSelectBean.getHint(),
                                multiSelectBean.getAutocomplete(), multiSelectBean.isExpandHint(),
                                multiSelectBean.getLabel(), multiSelectBean.getValidationRuleId(),
                                multiSelectBean.getValidationRuleLabel(), multiSelectBean.getValidationRuleClass(),
                                multiSelectBean.getRegExp(), multiSelectBean.isCaseSensitive(), multiSelectBean.getFieldClass(),
                                multiSelectBean.getExtraCssClass(), multiSelectBean.isConditional(),
                                multiSelectBean.isConditionNegate(), multiSelectBean.getConditionFieldName(),
                                multiSelectBean.getConditionFieldValue(),
                                multiSelectBean.getSelectType(), multiSelectBean.getValue(), multiSelectBean.getText(),
                                "MULTISELECT_BEAN"
                        );
                        return gson.toJson(multiSelectBeanResponse);
                    case ANTISPAM:
                        AntiSpamFieldBean antiSpamFieldBean = (AntiSpamFieldBean) abstractFieldBean;

                        AntiSpamFieldBeanResponse antiSpamFieldBeanResponse = new AntiSpamFieldBeanResponse(
                                antiSpamFieldBean.getFieldName(), antiSpamFieldBean.isMandatory(), antiSpamFieldBean.getHint(),
                                antiSpamFieldBean.getAutocomplete(), antiSpamFieldBean.isExpandHint(),
                                antiSpamFieldBean.getLabel(), antiSpamFieldBean.getValidationRuleId(),
                                antiSpamFieldBean.getValidationRuleLabel(), antiSpamFieldBean.getValidationRuleClass(),
                                antiSpamFieldBean.getRegExp(), antiSpamFieldBean.isCaseSensitive(), antiSpamFieldBean.getFieldClass(),
                                antiSpamFieldBean.getExtraCssClass(), antiSpamFieldBean.isConditional(),
                                antiSpamFieldBean.isConditionNegate(), antiSpamFieldBean.getConditionFieldName(),
                                antiSpamFieldBean.getConditionFieldValue(), antiSpamFieldBean.getAntiSpamType(), "ANTISPAM"
                        );
                        return gson.toJson(antiSpamFieldBeanResponse);
                    default:
                        return "not found";
                }
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
