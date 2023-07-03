<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
    <h1>${msg}</h1>
    <div>
      <c:forEach items="${list}" var="item">
        <c:if test="${!item.endsWith('2')}">
          <h2>${item}</h2>
        </c:if>
      </c:forEach>
    </div>
  </body>
</html>
