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
                    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
                    <link rel="stylesheet" href="/css/order.css">
                    <title>Đơn hàng</title>
                </head>

                <body>
                    <div class="sidebar">
                        <div class="logo-details" style="text-decoration: none;">
                            <a href="/products"> <i class='bx bxl-c-plus-plus'></i>
                            </a><span class="logo_name">Flee Bounce</span>
                        </div>
                        <ul class="nav-links">
                            <li>
                                <a href="/dashboard"> <i class='bx bx-grid-alt'></i> <span class="links_name">Dashboard</span>
                                </a>
                            </li>
                            <li>
                                <a href="/products/list"> <i class='bx bx-box'></i> <span class="links_name">Sản phẩm</span>
                                </a>
                            </li>
                            <li>
                                <a href="/category/list"> <i class='bx bx-list-ul'></i>
                                    <span class="links_name">Danh mục</span>
                                </a>
                            </li>
                            <li>
                                <a href="/order/list" class="active"> <i class='bx bx-pie-chart-alt-2'></i> <span class="links_name">Đơn
						hàng</span>
                                </a>
                            </li>
                            <li>
                                <a href="#"> <i class='bx bx-message'></i> <span class="links_name">Tin nhắn</span>
                                </a>
                            </li>
                            <li>
                                <a href="#"> <i class='bx bx-heart'></i> <span class="links_name">Yêu thích</span>
                                </a>
                            </li>
                            <li>
                                <a href="#"> <i class='bx bx-cog'></i> <span class="links_name">Cài đặt</span>
                                </a>
                            </li>
                            <li class="log_out">
                                <a href="#"> <i class='bx bx-log-out'></i>
                                    <span class="links_name">Đăng xuất</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <section class="home-section">
                        <nav>
                            <div class="sidebar-button">
                                <i class='bx bx-menu sidebarBtn'></i> <span class="dashboard">Hóa
					đơn</span>
                            </div>

                            <div class="profile-details">
                                <img src="/images/Hoho.jpg" alt=""> <span class="admin_name">Việt
					Hưng</span> <i class='bx bx-chevron-down'></i>
                            </div>
                        </nav>
                        <h1 style="font-size: 20px; margin: 105px auto 20px; width: 300px; font-weight: 900;">DANH SÁCH HÓA ĐƠN</h1>
                        <sf:form modelAttribute="searchForm" acion="test" method="get">
                            <div class="search-box" style="width: 198px; float: right; margin: 0px 15px">
                                <sf:input path="name" placeholder="Tìm kiếm..." style="height: 40px; width: 197px; outline: none; border: 2px solid #EFEEF1; border-radius: 6px; font-size: 14px; padding: 0 15px;" />
                                <sf:input path="sortBy" id="sortByInput" type="hidden" />
                                <sf:input path="index" id="indexInput" type="hidden" />
                                <sf:input path="page" id="pageInput" type="hidden" />
                                <input value="Tìm kiếm" id="searchBt" type="hidden" />
                            </div>
                        </sf:form>
                        <div class="table_responsive">
                            <table>
                                <thead>
                                    <tr>
                                        <th style="text-align: center;"><button sortBy="id" class="xep" style="background: #00bcd4; outline: none; border: none; color: white; cursor: pointer; font-weight: 700;">
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
                                        <th style="text-align: center;"><button sortBy="customer.name" class="xep" style="background: #00bcd4; outline: none; border: none; color: white; cursor: pointer; font-weight: 700;">
								Họ và tên
								<c:if test="${searchForm.sortBy == 'customer.name' }">
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
                                        <th style="text-align: center;"><button sortBy="customer.phoneNumber" class="xep" style="background: #00bcd4; outline: none; border: none; color: white; cursor: pointer; font-weight: 700;">
								Số điện thoại
								<c:if test="${searchForm.sortBy == 'customer.phoneNumber' }">
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
                                        <th style="text-align: center;"><button sortBy="customer.email" class="xep" style="background: #00bcd4; outline: none; border: none; color: white; cursor: pointer; font-weight: 700;">
								Email
								<c:if test="${searchForm.sortBy == 'customer.email' }">
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
                                        <th style="text-align: center;">Hiển thị</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="order" items="${order}">
                                        <tr>
                                            <td style="text-align: center;">${order.id}</td>
                                            <td style="text-align: center;">${order.customer.name}</td>
                                            <td style="text-align: center;">${order.customer.phoneNumber}</td>
                                            <td style="text-align: center;">${order.customer.email}</td>
                                            <td style="text-align: center;">
                                                <button type="button" class="action_btn" data-toggle="modal" data-target="#detail${order.id}">Chi tiết</button>
                                                <!-- Modal -->
                                                <div class="modal fade" id="detail${order.id}" tabindex="-1" role="dialog" aria-labelledby="#detail${order.id}" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Chi tiết hóa đơn</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div style="background: #ddd; padding: 20px; margin: 20px">
                                                                    <h3 style="text-align: center;">Thông tin đơn hàng</h3>
                                                                    <p>
                                                                        Mã order: <b>${order.id }</b>
                                                                    </p>
                                                                    <p>
                                                                        Tên người nhận: <b>${order.customer.name}</b>
                                                                    </p>
                                                                    <p>
                                                                        Địa chỉ: <b>${order.customer.address}</b>
                                                                    </p>
                                                                    <p>
                                                                        Số điện thoại: <b>${order.customer.phoneNumber}</b>
                                                                    </p>
                                                                    <p>
                                                                        Tổng tiền : <b><fmt:formatNumber type="number"
																maxFractionDigits="1" value="${order.total }" /> VND</b>
                                                                    </p>
                                                                </div>

                                                                <div style="background: #ddd; padding: 20px; margin: 20px">
                                                                    <h3 style="text-align: center;">Sản phẩm bao gồm</h3>
                                                                    <c:forEach var="orderDetail" items="${order.order_items }">
                                                                        <p>
                                                                            - ${orderDetail.products.name} : <b><fmt:formatNumber
																	type="number" maxFractionDigits="1"
																	value="${orderDetail.price}" /> VND</b> <i>(x${orderDetail.amount })</i>
                                                                        </p>
                                                                    </c:forEach>

                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="page-btn" style="margin-top: 20px; margin-left: 600px;">
                            <c:if test="${maxPage > 1 }">
                                <c:forEach var="pageIndex" begin="0" end="${maxPage - 1}">
                                    <button trang="${pageIndex}" class="trang<c:if test=" ${pageIndex==s earchForm.page} "> active</c:if>">
						${pageIndex + 1}</button>
                                </c:forEach>
                            </c:if>
                        </div>
                    </section>
                    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
                    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
                    <script type="text/javascript">
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