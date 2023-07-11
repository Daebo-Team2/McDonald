<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>직원 수정</h2>
<form action="http://localhost:8080/EmpUpdate.do" method="post">
    사번 <input type="text" name="no" readonly value="${emp.no}"> <br />
    이름 <input type="text" name="no" readonly value="${emp.name}"> <br />
    소속 가맹점 <input type="text" name="storeName" readonly value="${emp.storeNo}"> <br />
    입사일 <input type="text" name="hireDate" readonly value="${emp.hireDate}"> <br />
    연락처 <input type="text" name="tel" value="${emp.tel}"> <br />
    시급 <input type="text" name="pay" value="${emp.pay}"> <br />
    근무 시간 <input type="text" name="wTime" readonly value="${emp.wTime}"> <br />
    출근 시간 <input type="text" name="inTime" readonly value="${emp.inTime}"> <br />
    <input type="submit" value="등록">
</form> <br />

