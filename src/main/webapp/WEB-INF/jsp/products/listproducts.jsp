<%@page import="java.util.ArrayList"%>
<%@page import="com.stg.demo.model.Products"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
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
		</div>
		<!-- Sản phẩm -->
		<div class="small-container">
			<h2 class="pro-title" style="margin: 0 auto 20px;">
				<s:message code="lo.index.protitle" />
			</h2>
			<div class="box-container"
				style="min-height: 5vh; width: 180px; margin: 10px;">
				<h4 style="white-space: nowrap;">
					<s:message code="lo.index.sort" />
				</h4>
				<select id="select-category" onchange="onSelectCategory(event)"
					style="margin-left: 10px; margin-top: 3px; display: block; padding: 5px; border: 1px solid #ff1464; font-weight: 600; font-size: 16px; border-radius: 7px; cursor: pointer;">
					<option value=0><s:message code="lo.index.all" /></option>
					<c:forEach items="${categories}" var="category">
						<option value="${category.id}">${category.name}</option>
					</c:forEach>

				</select>
			</div>
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
							đ
						</p>
						<p>
							Ngày đăng:
							<fmt:formatDate pattern="dd/MM/yyyy" value="${pro.getCreated()}" />
						</p>
						<p
							style="overflow: hidden; text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;">*${pro.description }</p>
						<a class="btn-content" href="/them-vao-gio/${pro.getId()}"><s:message
								code="lo.index.buy" /></a>
					</div>
				</c:forEach>
			</div>
			<div class="page-btn" style="margin: 0 auto; text-align: center;">
				<c:if test="${maxPage > 1}">
					<c:forEach var="pageIndex" begin="0" end="${maxPage - 1}">
						<a href="/products?page=${pageIndex }&categoryID=${categoryID}"
							class='${pageIndex == page ? "trang active" : "trang"}'>
							${pageIndex + 1} </a>
					</c:forEach>
				</c:if>
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
						<button class="btn-content">
							<s:message code="lo.index.buy" />
						</button>
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
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		onSelectLoaded();
		function onSelectCategory(event) {
			let select = event.target;
			let value = select.value;

			let aTag = document.createElement("a");
			aTag.href = "/products?categoryID=" + value;

			document.body.appendChild(aTag);
			aTag.click();
		}

		function onSelectLoaded() {
			let select = document.getElementById("select-category");
			let s_url = window.location.href;
			let id = 0;

			s_url = s_url.substring(s_url.indexOf("?"), s_url.length);
			let params = new URLSearchParams(s_url);

			if (params.has("categoryID")) {
				id = params.get("categoryID");
				for (let i = 0; i < select.length; i++) {
					const option = select[i];
					if (option.value == id) {
						select.selectedIndex = i;
					}
				}
			}
		}
	</script>
</body>
</html>