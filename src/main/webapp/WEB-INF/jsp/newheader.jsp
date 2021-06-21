<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<link rel="stylesheet" href="/css/products.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<header>
	<div class="container-nav">
		<div class="nav-bar">
			<c:choose>
				<c:when test="${not empty currentUser}">
					<c:if test="${currentUser.getRole() eq 'admin' }">
						<div class="nav-bar">
							<div class="logo">
								<a href="http://localhost:8080/FleeBounce/home"> <img
									src="/images/logo.png" />
								</a>
							</div>
							<nav>
								<ul>
									<li><a href="dashboard.jsp">Dashboard</a></li>
									<li><a href="#">Sản phẩm</a></li>
									<li><a href="#">Giới thiệu</a></li>
									<li><a href="#">Liên hệ</a></li>
									<li><a href="#">${username}</a></li>
								</ul>
							</nav>
						</div>
						<a href="#" class="icon"> <img src="/images/cart.png" />
						</a>
						<form action="${pageContext.request.contextPath}/AuthController"
							method="post">
							<button class="log-out" name="action" value="logout">
								<i class="fa fa-sign-out" aria-hidden="true"></i>
							</button>
						</form>
					</c:if>
					<c:if test="${currentUser.getRole() eq 'user' }">
						<div class="nav-bar">
							<div class="logo">
								<a href="http://localhost:8080/FleeBounce/home"> <img
									src="/images/logo.png" />
								</a>
							</div>
							<nav>
								<ul>
									<li><a href="/dashboard">Trang chủ</a></li>
									<li><a href="#">Sản phẩm</a></li>
									<li><a href="#">Giới thiệu</a></li>
									<li><a href="#">Liên hệ</a></li>
									<li><a href="#">${username}</a></li>
								</ul>
							</nav>
						</div>
						<a href="#" class="icon"> <img src="/images/cart.png" />
						</a>
						<form action="${pageContext.request.contextPath}/AuthController"
							method="post">
							<button class="log-out" name="action" value="logout">
								<i class="fa fa-sign-out" aria-hidden="true"></i>
							</button>
						</form>
					</c:if>
				</c:when>
				<c:otherwise>
					<div class="container-nav">
						<div class="nav-bar">
							<div class="logo">
								<a href="/products"> <img src="/images/logo.png" />
								</a>
							</div>
							<nav>
								<ul>
									<li><a href="/dashboard"><s:message
												code="lo.index.home" /></a></li>
									<li><a href="#"><s:message code="lo.index.products" /></a></li>
									<li><a href="#"><s:message code="lo.index.about" /></a></li>
									<li><a href="#"><s:message code="lo.index.contact" /></a></li>
									<li><a href="views/sigin.jsp"><s:message
												code="lo.index.login" /></a></li>
								</ul>
							</nav>
							<a href="/your-cart" class="icon"> <img
								src="/images/cart.png" />
							</a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>