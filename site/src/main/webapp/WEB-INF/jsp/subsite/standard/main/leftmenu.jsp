<%--
  Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
--%>
<%@ include file="/WEB-INF/jspf/htmlTags.jspf" %>
<%--@elvariable id="menu" type="org.hippoecm.hst.core.sitemenu.HstSiteMenu"--%>

<c:if test="${not empty menu.siteMenuItems}">
  <ul class="nav nav-pills nav-stacked">
    <c:forEach var="item" items="${menu.siteMenuItems}">
      <tag:menuitem siteMenuItem="${item}"/>
    </c:forEach>
  </ul>
</c:if>