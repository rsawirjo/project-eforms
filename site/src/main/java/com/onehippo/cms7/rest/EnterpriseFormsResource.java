package com.onehippo.cms7.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.onehippo.cms7.essentials.components.paging.Pageable;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.onehippo.cms7.essentials.components.rest.ctx.DefaultRestContext;

import com.onehippo.cms7.eforms.hst.beans.FormBean;

/**
 * @version "$Id$"
 */

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/Form/")
public class EnterpriseFormsResource extends BaseRestResource {

    @GET
    @Path("/")
    public Pageable<FormBean> index(@Context HttpServletRequest request) {
        return findBeans(new DefaultRestContext(this, request), FormBean.class);
    }

    @GET
    @Path("/page/{page}")
    public Pageable<FormBean> page(@Context HttpServletRequest request, @PathParam("page") int page) {
        return findBeans(new DefaultRestContext(this, request, page, DefaultRestContext.PAGE_SIZE), FormBean.class);
    }

    @GET
    @Path("/page/{page}/{pageSize}")
    public Pageable<FormBean> pageForSize(@Context HttpServletRequest request, @PathParam("page") int page, @PathParam("pageSize") int pageSize) {
        return findBeans(new DefaultRestContext(this, request, page, pageSize), FormBean.class);
    }

}
