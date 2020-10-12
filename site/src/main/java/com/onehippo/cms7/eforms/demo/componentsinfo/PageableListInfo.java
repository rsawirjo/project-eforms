/*
 * Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.eforms.demo.componentsinfo;

import org.hippoecm.hst.core.parameters.Parameter;

public interface PageableListInfo extends GeneralListInfo {

    @Parameter(name = "pagesVisible", defaultValue="true", displayName = "Show pages")
    Boolean isPagesVisible();

}
