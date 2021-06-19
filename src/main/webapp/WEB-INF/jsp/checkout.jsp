<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="/css/form.css">
<title>Xác nhận đơn hàng</title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/newheader.jsp"></c:import>
	<h2 class="pro-title" style="margin-top: 90px;">Vui lòng xác nhận
		đơn hàng của bạn</h2>
	<form:form class="form-add" action="check-out" method="post"
		modelAttribute="customer" style="margin-top: -50px;">
		<form:input path="id" type="hidden" />
		<br />
		<form:label path="name">Họ và tên:</form:label>
		<form:input path="name" placeholder="Họ và tên..."></form:input>
		<form:errors path="name"
			cssStyle="color:red;margin-left: 10px;
    font-style: italic;"></form:errors>
		<br />
		<form:label path="phoneNumber">Số điện thoại:</form:label>
		<form:input path="phoneNumber" placeholder="Số điện thoại..."></form:input>
		<form:errors path="phoneNumber"
			cssStyle="color:red;margin-left: 10px;
    font-style: italic;"></form:errors>
		<br />
		<form:label path="address">Địa chỉ:</form:label>
		<form:input path="address" placeholder="Địa chỉ..."></form:input>
		<form:errors path="address"
			cssStyle="color:red;margin-left: 10px;
    font-style: italic;"></form:errors>
		<br />
		<form:label path="email">Email:</form:label>
		<form:input path="email" placeholder="Email..."></form:input>
		<form:errors path="email"
			cssStyle="color:red;margin-left: 10px;
    font-style: italic;"></form:errors>
		<br />
		<input class="btn-add" style="width: 200px; margin: 0 auto;"
			type="submit" value="Xác nhận" />
	</form:form>
	<%-- <form action="/check-out-successfully" method="post">
		Email : <input type="text" name="email"> <input type="submit"
			value="hoàn thành" />
	</form> --%>
	<c:import url="/WEB-INF/jsp/footer.jsp"></c:import>
</body>
</html>