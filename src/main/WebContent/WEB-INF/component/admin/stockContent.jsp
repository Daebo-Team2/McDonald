<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/admin/stock.css" rel="stylesheet" />

<div class="title">
  <h1>재고관리</h1>
</div>
<div class="wrapper">
  <table class="table" page="stock">
    <thead>
      <tr>
        <th>재고명</th>
        <th>수량</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${list}" var="stock">
        <tr food-num="${stock.foodno}">
          <td>${stock.foodName}</td>
          <td>${stock.quantity}</td>
          <td>
            <button class="btn btn-secondary updateBtn" onclick="stockUpdateBtnHandler(event)">수정</button>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
<div class="footer">
  <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">발주주문</button>
</div>
<!-- 모달 숨겨놓기-->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">재고 발주 주문</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table class="table" page="stock-modal">
          <thead>
            <tr>
              <th>재료</th>
              <th>수량</th>
            </tr>
          </thead>
          <tbody>
            <tr class="stockOrder">
              <td>
                <select class="form-select" aria-label="재료선택">
                  <option value = "0" selected>재료선택</option>
                  <option value="1">빵</option>
                  <option value="2">소고기패티</option>
                  <option value="3">닭고기패티</option>
                  <option value="4">새우패티</option>
                  <option value="5">닭고기</option>
                  <option value="6">소스</option>
                  <option value="7">치즈</option>
                  <option value="8">양상추</option>
                  <option value="9">양파</option>
                  <option value="10">해쉬브라운</option>
                  <option value="11">감자튀김</option>
                  <option value="12">초코시럽</option>
                  <option value="13">딸기시럽</option>
                  <option value="14">바닐라시럽</option>
                  <option value="15">오레오</option>
                  <option value="16">아이스크림</option>
                  <option value="17">원두</option>
                  <option value="18">라떼시럽</option>
                  <option value="19">코카콜라</option>
                  <option value="20">코카콜라 제로</option>
                  <option value="21">스프라이트</option>
                  <option value="22">환타</option>
                  <option value="23">쉐이크</option>
                  <option value="24">오렌지 주스</option>
                  <option value="25">생수</option>
                  <option value="26">코울슬로</option>
                  <option value="27">과자콘</option>
                </select>
              </td>
              <td>
                <input class="form-control stock-cnt" type="number" placeholder="수량입력"/>
                <button class="btn btn-sm btn-danger stockDelBtn" onclick="delStockOrderBtnHandler(event)">X</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" id="addStock" onclick="addStockOrderBtnHandler()">항목추가</button>
        <button type="button" class="btn btn-primary" id="stockOrder" onclick="stockOrderBtnHandler()">주문하기</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

