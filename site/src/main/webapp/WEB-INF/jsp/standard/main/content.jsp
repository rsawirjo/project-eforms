<%--
  Copyright 2013-2018 Hippo B.V. (http://www.onehippo.com)
--%>
<%@ include file="/WEB-INF/jspf/htmlTags.jspf" %>
<%--@elvariable id="document" type="com.onehippo.cms7.eforms.demo.beans.TextDocument"--%>

<c:choose>
  <c:when test="${empty document}">
    <tag:pagenotfound/>
  </c:when>
  <c:otherwise>

    <c:if test="${not empty document.title}">
      <hst:element var="headTitle" name="title">
        <c:out value="${document.title}"/>
      </hst:element>
      <hst:headContribution keyHint="headTitle" element="${headTitle}"/>
    </c:if>

    <article class="well well-large">
      <hst:manageContent hippobean="${document}"/>
      <header>
        <h2>${fn:escapeXml(document.title)}</h2>
        <p>${fn:escapeXml(document.summary)}</p>
      </header>
      <hst:html hippohtml="${document.html}"/>
    </article>

  </c:otherwise>
</c:choose>