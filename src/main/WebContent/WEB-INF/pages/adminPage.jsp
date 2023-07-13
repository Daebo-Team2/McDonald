<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 메인페이지</title>
    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    crossorigin="anonymous"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" defer integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/adminCommon.js" defer></script>
    <link href="${pageContext.request.contextPath}/css/adminCommon.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/admin/order.css" rel="stylesheet" />
  </head>
  <body>
    <jsp:include page="/WEB-INF/component/admin/adminHeader.jsp" />
    <div class="section">
      <div class="side">
        <jsp:include page="/WEB-INF/component/admin/adminSide.jsp" />
      </div>
      <div id="content">
        <jsp:include page="/WEB-INF/component/admin/orderContent.jsp" />
      </div>
    </div>
  </body>
</html>
