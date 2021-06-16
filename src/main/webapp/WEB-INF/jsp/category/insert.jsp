<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thêm danh mục</title>
</head>
<body>
	<h1>Thêm danh mục</h1>
	<form:form action="#" method="post" modelAttribute="category">
		<form:input path="categoryID" type="hidden" />
		<br />
		<form:label path="name">Tên</form:label>
		<form:input path="name"></form:input>
		<form:errors path="name" style="color: red;"></form:errors>
		<br />
		<input type="submit" value="Gửi" />
	</form:form>
</body>
</html>