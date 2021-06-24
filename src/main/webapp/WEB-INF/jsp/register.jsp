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
<title>Đăng ký</title>
</head>
<body>
	<div class="login">
		<form:form action="/register?cartStatus=${cartStatus}" method="POST"
			modelAttribute="customer">

			<h2>ĐĂNG KÝ</h2>
			<div class="group">
				<form:input type="hidden" path="id" />
				<form:input type="text" path="name" name="name"
					placeholder="Họ và tên..." />
				<form:errors path="name"
					cssStyle="color:red;margin-left: 10px;font-style: italic;"></form:errors>
				<br />
				<form:input type="text" path="phoneNumber" name="phoneNumber"
					placeholder="Số điện thoại..." />
				<form:errors path="phoneNumber"
					cssStyle="color:red;margin-left: 10px;font-style: italic;"></form:errors>
				<br />
				<form:input type="password" path="password" name="password"
					placeholder="Mật khẩu..." />
				<form:errors path="password"
					cssStyle="color:red;margin-left: 10px;font-style: italic;"></form:errors>
				<br />
				<form:input type="email" path="email" name="email"
					placeholder="Địa chỉ email..." />
				<form:errors path="email"
					cssStyle="color:red;margin-left: 10px;font-style: italic;"></form:errors>
				<br />
				<form:input type="text" path="address" name="address"
					placeholder="Địa chỉ..." />
				<form:errors path="address"
					cssStyle="color:red;margin-left: 10px;font-style: italic;"></form:errors>
				<br />
			</div>
			<input class="btn-add" style="width: 200px; margin: 0 auto;"
				type="submit" value="Đăng ký" />
			<br />
			<a href="/login?cartStatus=${cartStatus}" class="sig-up">Đăng
				nhập</a> / <a href="#" class="forgot-password">Quên mật khẩu</a>
		</form:form>
	</div>
</body>
</html>