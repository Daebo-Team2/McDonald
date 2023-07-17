<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/admin/sale.css" rel="stylesheet"/>

<div class="title">
    <h1>매출관리</h1>
    <h2>조회 매출액: ₩ <fmt:formatNumber value="${totalPrice}" pattern="###,###,###"/></h2>
    <div class="form-container">
        <div>
            <span>시작일자: </span>
            <input type="date" class="form-control form-control-sm" id="saleStartInput" value="${param.start}">
        </div>
        <div>
            <span>종료일자: </span>
            <input type="date" class="form-control form-control-sm" id="saleEndInput" value="${param.end}">
        </div>
        <div>
            <span>제품명: </span>
            <input type="text" class="form-control form-control-sm" id="saleMenuNameInput" placeholder="제품명 입력"
                   value="${param.menuName}">
        </div>
        <button class="btn btn-primary btn-sm" onclick="saleSearchBtnHandler()">검색</button>
    </div>
</div>
<div class="wrapper">
    <c:forEach var="sale" items="${list}">
        <div class="accordion-item">
            <h2 class="accordion-header">
                <div class="accordion-button collapsed" data-bs-toggle="collapse"
                     data-bs-target="#order${sale.no}"
                     aria-expanded="false">
                    <div class="order-info">
                        <span>주문번호: ${sale.no}</span>
                        <span>주문시간: <fmt:formatDate value="${sale.orderTime}" type="both"/></span>
                        <span>금액: ₩ <fmt:formatNumber value="${sale.price}" pattern="###,###,###"/></span>
                    </div>
                </div>
            </h2>
            <div id="order${sale.no}" class="accordion-collapse collapse">
                <div class="accordion-body">
                    <table class="table" page="sale">
                        <thead>
                        <tr>
                            <th>제품명</th>
                            <th>수량</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="menu" items="${sale.menuList}">
                            <tr>
                                <td>${menu.menuName}</td>
                                <td>${menu.quantity}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div class="footer">
    <c:if test="${pageStart != null && pageEnd != null}">
    <nav>
        <ul class="pagination">
            <c:if test="${pageStart != 1}">
                <li class="page-item">
                    <a class="page-link" onclick="pageMove('/admin/saleContent.do?start=${param.start}&end=${param.end}&menuName=${param.menuName}&pageNo=${pageStart - 1}')">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="i" begin="${pageStart}" end="${pageEnd}">
                <li class="page-item ${i==pageCurrent ? 'active' : ''}"><a class="page-link" onclick="pageMove('/admin/saleContent.do?start=${param.start}&end=${param.end}&menuName=${param.menuName}&pageNo=${i}')">${i}</a></li>
            </c:forEach>
            <c:if test="${pageEnd != totalPage}">
                <li class="page-item">
                    <a class="page-link" onclick="pageMove('/admin/saleContent.do?start=${param.start}&end=${param.end}&menuName=${param.menuName}&pageNo=${pageEnd+1}')">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
    </c:if>
</div>