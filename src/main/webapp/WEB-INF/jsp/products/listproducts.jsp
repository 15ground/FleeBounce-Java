<%@page import="java.util.ArrayList"%>
<%@page import="com.stg.demo.model.Products"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/products.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<title>FLEEBOUNCE | LIVE YOUR LIFE</title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/header.jsp"></c:import>
	<section>
		<!-- Danh mục sản phẩm -->
		<div class="categories">
			<div class="small-container">
				<div class="content">
					<div class="cate-content">
						<img src="/images/category-1.jpg" />
					</div>
					<div class="cate-content">
						<img src="/images/category-2.jpg" />
					</div>
					<div class="cate-content">
						<img src="/images/category-3.jpg" />
					</div>
				</div>
			</div>
			<div class="box-container">
				<div class="box">
					<h2>Danh mục</h2>
					<c:forEach items="${categories}" var="category">
						<ul>
							<li><span>*</span><a
								href="/products/home?danhmuc=${category.getCategoryID()}"
								class="list-group-item list-group-item-action">${category.getName()}</a></li>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>
		<!-- Sản phẩm -->
		<div class="small-container">
			<h2 class="pro-title">Sản phẩm mới</h2>
			<div class="content">
				<c:forEach items="${products}" var="pro">
					<div class="pro-content">
						<a href="/products/details?id=${pro.id}"> <img
							src="${pro.getImages()}" />
						</a>
						<h4>${pro.getName()}</h4>
						<div class="rating">
							<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star-o"></i>
						</div>
						<p style="font-weight: 700; color: #ff2435; font-size: 18px;">
							<fmt:formatNumber type="number" maxFractionDigits="1"
								value="${pro.getPrice()}" />
							vnd
						</p>
						<p>
							Ngày đăng:
							<fmt:formatDate pattern="dd/MM/yyyy" value="${pro.getCreated()}" />
						</p>
						<p>*${pro.getDescription()}</p>
						<a class="btn-content" href="/them-vao-gio/${pro.getId()}">Mua
							ngay</a>
					</div>
				</c:forEach>
			</div>
			<div class="page-btn">
				<span>&#8592</span>
				<c:forEach var="i" begin="1" end="5">
					<span${i == ItemOfPage ? "ItemOfPage" : ""  }"><a
						class="page-link" href="products?page=${i}">${i}</a></span>
				</c:forEach>
				<span>&#8594</span>
			</div>
		</div>
		<div class="offer">
			<div class="small-container">
				<div class="content">
					<div class="text-content">
						<img src="/images/exclusive.png" class="offer-img" />
					</div>
					<div class="text-content">
						<p>Exclusively Available on FleeBounce</p>
						<h1>Mi Band 4</h1>
						<small>The Mi Band 4 features a 39.9% larger than Mi Band
							3 AMOLED color full-touch display with adjustable brightness, so
							everything is clear as can be.</small><br />
						<button class="btn-content">Mua ngay</button>
					</div>
				</div>
			</div>
		</div>
		<div class="brands">
			<div class="small-container">
				<div class="content">
					<div class="icon-brand">
						<img src="/images/logo-godrej.png" />
					</div>
					<div class="icon-brand">
						<img src="/images/logo-coca-cola.png" />
					</div>
					<div class="icon-brand">
						<img src="/images/logo-oppo.png" />
					</div>
					<div class="icon-brand">
						<img src="/images/logo-paypal.png" />
					</div>
					<div class="icon-brand">
						<img src="/images/logo-philips.png" />
					</div>
				</div>
			</div>
		</div>
	</section>
	<c:import url="/WEB-INF/jsp/footer.jsp"></c:import>
</body>
</html>