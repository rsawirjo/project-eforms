<%--
  Copyright 2014 Hippo B.V. (http://www.onehippo.com)
  --%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<hst:element var="headTitle" name="title">
   <fmt:message key="page.not.found"/>
 </hst:element>
<hst:headContribution keyHint="headTitle" element="${headTitle}"/>
<h2><fmt:message key="page.not.found"/></h2>