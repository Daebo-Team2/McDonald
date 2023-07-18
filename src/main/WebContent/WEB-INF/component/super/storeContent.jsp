<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/super/store.css" rel="stylesheet" />
<div class="title">
  <h1>가맹점관리</h1>
</div>

<div class="wrapper">
  <table class="table" page="store">
    <thead>
      <tr>
        <th>지점명</th>
        <th>점주</th>
        <th>전화번호</th>
        <th>개점일</th>
        <th>주소</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="store" items="${list}">
        <tr store-num="${store.no}">
          <td>${store.name}</td>
          <td>${store.owner}</td>
          <td>${store.tel}</td>
          <td>${store.openingday}</td>
          <td>${store.address}</td>
          <td>
            <button class="btn btn-danger btn-sm" onclick="storeDelBtnHandler(${store.no})">
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
    <c:if test="${ pageStart != 1 }">
      <li class="page-item">
        <a class="page-link" onclick="pageMove('/super/storeContent.do?pageNo=${ pageStart+1 }')">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
     </c:if>
     <c:forEach var="i" begin="${ pageStart }" end="${ pageEnd }" >
      <li class="page-item ${i==pageCurrent ? 'active' : '' }">
      	<a class="page-link ${i==pageCurrent ? 'green-btn ' : 'green'}" onclick="pageMove('/super/storeContent.do?pageNo=${i}')">${i}</a></li>
	</c:forEach>
	<c:if test="${ pageEnd != totalPage }">
      <li class="page-item">
        <a class="page-link" onclick="pageMove('/super/storeContent.do?pageNo=${ pageEnd+1 }')">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
     </c:if>
    </ul>
  </nav>
  <button class="btn btn-primary orange-btn" data-bs-toggle="modal" data-bs-target="#store-modal">
    가맹점 추가
  </button>
  <!-- <button class="btn btn-primary" onclick="">가맹점추가</button> -->
</div>
<!-- 모달 숨겨놓기-->
<div
class="modal fade"
id="store-modal"
data-bs-backdrop="static"
tabindex="-1"
aria-hidden="true"
>
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content" id="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">가맹점 추가</h5>
        <button
        type="button"
        class="btn-close"
        data-bs-dismiss="modal"
        aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">
        <label class="form-label">지점명</label>
        <input type="text" class="form-control" id="storeName" placeholder="OOO점"/>
        <label class="form-label">ID</label>
        <input type="text" class="form-control" id="storeId" placeholder="알파벳+숫자 5~18자"/>
        <label class="form-label">비밀번호</label>
        <input type="password" class="form-control" id="storePwd"/>
        <label class="form-label">점주명</label>
        <input type="text" class="form-control" id="storeOwner"/>
        <label class="form-label">전화번호</label>
        <input type="text" class="form-control" id="storeTel" placeholder="(00)000-0000-0000"/>
        <label class="form-label">주소</label>
        <input type="text" class="form-control" id="sample4_postcode" placeholder="우편번호">
		<input type="button" class="form-control" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
		<input type="text" class="form-control" id="sample4_roadAddress" placeholder="도로명주소">
		<input type="text" class="form-control" id="sample4_jibunAddress" placeholder="지번주소">
		<span id="guide" class="form-control" style="color:#999;display:none"></span>
		<input type="text" class="form-control" id="sample4_detailAddress" placeholder="상세주소">
		<input type="text" class="form-control" id="sample4_extraAddress" placeholder="참고항목">
        
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary green-btn" onclick="storeAddBtnHandler()">
          추가
        </button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>