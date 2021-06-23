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
<title>Insert title here</title>
</head>
<body>
	<div class="login">
		<form:form action="/login?cartStatus=${cartStatus}" method="POST"
			modelAttribute="login">

			<h2>ĐĂNG NHẬP</h2>
			<div class="group">
				<form:input type="text" value="${login.getPhoneNumber() }"
					path="phoneNumber" name="phoneNumber"
					placeholder="Số điện thoại..." />
				<form:errors path="phoneNumber" cssStyle="color:red"></form:errors>
				<br />
				<form:input type="text" path="passWord" name="passWord"
					placeholder="Mật khẩu..." />
				<form:errors path="passWord" cssStyle="color:red"></form:errors>
				<br />
			</div>
			<input class="btn-add" style="width: 200px; margin: 0 auto;"
				type="submit" value="Đăng nhập" />
				<a href="/register?cartStatus=${cartStatus }">Đăng ký</a>
		</form:form>
	</div>
</body>
</html>