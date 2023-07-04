<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>
<div class="clearfix sidebar">
	<div class="clearfix single_sidebar category_items">
		<h2>Danh mục bất động sản</h2>
		<ul>
			<c:choose>
				<c:when test="${not empty caterogy }">
					<c:forEach items="${caterogy}" var="cat">
						<li class="cat-item"><a
							href="${pageContext.request.contextPath}/cat/${cat.cid}">${cat.cname}</a>(${landDAO.countLandByCatId(cat.cid)})</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li class="cat-item"><a href="">Không có danh mục nào</a>(12)</li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>

	<div class="clearfix single_sidebar">
		<div class="popular_post">
			<div class="sidebar_title">
				<h2>Xem nhiều</h2>
			</div>
			<ul>
				<c:choose>
					<c:when test="${not empty lands }">
						<c:forEach items="${lands}" var="land">
							<li><a href="">${land.lname}</a></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li><a href="">Không có tin tức nào</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>