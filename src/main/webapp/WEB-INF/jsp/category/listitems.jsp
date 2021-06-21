<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/style.css">
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet" href="/css/dashboardst.css">
<title>Danh mục</title>
</head>
<body>
	<div class="sidebar">
		<div class="logo-details" style="text-decoration: none;">
			<a href="/products"> <i class='bx bxl-c-plus-plus'></i>
			</a><span class="logo_name">Flee Bounce</span>
		</div>
		<ul class="nav-links">
			<li><a href="/dashboard"> <i class='bx bx-grid-alt'></i> <span
					class="links_name">Dashboard</span>
			</a></li>
			<li><a href="/products/list"> <i class='bx bx-box'></i> <span
					class="links_name">Sản phẩm</span>
			</a></li>
			<li><a href="/category/list" class="active"> <i
					class='bx bx-list-ul'></i> <span class="links_name">Danh mục</span>
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
		<nav>
			<div class="sidebar-button">
				<i class='bx bx-menu sidebarBtn'></i> <span class="dashboard">Danh
					mục</span>
			</div>

			<div class="profile-details">
				<img src="/images/Hoho.jpg" alt=""> <span class="admin_name">Việt
					Hưng</span> <i class='bx bx-chevron-down'></i>
			</div>
		</nav>
		<h1
			style="font-size: 20px; margin: 105px auto 20px; width: 300px; font-weight: 900;">DANH
			SÁCH DANH MỤC</h1>
		<sf:form modelAttribute="searchForm" acion="test" method="get">
			<div class="search-box"
				style="width: 198px; float: right; margin: 0px 15px">
				<sf:input path="name" placeholder="Tìm kiếm..."
					style="height: 40px; width: 197px; outline: none; border: 2px solid #EFEEF1; border-radius: 6px; font-size: 14px; padding: 0 15px;" />
				<sf:input path="sortBy" id="sortByInput" type="hidden" />
				<sf:input path="index" id="indexInput" type="hidden" />
				<sf:input path="page" id="pageInput" type="hidden" />
				<input value="Tìm kiếm" id="searchBt" type="hidden" />
			</div>
		</sf:form>
		<div class="button-box"
			style="width: 250px; float: left; margin: 0px -24px">
			<span class="action_btn" style="margin-left: -82px;"><a
				href="/category/insert">Thêm</a> </span>
		</div>
		<div class="table_responsive">
			<table>
				<thead>
					<tr>
						<th><button sortBy="id" class="xep"
								style="background: #00bcd4; outline: none; border: none; color: white; cursor: pointer; font-weight: 700;">
								ID
								<c:if test="${searchForm.sortBy == 'id' }">
									<c:choose>
										<c:when test="${searchForm.index}">
									&#8593
								</c:when>
										<c:otherwise>
									&#8595
								</c:otherwise>
									</c:choose>
								</c:if>
							</button></th>
						<th><button sortBy="name" class="xep"
								style="background: #00bcd4; outline: none; border: none; color: white; cursor: pointer; font-weight: 700;">
								Tên danh mục
								<c:if test="${searchForm.sortBy == 'name' }">
									<c:choose>
										<c:when test="${searchForm.index}">
									&#8593
								</c:when>
										<c:otherwise>
									&#8595
								</c:otherwise>
									</c:choose>
								</c:if>
							</button></th>
						<th>Thao tác</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="category" items="${category}">
						<tr>
							<td style="text-align: center;">${category.id}</td>
							<td style="text-align: center;">${category.name}</td>
							<td><span class="action_btn"> <a
									href="/category/edit?id=${category.id}">Sửa</a>
									<p onclick="confirmDelete(${category.id})">Xóa</p>
							</span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="page-btn" style="margin-top: 20px; margin-left: 600px;">
			<c:if test="${maxPage > 1 }">
				<c:forEach var="pageIndex" begin="0" end="${maxPage - 1}">
					<button trang="${pageIndex}"
						class="trang<c:if test="${pageIndex == searchForm.page}"> active</c:if>">
						${pageIndex + 1}</button>
				</c:forEach>
			</c:if>
		</div>
	</section>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
	 confirmDelete = (id) =>{
	    let ok = confirm("Xác nhận xóa?");
	    if (ok == true) {

	        let aTag = document.createElement("a");
	        aTag.href = "/category/delete?id=" + id;
	        document.body.append(aTag);
	        aTag.click();
	        
	    }
	}
	 /* Tìm */
	 $('#searchBt').click(function() {
			$('form #sortByInput').val(xepTheo);
			$('form #indexInput').val(true);
			$('form #pageInput').val(0);
			$('form').submit();
		});
	 /* Pagin */
		$('.trang').click(function() {
			var trang = $(this).attr('trang');
			$('form #pageInput').val(trang);
			$('form').submit();
		});
	 /* Sort */
		$('.xep').click(function() {
			var xepTheo = $(this).attr('sortBy');
			var xepTheoInput = $('form #sortByInput');
			var thutuInput = $('form #indexInput');
			// kiểm tra nếu xếp theo bằng giá trị xếp theo đã nhập
			// thì thay đổi thứ tự
			if (xepTheo == xepTheoInput.val()) {
				if (thutuInput.val() == 'true') {
					thutuInput.val(false);
				} else {
					thutuInput.val(true);
				}
			} else {
				// gán giá trị thứ tự thành tăng dần
				thutuInput.val(true);
				// gán giá trị xếp theo cho input
				xepTheoInput.val(xepTheo);
			}
			// đặt giá trị trang về 0
			$('form #pageInput').val(0);
			$('form').submit();
		});
		/* Menu bar */
		let sidebar = document.querySelector(".sidebar");
		let sidebarBtn = document.querySelector(".sidebarBtn");
		sidebarBtn.onclick = function() {
			sidebar.classList.toggle("active");
			if (sidebar.classList.contains("active")) {
				sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
			} else
				sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
		}
	</script>
</body>
</html>