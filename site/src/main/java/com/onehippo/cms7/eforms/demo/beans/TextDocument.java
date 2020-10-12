/*
 * Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.eforms.demo.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@Node(jcrType="hippoaddoneformsdemo:textdocument")
public class TextDocument extends BaseDocument{
    
    public String getTitle() {
        return getSingleProperty("hippoaddoneformsdemo:title");
    }

    public String getSummary() {
        return getSingleProperty("hippoaddoneformsdemo:summary");
    }
    
    public HippoHtml getHtml(){
        return getHippoHtml("hippoaddoneformsdemo:body");    
    }

}
