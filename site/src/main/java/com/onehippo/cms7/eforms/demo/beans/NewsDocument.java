/*
 * Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.eforms.demo.beans;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSetBean;

@Node(jcrType="hippoaddoneformsdemo:newsdocument")
public class NewsDocument extends BaseDocument{

    public String getTitle() {
        return getSingleProperty("hippoaddoneformsdemo:title");
    }
    
    public String getSummary() {
        return getSingleProperty("hippoaddoneformsdemo:summary");
    }
    
    public Calendar getDate() {
        return getSingleProperty("hippoaddoneformsdemo:date");
    }

    public HippoHtml getHtml(){
        return getHippoHtml("hippoaddoneformsdemo:body");    
    }

    /**
     * Get the imageset of the newspage
     *
     * @return the imageset of the newspage
     */
    public HippoGalleryImageSetBean getImage() {
        return getLinkedBean("hippoaddoneformsdemo:image", HippoGalleryImageSetBean.class);
    }


}
