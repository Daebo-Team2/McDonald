<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <div class="modal-header">
        <h5 class="modal-title">${ name }</h5>
        <button
        type="button"
        class="btn-close"
        data-bs-dismiss="modal"
        aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">
        <label class="form-label">이미지</label>
        <img src="${image}" width="450" height="400" />
        <label class="form-label">분류</label>
        <p>${ category }</p>
        <label class="form-label">가격</label>
        <p>${ price }₩</p>
        <label class="form-label">재료</label>
        <c:forEach var="recipe" items="${ recipelist }">
        <li>${ recipe.foodname }</li>
        </c:forEach>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary red-btn" data-bs-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>
<!-- <script>
 menuViewBtnHandler.init("div#menu-detail-modal-content")
</script> -->