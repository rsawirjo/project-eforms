/*
 * Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.eforms.demo.componentsinfo;

import org.hippoecm.hst.core.parameters.Parameter;

public interface SearchInfo extends PageableListInfo {
    
    @Override
    @Parameter(name = "title", displayName = "The title of the page", defaultValue="Search Result")
    String getTitle();
    
}
