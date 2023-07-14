<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-header">
	<h3 class="modal-title" id="staticBackdropLabel">문의 내역 조회</h3>
	<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
</div>
<div class="modal-body">
	<h3>문의 내용</h3>
	<h5>제목</h5>
	<p>${post.title}</p>
	<h5>내용</h5>
	<p class="post-content">${post.content}	</p>
	<c:if test="${requestScope.reply != null}">
			<hr>
			<h3>답변</h3>
			<h5>제목</h5>
			<p>${reply.title}</p>
			<h5>내용</h5>
			<p class="post-content">${reply.content} </p>
	</c:if>

</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
</div>