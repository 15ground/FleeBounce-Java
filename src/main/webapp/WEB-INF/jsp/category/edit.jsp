<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/form.css">
<title>Thêm danh mục</title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/newheader.jsp"></c:import>
	<div class="title" style="width: 250px; margin: 90px auto 0px;">
		<h1>Sửa danh mục</h1>
	</div>
	<form:form class="form-add" action="#" method="post"
		modelAttribute="category" style="min-height: 341px;">
		<form:input path="id" type="hidden" />
		<br />
		<form:label path="name">Tên danh mục:</form:label>
		<form:input path="name" placeholder="Tên danh mục..."></form:input>
		<form:errors path="name"
			cssStyle="color:red;margin-left: 10px;
    font-style: italic;"></form:errors>
		<br />
		<input class="btn-add" style="width: 200px; margin: 130px auto 0;"
			type="submit" value="Sửa" />
	</form:form>
	<c:import url="/WEB-INF/jsp/footer.jsp"></c:import>
</body>
</html>