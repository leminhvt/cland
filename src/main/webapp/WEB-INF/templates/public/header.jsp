<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>
<div class="wrapper header">
	<div class="clearfix header_top">
		<div class="clearfix logo floatleft">
			<a href="${pageContext.request.contextPath}"><h1>
					<span>C</span>Land
				</h1></a>
		</div>
		<div class="clearfix search floatright">
			<form>
				<input type="text" placeholder="Search" /> <input type="submit" />
			</form>
		</div>
	</div>
	<div class="header_bottom">
		<nav>
			<ul id="nav">
				<li><a href="${pageContext.request.contextPath}">Trang chủ</a></li>
				<li id="dropdown"><a href="${pageContext.request.contextPath}/cat">Bất động sản</a>
					<ul>
						<c:choose>
							<c:when test="${not empty caterogy }">
								<c:forEach items="${caterogy}" var="cat">
									<li><a
										href="${pageContext.request.contextPath}/cat/${cat.cid}">${cat.cname}</a></li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li><a href="">Không có danh mục nào</a>(12)</li>
							</c:otherwise>
						</c:choose>
					</ul></li>
				<li id="dropdown"><a href="${pageContext.request.contextPath}/single">Giới thiệu</a></li>
				<li><a href="${pageContext.request.contextPath}/contact">Liên hệ</a></li>
			</ul>
		</nav>
	</div>
</div>