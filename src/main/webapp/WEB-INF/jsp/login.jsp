<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/sigup.css" />
<title>Đăng nhập</title>
</head>
<body>
	<div class="login">
		<form:form action="/login?cartStatus=${cartStatus}" method="POST"
			modelAttribute="login">

			<h2>ĐĂNG NHẬP</h2>
			<div class="group" style="margin-bottom: -20px;">
				<form:input type="text" value="${login.getPhoneNumber() }"
					path="phoneNumber" name="phoneNumber"
					placeholder="Số điện thoại..." />
				<br />
				<form:input type="text" path="passWord" name="passWord"
					placeholder="Mật khẩu..." />
				<p style="color: red; margin-top: 10px;">${message }</p>
				<br />
			</div>
			<input class="btn-add" style="width: 200px; margin: 0 auto 10px;"
				type="submit" value="Đăng nhập" />
			<p class="fs" style="margin-bottom: 10px;">
				Bạn đã quên <a href="#">Tài khoản</a> / <a href="#">Mật khẩu</a> ?
			</p>
			<p class="ss">
				Bạn chưa có tài khoản? <a href="/register?cartStatus=${cartStatus }">Đăng
					ký</a>
			</p>
		</form:form>
	</div>
</body>
</html>