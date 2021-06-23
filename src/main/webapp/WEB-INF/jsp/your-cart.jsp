<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/products.css">
<title>Giỏ hàng của bạn</title>
</head>
<body>
	<div id="table-content">
		<c:import url="newheader.jsp"></c:import>
		<h2 class="pro-title" style="margin-top: 90px;">Giỏ hàng của bạn</h2>
		<section style="margin-bottom: 370px;">
			<div class="small-container">
				<div class="content cart-page" id="cartPage">
					<table>
						<tr>
							<th>Sản phẩm</th>
							<th>Số lượng</th>
							<th>Thành tiền</th>
						</tr>
						<c:forEach var="cart" items="${cart.cartDetails}">
							<tr>
								<td>
									<div class="cart-info">
										<img src="${cart.key.images }" />
										<div>
											<p>${cart.key.name }</p>
											<small>Giá tiền: <fmt:formatNumber type="number"
													maxFractionDigits="1" value="${cart.key.price}" />đ
											</small> <br />
											<button onclick="xoaSP(event)" id="${cart.key.id}">Bỏ</button>
										</div>
									</div>
								</td>
								<td><button onclick="truSP(event)" id="${cart.key.id }"
										class='${cart.value<=1 ? "btn-cart-minus disable-minus" : "btn-cart-minus"}'
										<c:if test="${cart.value<=1 }">disabled="disabled"</c:if>>-</button>
									${cart.value }
									<button onclick="congSP(event)" id="${cart.key.id }"
										class='${cart.value>=10 ? "btn-cart-add disable-add" : "btn-cart-add"}'
										<c:if test="${cart.value>=10 }">disabled="disabled"</c:if>>+</button></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="1"
										value="${cart.value*cart.key.price}" />đ</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="total-price">
					<table>
						<tr>
							<c:set var="total" value="${0 }" />
							<c:forEach var="cart" items="${cart.cartDetails}">
								<c:set var="total" value="${total+(cart.value*cart.key.price) }" />
							</c:forEach>
							<td>Thành tiền:</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="1"
									value="${total}" />đ</td>
						</tr>
						<tr>
							<td>Phí vận chuyển:</td>
							<td>35.000đ</td>
						</tr>
						<tr>
							<td>Tổng tiền:</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="1"
									value="${total+35000 }" />đ</td>
						</tr>
					</table>
					<a class="btn-add" href="check-out">THANH TOÁN</a>
					<h4 style="white-space: nowrap; margin: 1px auto;">hoặc</h4>
					<a class="btn-add" href="/products">TIẾP TỤC MUA HÀNG</a>
				</div>
			</div>
		</section>
		<c:import url="footer.jsp"></c:import>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script>
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
  			let ok = confirm("Xác nhận xóa?");
  		    if (ok == true) {
  			let id = $(ev.target).attr('id');
  			console.log(id);
  			fetch('/xoa-san-pham/' + id)
  			.then(response => response.text())
  			.then(data => {
  				$('#table-content').html(data);
  			});
  		    }
  		}
		</script>

