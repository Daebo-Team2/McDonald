<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal-header">
    <h5 class="modal-title" id="staticBackdropLabel">직원수정</h5>
    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
</div>
<div class="modal-body" id="modal-body">
    <label class="form-label">이름</label>
    <input type="text" class="form-control" value="${emp.name}" id="empUpdateNameInput" readonly>
    <label class="form-label">시급</label>
    <input type="text" class="form-control" value="${emp.pay}" id="empUpdatePayInput">
    <label class="form-label">전화번호</label>
    <input type="text" class="form-control" value="${emp.tel}" id="empUpdateTelInput">
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" onclick="empUpdateBtnHandler(${emp.no})">수정하기</button>
    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
</div>
