<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>

<div class="clearfix content">
	<div class="content_title">
		<h2>Bài viết mới</h2>
	</div>
	<c:choose>
		<c:when test="${not empty landsL}">
			<c:forEach items="${landsL}" var="land">
				<div class="clearfix single_content">
					<div class="clearfix post_date floatleft">
						<div class="date">
							<h3>
								<fmt:formatDate value="${land.date_create}" pattern="dd" />
							</h3>
							<p>
								Tháng
								<fmt:formatDate value="${land.date_create}" pattern="M" />
							</p>
						</div>
					</div>
					<div class="clearfix post_detail">
						<h2>
							<a href="${pageContext.request.contextPath}/single/${land.lid}">${land.lname}</a>
						</h2>
						<div class="clearfix post-meta">
							<p>
								<span><i class="fa fa-clock-o"></i> Địa chỉ:
									${land.address}</span> <span><i class="fa fa-folder"></i> Diện
									tích: ${land.area}m2</span>
							</p>
						</div>
						<div class="clearfix post_excerpt">
							<img
								src="${pageContext.request.contextPath}/files/${land.picture}"
								alt="" />
							<p>${land.description}</p>
						</div>
						<a href="${pageContext.request.contextPath}/single/${land.lid}">Đọc
							thêm</a>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>Không có tin tức nào</div>
		</c:otherwise>
	</c:choose>
</div>

<div class="pagination">
	<nav>
		<ul>
			<c:choose>
				<c:when test="${page == 1}">
					<li class="active"><a href="#"> << </a></li>
				</c:when>
				<c:otherwise>
					<li><a
						href="${pageContext.request.contextPath}/?page=${page - 1}">
							<< </a></li>
				</c:otherwise>
			</c:choose>

			<c:forEach begin="1" end="${numberOfPage}" step="1" varStatus="loop">
				<c:choose>
					<c:when test="${loop.count == page}">
						<li class="active"><a
							href="${pageContext.request.contextPath}/?page=${loop.count}">${loop.count}</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${pageContext.request.contextPath}/?page=${loop.count}">${loop.count}</a></li>
					</c:otherwise>
				</c:choose>

			</c:forEach>

			<c:choose>
				<c:when test="${page == numberOfPage}">
					<li class="active"><a href="#"> >> </a></li>
				</c:when>
				<c:otherwise>
					<li><a
						href="${pageContext.request.contextPath}/?page=${page + 1}">
							>> </a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</nav>
</div>