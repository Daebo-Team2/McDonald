<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<h2 style="font-family: 'Noto Sans KR', sans-serif;">출퇴근 관리</h2>
<table class="table inout-table">
  <c:forEach items="${list}" var="emp">
    <tr>
      <td><span class="emp-name">${emp.name}</span></td>
      <td>
        <c:if test="${emp.inTime == null}">
          <button type="button" class="btn btn-success" onclick="empInoutBtnHandler(${emp.no})">출근</button>
        </c:if>
        <c:if test="${emp.inTime != null}">
          <button type="button" class="btn btn-danger" onclick="empInoutBtnHandler(${emp.no})">퇴근</button>
        </c:if>
      </td>
    </tr>
  </c:forEach>
</table>
