<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>
<div class="clearfix content">
	<div class="content_title">
		<h2>${category.cname}</h2>
	</div>

	<div class="clearfix single_work_container">
		<c:choose>
			<c:when test="${not empty lands}">
				<c:forEach items="${lands}" var="land">
					<div class="clearfix single_work">
						<img class="img_top"
							src="${pageContext.request.contextPath}/files/${land.picture}"
							alt="" /> <img class="img_bottom"
							src="${pageContext.request.contextPath}/files/${land.picture}"
							alt="" /> <a href="${pageContext.request.contextPath}/single/${land.lid}"><h2>${land.lname}</h2></a>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div>Không có tin tức nào của danh mục ${category.cname}</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
