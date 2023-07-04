<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>
<div class="clearfix content">

	<h1>Tiêu đề bài viết</h1>
	<div class="clearfix post-meta">
		<p>
			<span><i class="fa fa-clock-o"></i> Địa chỉ: ${land.address}</span> <span><i
				class="fa fa-folder"></i> Diện tích: ${land.area}</span>
		</p>
	</div>
	<div class="vnecontent">
		<p>${land.description}</p>
	</div>
	<br />
	<div >
		<a href="javascript:void(0)" onclick="return getActive(${land.lid})">
			<img style="width: 50px;height: 50x" src="${pageContext.request.contextPath}/publicUrl/images/slides/like.jpg"
			alt="" />
		</a>
		<p class="abc-${land.lid}">${land.count_views}</p>
	</div>


</div>

<div class="more_themes">
	<h2>
		Bất động sản liên quan <i class="fa fa-thumbs-o-up"></i>
	</h2>
	<div class="more_themes_container">
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="land">
					<div class="single_more_themes floatleft">
						<img
							src="${pageContext.request.contextPath}/files/${land.picture}"
							alt="" /> <a href=""><h2>${land.lname}</h2></a>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div>Không có tin tức cùng danh mục</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<script type="text/javascript">
	function getActive(landId){
		$.ajax({
			url: '<%=request.getContextPath()%>/updateLike',
			type : 'POST',
			cache : false,
			data : {
				alandId : landId
			},
			success : function(data) {
				$(".abc-"+landId).html(data);
			},
			error : function() {
				alert('Có lỗi xảy ra');
			}
		});
		return false;

	}
</script>
