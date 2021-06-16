<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/style.css">
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" href="/css/dashboardst.css">
<title>Sản phẩm</title>
</head>
<body>
	<div class="sidebar">
		<div class="logo-details">
			<i class='bx bxl-c-plus-plus'></i> <span class="logo_name">De
				5</span>
		</div>
		<ul class="nav-links">
			<li><a href="/dashboard"> <i class='bx bx-grid-alt'></i> <span
					class="links_name">Dashboard</span>
			</a></li>
			<li><a href="/products/list" class="active"> <i class='bx bx-box'></i>
					<span class="links_name">Sản phẩm</span>
			</a></li>
			<li><a href="/category/list"> <i class='bx bx-list-ul'></i>
					<span class="links_name">Danh mục</span>
			</a></li>
			<li><a href="#"> <i class='bx bx-pie-chart-alt-2'></i> <span
					class="links_name">Thống kê</span>
			</a></li>
			<li><a href="#"> <i class='bx bx-message'></i> <span
					class="links_name">Tin nhắn</span>
			</a></li>
			<li><a href="#"> <i class='bx bx-heart'></i> <span
					class="links_name">Yêu thích</span>
			</a></li>
			<li><a href="#"> <i class='bx bx-cog'></i> <span
					class="links_name">Cài đặt</span>
			</a></li>
			<li class="log_out"><a href="#"> <i class='bx bx-log-out'></i>
					<span class="links_name">Đăng xuất</span>
			</a></li>
		</ul>
	</div>
	<section class="home-section">
		<h1
			style="font-size: 20px; margin: 20px auto; width: 300px; font-weight: 900;">DANH
			SÁCH SẢN PHẨM</h1>
		<form action="list">
			<div class="search-box"
				style="width: 250px; float: right; margin: 0px 15px">
				<input
					style="height: 40px; width: 250px; outline: none; border: 2px solid #EFEEF1; border-radius: 6px; font-size: 18px; padding: 0 15px;"
					type="text" name="search" value="${search}"
					placeholder="Tìm kiếm...">
			</div>
		</form>
		<div class="button-box"
			style="width: 250px; float: left; margin: 0px -24px">
			<span class="action_btn"><a href="/products/insert">Thêm
					sản phẩm</a> </span>
		</div>
		<div class="table_responsive">
			<table>
				<thead>
					<tr>
						<th><a href="/products/sort?order=id"
							style="text-decoration: none; color: white;">ID</a></th>
						<th><a href="/products/sort?order=name"
							style="text-decoration: none; color: white;">Tên</a></th>
						<th><a href="/products/sort?order=price"
							style="text-decoration: none; color: white;">Giá bán</a></th>
						<th>Ngày tạo</th>
						<th>Danh mục</th>
						<th>Mô tả</th>
						<th>Hành động</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="product" items="${products}">
						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="1"
									value="${product.price}" /></td>
							<td><fmt:formatDate pattern="dd/MM/yyyy"
									value="${product.created}" /></td>
							<td>${product.category.name}</td>
							<td>${product.description}</td>
							<td><span class="action_btn"> <a
									href="/products/edit?id=${product.id}">Sửa</a>
									<p onclick="confirmDelete(${product.id})">Xóa</p>
							</span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="page-btn" style="margin-top: 20px; margin-left: 600px;">
			<c:if test="${maxPage > 1 }">
				<c:forEach var="pageIndex" begin="0" end="${(maxPage - 1)}">
					<a style="text-decoration: none; color: black;" class="page-link"
						href="/products/list?pageIndex=${pageIndex}&search=${search}">
						${pageIndex+1} </a>
				</c:forEach>
			</c:if>
		</div>
	</section>
	<script>
	 confirmDelete = (id) =>{
	    let ok = confirm("Xác nhận xóa?");
	    if (ok == true) {

	        let aTag = document.createElement("a");
	        aTag.href = "/products/delete?id=" + id;
	        document.body.append(aTag);
	        aTag.click();
	        
	    }
	}
	</script>
</body>
</html>