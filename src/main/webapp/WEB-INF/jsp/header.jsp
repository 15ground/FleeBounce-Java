<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<link rel="stylesheet" href="/css/products.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<header>
	<c:choose>
		<c:when test="${not empty currentUser}">
			<div class="container-nav">
				<div class="nav-bar">
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
									<li><a href="#">${username }</a></li>
								</ul>
							</nav>
							<a href="/your-cart" class="icon"> <img
								src="/images/cart.png" />
							</a>
							<form action="/logout" method="post">
								<button class="log-out" name="action" value="logout">
									<i class="fa fa-sign-out" aria-hidden="true"></i>
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>

			<div class="container-nav">
				<div class="nav-bar">
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
									<li><a href="/login?cartStatus=${cartStatus}"><s:message
												code="lo.index.login" /></a></li>
								</ul>
							</nav>
							<a href="/your-cart" class="icon"> <img
								src="/images/cart.png" />
							</a>
						</div>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<div class="content">
		<div class="text-content">
			<h1>
				<s:message code="lo.index.title1" />
			</h1>
			<p>
				<s:message code="lo.index.title2" />
			</p>
			<a href="#" class="btn-content"><s:message code="lo.index.title" />
				&#8594;</a>
		</div>
		<div class="text-content">
			<img src="/images/image1.png" />
		</div>
	</div>
</header>