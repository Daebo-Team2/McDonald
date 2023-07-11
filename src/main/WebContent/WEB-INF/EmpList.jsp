<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <style>
        table {
            width: 50%;
            border-collapse: collapse;
        }

        th, td {
            border-bottom: 1px solid #dee2e6;
            padding: 10px;
            text-align: left;
        }

        thead tr {
            background-color: #FFC72C;
            color: #ffffff;
        }

        tbody tr:nth-child(2n) {
            background-color: #ffffff;
        }

        tbody tr:nth-child(2n+1) {
            background-color: #e5e5e6;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer>
        function trHandler(event) {
            const tr = event.target.parentElement;
            console.log(tr.firstElementChild.textContent)
            $.ajax({
                url: "../EmpSelect.do",
                type: "post",
                data: {
                    no: tr.querySelector("td:nth-child(2)").textContent,
                    storeName: tr.querySelector("td:nth-child(3)").textContent
                },
                dataType: "text",
                success: sucFunc,
                error: errFunc
            });
        }

        function sucFunc(d) {
            $('#empUpdate').html(d);
        }

        function errFunc(e) {
            alert("실패: " + e.status);
        }
    </script>
</head>
<body>
<h1>직원 내역</h1>
<div>
    <div>
        <div>
            <table id="empTable">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">사번</th>
                    <th scope="col">이름</th>
                    <th scope="col">소속 가맹점</th>
                    <th scope="col">입사일</th>
                    <th scope="col">연락처</th>
                    <th scope="col">시급</th>
                    <th scope="col">근무 시간</th>
                    <th scope="col">출근 시간</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="emp" items="${requestScope.list}" varStatus="row">
                    <tr onclick="trHandler(event)">
                        <th scope="row">${row.count}</th>
                        <td>${emp.no}</td>
                        <td>${emp.name}</td>
                        <td>${emp.storeNo}</td>
                        <td>${emp.hireDate}</td>
                        <td>${emp.tel}</td>
                        <td>${emp.pay}</td>
                        <td>${emp.wTime}</td>
                        <td>${emp.inTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div id="empUpdate">

</div>
</body>
</html>
