<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<table>
	<thead>
		<tr>
			<th>Tên</th>
			<th></th>
			<th>Số lượng</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="chitiet" items="${gioHang.cartDetails}">
			<tr>
				<td>${chitiet.key.name}</td>
				<td><button onclick="truSP(event)" id="${chitiet.key.id}"
						<c:if test="${chitiet.value==1 }">disabled="disabled"</c:if>>-</button></td>
				<td>${chitiet.value}</td>
				<td>
					<button onclick="congSP(event)" id="${chitiet.key.id}">+</button>
				</td>
				<td>
					<button onclick="xoaSP(event)" id="${chitiet.key.id}">X</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>