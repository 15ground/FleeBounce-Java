<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="/css/form.css">
<title>Xác nhận đơn hàng</title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/newheader.jsp"></c:import>
	<h2 class="pro-title" style="margin-top: 90px;">Vui lòng xác nhận
		đơn hàng của bạn</h2>
	<div class="small-container" style="margin-bottom: 230px;">
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
								</div>
							</div>
						</td>
						<td><h4 style="white-space: nowrap; margin-left: 20px;">${cart.value }</h4></td>
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
		</div>
	</div>
	<form:form class="form-add" action="check-out-confirm" method="post"
		modelAttribute="order" style="width: 1040px;margin-left: 273px;">
		<form:input path="id" type="hidden" />
		<br />
		<form:label path="name">Họ và tên:</form:label>
		<form:input path="name" value="${name }" placeholder="Họ và tên..."></form:input>
		<form:errors path="name"
			cssStyle="color:red;margin-left: 10px;font-style: italic;"></form:errors>
		<br />
		<form:label path="phoneNumber">Số điện thoại:</form:label>
		<form:input path="phoneNumber" value="${phoneNumber }"
			placeholder="Số điện thoại..."></form:input>
		<form:errors path="phoneNumber"
			cssStyle="color:red;margin-left: 10px;font-style: italic;"></form:errors>
		<br />
		<form:label path="address">Địa chỉ nhận hàng:</form:label>
		<form:input path="address" value="${address }"
			placeholder="Địa chỉ nhận hàng..."></form:input>
		<form:errors path="address"
			cssStyle="color:red;margin-left: 10px;font-style: italic;"></form:errors>
		<br />
		<br />
		<input class="btn-add" style="width: 200px; margin: 0 auto;"
			type="submit" value="Xác nhận" />
	</form:form>

	<c:import url="/WEB-INF/jsp/footer.jsp"></c:import>
</body>
</html>