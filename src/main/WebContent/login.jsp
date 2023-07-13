<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>

<link href="/css/login.css" rel="stylesheet">
<script defer src="login.js"></script>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

	<main class="form-signin w-100 m-auto">
		<form  action="/page/login" method="post" id="login-form">
			<div class="form-img">
				<img class="mb-4" src="logo1.png" alt="" width="200" height="150">
			</div>
			<!-- <h1 class="h3 mb-3 fw-normal">Please sign in</h1> -->

			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInput" name="id"
					placeholder="Id"> <label for="floatingInput"></label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword"
					name="password" placeholder="Password"> <label for="floatingPassword"></label>
			</div>

			<button class="btn btn-primary w-100 py-2" id="login-submit"type="submit">로그인</button>
		</form>
	</main>
</body>
</html>