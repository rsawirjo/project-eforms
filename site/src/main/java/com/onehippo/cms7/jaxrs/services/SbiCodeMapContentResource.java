package com.onehippo.cms7.jaxrs.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;

import com.google.gson.Gson;
import com.onehippo.cms7.eforms.demo.beans.SbiCodeMap;
import com.onehippo.cms7.eforms.demo.beans.SbiCodeRow;
import com.onehippo.cms7.eforms.demo.util.ContextHelper;

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/sbicodemap")
public class SbiCodeMapContentResource extends BaseRestResource {
    @GET
    @Path("/getAllDescriptions/")
    @Produces({"application/json"})
    public String getAllDescriptions(
            @Context HttpServletRequest servletRequest,
            @Context HttpServletResponse servletResponse) {
        SbiCodeMap sbiCodeMap = getSbiCodeMapDocument();
        if (sbiCodeMap != null) {
            List<SbiCodeRow> sbiCodeRows = sbiCodeMap.getSbiCodeRowList();
            Map<String, String> allDescriptions = new HashMap<>();
            for (SbiCodeRow sbiCodeRow : sbiCodeRows) {
                allDescriptions.put(sbiCodeRow.getSbi(), sbiCodeRow.getDescription());
            }
            if (!allDescriptions.isEmpty()) {
                Gson gson = new Gson();
                return gson.toJson(allDescriptions);
            }
        }
        return "No Results";
    }

    @GET
    @Path("/getSbiRow/{sbiCode}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public String getSbiRow (
            @PathParam("sbiCode") String sbiCode,
            @Context HttpServletRequest servletRequest,
            @Context HttpServletResponse servletResponse) {
        if (StringUtils.isNotEmpty(sbiCode)) {
            SbiCodeMap sbiCodeMap = getSbiCodeMapDocument();
            if (sbiCodeMap != null) {
                List<SbiCodeRow> sbiCodeRows = sbiCodeMap.getSbiCodeRowList();
                Gson gson = new Gson();
                for (SbiCodeRow sbiCodeRow : sbiCodeRows) {
                    if (StringUtils.equalsIgnoreCase(sbiCodeRow.getSbi(), sbiCode)) {
                        SbiCodeRowResponseObject sbiCodeRowResponseObject = new SbiCodeRowResponseObject(
                                sbiCodeRow.getSbi(), sbiCodeRow.getDescription(), sbiCodeRow.getZzpPackage(),
                                sbiCodeRow.getPremieExTax(), sbiCodeRow.getBik(), sbiCodeRow.getPrice(), sbiCodeRow.getPremieIncTax()
                        );
                        return gson.toJson(sbiCodeRowResponseObject);
                    }
                }
            }
        }
        return "No Results";
    }

    private SbiCodeMap getSbiCodeMapDocument() {
        ContextHelper contextHelper = new ContextHelper();
        HstRequestContext hstRequestContext = contextHelper.getRequestContext();
        HippoBean scope = hstRequestContext.getSiteContentBaseBean();
        HippoFolderBean sbiCodesFolder = scope.getBean("sbicode-files");
        try {
            HstQueryManager hstQueryManager = hstRequestContext.getQueryManager();
            HstQuery hstQuery = hstQueryManager.createQuery(sbiCodesFolder, SbiCodeMap.class);
            hstQuery.addOrderByDescending("hippostdpubwf:publicationDate");
            HstQueryResult result = hstQuery.execute();

            if (result != null) {
                HippoBeanIterator iterator = result.getHippoBeans();
                if (iterator.hasNext()) {
                    HippoBean document = iterator.nextHippoBean();
                    if (document instanceof SbiCodeMap){
                        return (SbiCodeMap) document;
                    }
                    return null;
                }
            }

        } catch (QueryException e) {
            throw new HstComponentException("An exception occurred while creating or executing HstQuery. ", e);
        }

        return null;
    }
}
