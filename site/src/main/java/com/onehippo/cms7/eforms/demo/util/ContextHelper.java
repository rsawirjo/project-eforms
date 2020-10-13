package com.onehippo.cms7.eforms.demo.util;

import java.util.Map;

import javax.jcr.Credentials;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletContext;

import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.ContentNodeBinder;
import org.hippoecm.hst.content.beans.manager.ObjectBeanManager;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManagerImpl;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.standard.HippoAvailableTranslationsBean;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.site.HstServices;

/**
 * Facade for easy access to the request context and the repository. It hides the internals of the
 * {@link HstRequestContext}, and provides methods for frequently used functionality.
 * 
 * @author Ricardo Sawirjo
 */
public class ContextHelper {

    private HstRequestContext context;

    public ContextHelper() {
        if (!HstServices.isAvailable()) {
            throw new IllegalStateException("HSTServices are not available, cannot obtain HstRequestContext.");
        }
        context = RequestContextProvider.get();
    }

    public ContextHelper(final HstRequestContext context) {
        this.context = context;
    }

    public HstRequestContext getRequestContext() {
        return context;
    }

    public ObjectBeanManager getObjectBeanManager() {
        return getRequestContext().getObjectBeanManager();
    }

    public HstQueryManager getQueryManager() {
        return getRequestContext().getQueryManager();
    }

    public Session getPersistableSession() throws RepositoryException {
        HstRequestContext requestContext = getRequestContext();
        Credentials credentials = requestContext.getContextCredentialsProvider().getWritableCredentials(
                requestContext);
        Repository repository = HstServices.getComponentManager().getComponent(Repository.class.getName());
        return repository.login(credentials);
    }

    public <T extends HippoBean> T getRootDocument(final T document) {

        T result = null;
        if (document != null) {
            HippoAvailableTranslationsBean<T> translations = document.getAvailableTranslations();
            result = translations.getTranslation("nl");
        }
        if (result == null) {
            result = document;
        }
        return result;
    }

    public ServletContext getServletContext() {
        return getRequestContext().getServletContext();
    }

    public HippoBean getContentRoot() {
        return getRequestContext().getSiteContentBaseBean();
    }

    public Mount getResolvedMount() {
        return getRequestContext().getResolvedMount().getMount();
    }

    public WorkflowPersistenceManager getWorkflowPersistenceManager(Session session, Map<String, ContentNodeBinder> contentNodeBinders) {
        WorkflowPersistenceManagerImpl wpm = new WorkflowPersistenceManagerImpl(session, RequestContextProvider.get().getContentBeansTool().getObjectConverter(), contentNodeBinders);
        return wpm;
    }

    public WorkflowPersistenceManager getWorkflowPersistenceManager(Session session) {
        return this.getWorkflowPersistenceManager(session, (Map)null);
    }

}
