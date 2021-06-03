<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thêm sản phẩm</title>
</head>
<body>
	<h1>Thêm sản phẩm</h1>
	<form:form action="#" method="post" modelAttribute="products">
		<form:input path="id" type="hidden" />
		<br />
		<form:label path="name">Tên</form:label>
		<form:input path="name"></form:input>
		<form:errors path="name" cssStyle="color:red"></form:errors>
		<br />
		<form:label path="price">Giá</form:label>
		<form:input path="price"></form:input>
		<form:errors path="price" cssStyle="color:red"></form:errors>
		<br />
		<form:label path="images">Hình ảnh</form:label>
		<form:input path="images"></form:input>
		<form:errors path="images" cssStyle="color:red"></form:errors>
		<br />
		<form:label path="category.categoryID">Danh mục</form:label>
		<form:select path="category.categoryID" itemValue="categoryID"
			itemLabel="name" items="${categories}" />
		<form:errors path="category" cssStyle="color:red"></form:errors>
		<br />
		<input type="submit" value="Gửi" />
	</form:form>
</body>
</html>