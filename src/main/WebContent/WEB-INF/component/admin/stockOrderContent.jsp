<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/super/stock.css" rel="stylesheet"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result == 0}">
    <style>
        #deleteError {
            display: flex;
        }
    </style>
    <div id="deleteError">
    <h3>발주 취소가 불가합니다. 본사에 문의하세요!</h3>
    </div>
</c:if>
<c:if test="${result != 0}">
<div class="title">
    <h1>재고 주문 목록</h1>
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
                        <span>주문시간: ${stockOrder.time}</span>
                        <c:if test="${stockOrder.status == 0}">
                            <span class="red">접수중</span>
                        </c:if>
                        <c:if test="${stockOrder.status != 0}">
                            <span class="green">완료</span>
                        </c:if>
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
                    <button class="btn btn-primary red-btn"
                            onclick="stockOrderListDeleteBtnHandler(${stockOrder.no})">발주취소
                    </button>
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
                    <a class="page-link" onclick="pageMove('/admin/stockorderlist.do?pageNo=${pageStart - 1}')">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="i" begin="${pageStart}" end="${pageEnd}">
                <li class="page-item ${i==pageCurrent ? 'active' : ''}">
                <a class="page-link  ${i==pageCurrent ? 'green-btn' : 'green'}" onclick="pageMove('/admin/stockorderlist.do?pageNo=${i}')">${i}</a>
                </li>
            </c:forEach>
            <c:if test="${pageEnd != totalPage}">
                <li class="page-item">
                    <a class="page-link" onclick="pageMove('/admin/stockorderlist.do?pageNo=${pageEnd+1}')">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</c:if>
