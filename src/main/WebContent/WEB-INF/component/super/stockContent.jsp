<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/super/stock.css" rel="stylesheet" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="title">
  <h1>재고주문관리</h1>
</div>
<div class="wrapper">

  <c:forEach var="stockOrder" items="${list}">
    <div class="accordion-item">
      <h2 class="accordion-header">
        <div
        class="accordion-button collapsed"
        data-bs-toggle="collapse"
        data-bs-target="#stockorder${stockOrder.no}"
        aria-expanded="false"
        >
          <div class="order-info">
            <span>주문번호: ${stockOrder.no}</span>
            <span>가맹점: ${stockOrder.storename}</span>
            <span>주문시간: ${stockOrder.time}</span>
          </div>
        </div>
      </h2>
      <div id="stockorder${stockOrder.no}" class="accordion-collapse collapse">
        <div class="accordion-body">
          <table class="table" page="stock">
            <thead>
              <tr>
                <th>재고명</th>
                <th>수량</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="stock" items="${stockOrder.list}">
                <tr>
                  <td>${stock.foodname}</td>
                  <td>${stock.quantity}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <button class="btn btn-primary" onclick="stockConfirmBtnHandler(${stockOrder.no})">발주</button>
        </div>
      </div>
    </div>
  </c:forEach>

</div>
<div class="footer">
  <nav>
    <ul class="pagination">
      <c:if test="${pageStart != 1}">
        <li class="page-item">
          <a class="page-link" onclick="pageMove('/super/stockContent.do?pageNo=${pageStart - 1}')">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
      </c:if>
      <c:forEach var="i" begin="${pageStart}" end="${pageEnd}">
        <li class="page-item ${i==pageCurrent ? 'active' : ''}"><a class="page-link" onclick="pageMove('/super/stockContent.do?pageNo=${i}')">${i}</a></li>
      </c:forEach>
      <c:if test="${pageEnd != totalPage}">
        <li class="page-item">
          <a class="page-link" onclick="pageMove('/super/stockContent.do?pageNo=${pageEnd+1}')">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </c:if>
    </ul>
  </nav>
</div>