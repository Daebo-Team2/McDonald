<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/super/menu.css" rel="stylesheet" />
<div class="title">
  <h1>메뉴관리</h1>
</div>

<div class="wrapper">
  <table class="table" page="menu">
    <thead>
      <tr>
        <th>제품번호</th>
        <th>이미지</th>
        <th>분류</th>
        <th>이름</th>
        <th>가격</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="menulist" items="${ list }">
    <tr>
        <td>${ menulist.no }</td>
        <td><img src="${ menulist.image }" width="50" height="50" /></td>
        <td>${ menulist.category }</td>
        <td>${ menulist.name }</td>
        <td>${ menulist.price }₩</td>
        <td>
          <button class="btn btn-secondary btn-sm" onclick="menuViewBtnHandler(${ menulist.no })">
            상세정보
          </button>
          <button class="btn btn-danger btn-sm" onclick="menuDelBtnHandler(${ menulist.no })">
            삭제
          </button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<div class="footer">
  <div class="empty"></div>
  <nav>
    <ul class="pagination">
      <c:if test="${pStart != 1}">
        <li class="page-item">
          <a class="page-link green" onclick="pageMove('/super/menuContent.do?pageNo=${pStart - 1}')">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
      </c:if>
      <c:forEach var="i" begin="${pStart}" end="${pEnd}">
        <li class="page-item ${i==pageNo ? 'active' : '' }">
          <a class="page-link ${i==pageNo ? 'green-btn' : 'green' }" onclick="pageMove('/super/menuContent.do?pageNo=${i}')" >${i}</a>
        </li>
      </c:forEach>
      <c:if test = "${pEnd  != totalPage }">
        <li class="page-item">
          <a class="page-link green" onclick="pageMove('/super/menuContent.do?pageNo=${pEnd + 1}')">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </c:if>
    </ul>
  </nav>
  <button class="btn btn-primary orange-btn" data-bs-toggle="modal" data-bs-target="#menu-add-modal">
    메뉴 추가
  </button>
  <!-- <button class="btn btn-primary" onclick="">가맹점추가</button> -->
</div>
<!-- 메뉴 상세보기 모달 -->
<div class="modal fade" id="menu-detail-modal" data-bs-backdrop="static"
tabindex="-1" aria-hidden="true" >
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content" id="menu-detail-modal-content">
    </div>
  </div>
</div>

<!-- 메뉴추가 모달 -->
<div
class="modal fade"
id="menu-add-modal"
data-bs-backdrop="static"
tabindex="-1"
aria-hidden="true"
>
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content" id="menu-add-modal-content">
      <div class="modal-header">
        <h5 class="modal-title">메뉴 추가</h5>
        <button
        type="button"
        class="btn-close"
        data-bs-dismiss="modal"
        aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">
        <label class="form-label">이미지</label><br />
        <img src="${pageContext.request.contextPath}/image/noimage.svg" width="450" height="400" id="preview"/><br /><br />
        <input
        type="file"
        id="file-form"
        class="form-control"
        onchange="fileChangeHandler()"
        />
        <label class="form-label">메뉴명</label>
        <input type="text" class="form-control" id="menuname"/>
        <label class="form-label">분류</label>
        <select class="form-select" aria-label="재료선택" id="category">
                  <option value = "0" selected>분류선택</option>
                  <option value="세트">세트</option>
                  <option value="버거">버거</option>
                  <option value="사이드">사이드</option>
                  <option value="디저트">디저트</option>
                  <option value="음료/카페">음료/카페</option>
                </select>
        <label class="form-label">가격</label>
        <input type="text" class="form-control" id="price" onkeyup="checkNum(event)"/>
        <label class="form-label">재료</label>
        <div id="recipe">
        <c:forEach var="foods" items="${ foodlist }" >
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="${ foods.no }" />
            <label class="form-check-label" for="${foods.no}">${ foods.name }</label>
          </div>
          </c:forEach>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary green-btn" onclick="menuAddBtnHandler(event)">추가</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<script>
  menuViewModal.init("div#menu-detail-modal")
</script>
