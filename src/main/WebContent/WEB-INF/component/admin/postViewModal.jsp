<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-header">
	<h3 class="modal-title" id="staticBackdropLabel"><strong>문의 내역 조회</strong></h3>
	<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
</div>
<div class="modal-body">
	<h3><strong>문의 내용</strong></h3>
	<h5>제목</h5>
	<p class="post-title">${post.title}</p>
	<h5>내용</h5>
	<p class="post-content">${post.content}	</p>
	<c:if test="${requestScope.reply != null}">
			<hr>
		<h3><strong>답변</strong></h3>
			<h5>제목</h5>
			<p class="post-title">${reply.title}</p>
			<h5>내용</h5>
			<p class="post-content">${reply.content} </p>
	</c:if>

</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary green-btn" data-bs-dismiss="modal">확인</button>
</div>