<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
  <ul class="nav nav-tabs">
    <li class="nav-item">
      <img src="${pageContext.request.contextPath}/image/logo2.png" width="80" height="70" />
    </li>
    <li class="nav-item">
      <button class="nav-link">주문관리</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="pageMove('${pageContext.request.contextPath}/admin/saleContent.do')">매출관리</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="pageMove('${pageContext.request.contextPath}/admin/empContent.do')">직원관리</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="pageMove('${pageContext.request.contextPath}/admin/stockContent.do')">재고관리</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="pageMove('${pageContext.request.contextPath}/admin/postContent.do')">문의게시판</button>
    </li>
    <li class="nav-item">
      <button class="nav-link" onclick="location.href='${pageContext.request.contextPath}/auth/logout.do'">Logout</button>
    </li>
  </ul>
</div>