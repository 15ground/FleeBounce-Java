<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<title>Trang chủ</title>
<style type="text/css">
div.gio-hang {
	width: 80%;
	min-height: 200px;
	position: absolute;
	top: 10px;
	left: 10%;
	background: #f1f1f1;
	display: none;
	border: 1px solid #000;
}

button.close {
	float: right;
}
</style>
</head>
<body>
	${lang}
	<header>
		<nav>
			<ul>
				<li><a href="/products"> <s:message code="lo.index.home" />
				</a></li>
				<li><a href="/products/list"> <s:message
							code="lo.index.products" />
				</a></li>
				<li><a href="/your-cart"> <s:message code="lo.index.card" />
				</a></li>
			</ul>
			<ul>
				<li><a
					href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">
						<s:message code="lo.index.en" />
				</a> <a
					href="${requestScope['javax.servlet.forward.request_uri']}?lang=vi">
						<s:message code="lo.index.vi" />
				</a></li>
			</ul>
		</nav>
	</header>
	<h1>${title}</h1>
	<ul>
		<c:forEach var="category" items="${categories}">
			<li><a href="danh-muc/${category.categoryId}">
					${category.name} </a> <a
				href="danh-muc?categoryId=${category.categoryId}">
					${category.name} </a></li>
		</c:forEach>
	</ul>
	<footer>
		${footerInfo.companyName} <br /> Địa chỉ : ${footerInfo.address} <br />
		email : ${footerInfo.email} <br /> Số điện thoại:
		${footerInfo.phoneNumber} <br />
	</footer>
	<h1>${title}</h1>
	<button class="mo-gio-hang">Mở giỏ hàng</button>

	<div class="gio-hang">
		<button class="close">đóng</button>
		<div id="table-content"></div>
	</div>
	<a href="hoan-thanh-dat-hang">Thanh toán</a>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script>
      		$('button.close').click(function () {
      			$(this).parent().css("display", "none");
      		});
      		$('button.mo-gio-hang').click(function() {
      			$('div.gio-hang').css("display", "block");
      			fetch('/cart')
      			.then(response => response.text())
      			.then(data => {
      				$('#table-content').html(data);
      			});
      		});
      		function truSP(ev) {
      			let id = $(ev.target).attr('id');
      			fetch('/tru-san-pham/' + id)
      			.then(response => response.text())
      			.then(data => {
      				$('#table-content').html(data);
      			});
      		}
      		function congSP(ev) {
      			let id = $(ev.target).attr('id');
      			fetch('/them-vao-gio/' + id)
      			.then(response => response.text())
      			.then(data => {
      				$('#table-content').html(data);
      			});
      		}
      		function xoaSP(ev) {
      			let id = $(ev.target).attr('id');
      			fetch('/xoa-san-pham/' + id)
      			.then(response => response.text())
      			.then(data => {
      				$('#table-content').html(data);
      			});
      		}
      	</script>
</body>
</html>