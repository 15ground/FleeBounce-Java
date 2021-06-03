<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Danh sách sản phẩm</title>
</head>
<body>
	<h1>Danh sách sản phẩm</h1>
	<form action="search">
		<input type="text" name="name" /> <input type="submit"
			value="Tìm kiếm" />
	</form>
	<a href="/products/insert">Thêm sản phẩm</a>
	<table>
		<thead>
			<tr>
				<th><a href="/products/see?order=id"> Id </a></th>
				<th><a href="/products/see?order=name"> Tên </a></th>
				<th><a href="/products/see?order=price"> Giá </a></th>
				<th>Ngày tạo</th>
				<th>Danh mục</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td>${product.created}</td>
					<td>${product.category.name}</td>
					<td><a href="/products/edit?id=${product.id}">Sửa</a></td>
					<td><a href="/products/delete?id=${product.id}">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	Trang :
	<c:forEach var="pageIndex" begin="0" end="${maxPage}">
		<a href="/products/page?pageIndex=${pageIndex}">${pageIndex + 1}</a>
	</c:forEach>

</body>
</html>