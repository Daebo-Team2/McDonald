<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<div class="header">
  <ul class="nav nav-tabs">
    <li class="nav-item">
      <img src="${pageContext.request.contextPath}/image/logo2.png" width="80" height="70" />
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="pageMove('${pageContext.request.contextPath}/super/storeContent.do')">가맹점관리</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="pageMove('${pageContext.request.contextPath}/super/saleContent.do')">매출관리</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="pageMove('${pageContext.request.contextPath}/super/menuContent.do')">메뉴관리</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="pageMove('${pageContext.request.contextPath}/super/stockContent.do')">재고관리</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="pageMove('${pageContext.request.contextPath}/super/postContent.do')">문의게시판</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="location.href='${pageContext.request.contextPath}/auth/logout.do'">Logout</button>
    </li>
  </ul>
</div>