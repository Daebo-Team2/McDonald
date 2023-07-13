<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>본사 메인페이지</title>
    <%-- bootstrap css, js    --%>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      defer
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <%-- jQuery --%>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" defer integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
    <%-- 공통 css, js  --%>
    <script src="${pageContext.request.contextPath}/js/superCommon.js" defer></script>
    <link href="${pageContext.request.contextPath}/css/superCommon.css" rel="stylesheet" />
  </head>
  <body>
    <jsp:include page="/WEB-INF/component/super/superHeader.jsp" />
    <div class="section">
      <div id="content">
        <jsp:include page="/WEB-INF/component/super/storeContent.jsp" />
      </div>
    </div>

  </body>
</html>
