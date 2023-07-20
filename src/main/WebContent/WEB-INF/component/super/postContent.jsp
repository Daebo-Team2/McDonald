<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/super/post.css" rel="stylesheet" />

<div class="title">
  <h1>문의게시판</h1>
</div>
<div class="wrapper">
  <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">

    <li class="nav-item" role="presentation">
      <button
      class='nav-link  ${status == 0 ? "active red-btn " : "red"} '
      type="button"
      data-bs-toggle="pill"
      role="tab"
      aria-selected="true"
      onclick="tabBtnClick(0)"
      >
        문의 대기
      </button>
    </li>

    <li class="nav-item" role="presentation">
      <button
      class='nav-link ${status == 1 ? "active green-btn" : "green"}'
      type="button"
      data-bs-toggle="pill"
      role="tab"
      aria-selected="false"
      onclick="tabBtnClick(1)"
      >
        답변 완료
      </button>
    </li>
  </ul>
  <table class="table" page="post">
    <thead>
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>가맹점</th>
        <th>작성시간</th>
        <th></th>
      </tr>
    </thead>   
    <tbody>
    <c:forEach items="${list}" var="post">
      <tr onclick="openViewModal(${post.no})">
        <td>${post.no}</td>
        <td>${post.title}</td>
        <td>${post.storename}</td>
        <td>${post.time}</td>
        <td>
        	<c:if test="${post.reno == 0}">
   				<span class="yellow">답변대기</span>
   			</c:if>
   			<c:if test="${post.reno != 0}">
   				<span class="green">답변완료</span>
   			</c:if>
        </td>
      </tr>
    
    </c:forEach>
    </tbody>
  </table>
</div>
<div class="footer">
  <nav>
    <ul class="pagination">
    <c:if test = "${pageStart != 1 }">
    	<li class="page-item">
          <a class="page-link green" onclick="pageMove('/super/postContent.do?pageNo=${pageStart - 1}&status=${status}')">
          	<span aria-hidden="true">&laquo;</span>
          </a>
      	</li>
    </c:if>
	<c:forEach var = "i" begin ="${pageStart}" end = "${pageEnd}">
		<li class="page-item ${i==pageCurrent ? 'active' : '' }">
		<a class="page-link ${i==pageCurrent ? 'green-btn' : 'green' }" onclick="pageMove('/super/postContent.do?pageNo=${i}&status=${status}')" >${i}</a></li>	
	</c:forEach>
    <c:if test = "${pageEnd  != totalPage }">
    	<li class="page-item">
        	<a class="page-link green" onclick="pageMove('/super/postContent.do?pageNo=${pageEnd + 1}&status=${status}')">
          	  <span aria-hidden="true">&raquo;</span>
        	</a>
      	</li>
    </c:if>
    </ul>
  </nav>
</div>
<!-- 모달 숨겨놓기-->
<div
class="modal fade"
id="post-modal"
data-bs-backdrop="static"
tabindex="-1"
aria-hidden="true"
>
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content" id="modal-content">
    </div>
  </div>
</div>
<script>
  postViewModal.init("div#post-modal");
</script>
