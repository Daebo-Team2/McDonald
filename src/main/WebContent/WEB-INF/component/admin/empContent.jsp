<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/admin/emp.css" rel="stylesheet" />
<div class="title">
  <h1>직원관리</h1>
</div>
<div class="wrapper">
  <table class="table" page="emp">
    <thead>
      <tr>
        <th>사번</th>
        <th>이름</th>
        <th>입사일</th>
        <th>연락처</th>
        <th>근무시간</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="emp">
      <tr>
        <td>${emp.no}</td>
        <td>${emp.name}</td>
        <td>${emp.hireDate}</td>
        <td>${emp.tel}</td>
        <td>${emp.wTime}</td>
        <td>
          <button class="btn btn-secondary btn-sm" onclick="empUpdateModalOpener(${emp.no})">수정</button>
          <button class="btn btn-danger btn-sm">삭제</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<div class="footer">
  <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#emp-add-modal">추가</button>
</div>
<!-- 모달 숨겨놓기 -->
<!-- 업데이트 모달(서버로부터 모달 내용받아서 렌더링) -->
<div class="modal fade" id="emp-update-modal" data-bs-backdrop="static" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content" id="update-modal-content">
    </div>
  </div>
</div>

<!-- 추가 모달(항상 동일한 폼) -->
<div class="modal fade" id="emp-add-modal" data-bs-backdrop="static"tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content" id="add-modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">직원등록</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="modal-body">
        <label class="form-label">이름</label>
        <input type="text" class="form-control" id="empAddNameInput">
        <label class="form-label">시급</label>
        <input type="text" class="form-control" id="empAddPayInput">
        <label class="form-label">전화번호</label>
        <input type="text" class="form-control" id="empAddTelInput">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="empAddBtnHandler()">등록하기</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<script>
  empUpdateModal.init("div#emp-update-modal")
</script>