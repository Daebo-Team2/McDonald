<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>kiosktouch</title>
		<link href="${pageContext.request.contextPath}/css/kiosktouch.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="di">
			<img src="${pageContext.request.contextPath}/image/logo2.png" style="width : 27%" />
			<h1 class="h">${sessionScope.login.name}</h1>
			<img src="${pageContext.request.contextPath}/image/logo2.png" style="width : 27%" />
		</div>

		<br /><br />

		<div class="bt">
			<button type="button" class="btn btn-primary btn-lg">주문하기 <br />화면을 터치하세요</button>
		</div>
	</body>
</html>