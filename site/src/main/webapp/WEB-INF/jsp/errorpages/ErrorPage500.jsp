<!doctype html>
<%--
  Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
--%>
<%@ include file="/WEB-INF/jspf/htmlTags.jspf" %>
<%@ page isErrorPage="true" %>
<% response.setStatus(500); %>

<html lang="en">
  <head>
    <meta charset="utf-8"/>
    <title>500 error</title>
  </head>
  <body>
    <h1>Server error</h1>
    <% out.println("<!-- An unexcepted error occurred. The name of the exception is:"); %>
    <%= exception %>
    <% out.println("-->"); %>
  </body>
</html>