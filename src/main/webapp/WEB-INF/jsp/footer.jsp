<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<link rel="stylesheet" href="/css/products.css">

<footer>
	<div class="container">
		<div class="content">
			<div class="footer-col-1">
				<h3>
					<s:message code="lo.index.app" />
				</h3>
				<p>
					<s:message code="lo.index.apptitle" />
				</p>
				<div class="app-logo">
					<img src="/images/play-store.png"> <img
						src="/images/app-store.png">
				</div>
			</div>
			<div class="footer-col-2">
				<img src="/images/logo-white.png">
				<p>
					<s:message code="lo.index.foottitle" />
				</p>
			</div>
			<div class="footer-col-3">
				<h3>
					<s:message code="lo.index.contactus" />
				</h3>
				<ul>
					<li>Facebook</li>
					<li>Twitter</li>
					<li><a
						href="${requestScope['javax.servlet.forward.request_uri']}?lang=en"
						class="active-lang"> <s:message code="lo.index.en" />
					</a></li>
					<li><a
						href="${requestScope['javax.servlet.forward.request_uri']}?lang=vi">
							<s:message code="lo.index.vi" />
					</a></li>
				</ul>
			</div>
		</div>
		<hr>
		<p class="copyright">Copyright 2021 - 15ground</p>
	</div>
</footer>