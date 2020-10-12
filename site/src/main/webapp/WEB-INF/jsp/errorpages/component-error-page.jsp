<%--
  Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
--%>
<%@ include file="/WEB-INF/jspf/htmlTags.jspf" %>
<%--@elvariable id="errorComponentWindow" type="org.hippoecm.hst.core.container.HstComponentWindow"--%>

<c:if test="${not empty errorComponentWindow.componentExceptions}">
  <ul>
    <c:forEach var="componentException" items="${errorComponentWindow.componentExceptions}">
      <li>${fn:escapeXml(componentException.message)}</li>
    </c:forEach>
  </ul>
</c:if>