<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Danh sách danh mục</title>
</head>
<body>
	<h1>Danh sách danh mục</h1>
	<a href="/category/insert">Thêm danh mục</a>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Tên</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="category" items="${categories}">
				<tr>
					<td>${category.categoryID}</td>
					<td>${category.name}</td>
					<td><a href="/category/edit?id=${category.categoryID}">Sửa</a></td>
					<td><a href="/category/delete?id=${category.categoryID}">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>