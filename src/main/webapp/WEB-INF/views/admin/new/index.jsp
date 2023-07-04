<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>
<div class="content-box-large">
	<div class="row">
		<div class="panel-heading">
			<div class="panel-title ">Quản lý tin tức</div>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-8">
			<a href="${pageContext.request.contextPath}/admin/new/add"
				class="btn btn-success"><span class="glyphicon glyphicon-plus"
				aria-hidden="true"></span>&nbsp;Thêm</a>

		</div>
		<div class="col-sm-6" style="text-align: right;">
			<form method="get" action="${pageContext.request.contextPath}/admin/new/search">
				<input type="text"  name="search"/>
				<input type="submit" value="Tìm kiếm" />
			</form>
			<br />
		</div>
	</div>
	${msg}
	<div class="row">
		<div class="panel-body">
			<table cellpadding="0" cellspacing="0" border="0"
				class="table table-striped table-bordered" id="example">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tên</th>
						<th>Ngày đăng</th>
						<th>Danh mục</th>
						<th>Hình ảnh</th>
						<th>Diện tích</th>
						<th>Địa chỉ</th>
						<th>Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty listL }">
							<c:forEach items="${listL}" var="land">
								<tr class="odd gradeX">
									<td>${land.lid}</td>
									<td>${land.lname}</td>
									<td>${land.date_create}</td>
									<td>${land.category.cname}</td>
									<td><img style="width: 100px"
										src="${pageContext.request.contextPath}/files/${land.picture}" />
									</td>
									<td>${land.area}</td>
									<td>${land.address}</td>
									<td class="center text-center"><a
										href="${pageContext.request.contextPath}/admin/new/edit/${land.lid}"
										title="" class="btn btn-primary"><span
											class="glyphicon glyphicon-pencil "></span> Sửa</a> <a
										onclick="return confirm('Bạn có muốn xóa ${land.lname}')"
										href="${pageContext.request.contextPath}/admin/new/del/${land.lid}"
										title="" class="btn btn-danger"><span
											class="glyphicon glyphicon-trash"></span> Xóa</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<td>Không có tin tức nào</td>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>

			<!-- Pagination -->
			<nav class="text-center" aria-label="...">
				<ul class="pagination">
					<c:choose>
						<c:when test="${page == 1}">
							<li class="active"><a href="#"> << </a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.request.contextPath}/admin/new/index?page=${page - 1}">
									<< </a></li>
						</c:otherwise>
					</c:choose>

					<c:forEach begin="1" end="${numberOfPage}" step="1"
						varStatus="loop">
						<c:choose>
							<c:when test="${loop.count == page}">
								<li class="active"><a
									href="${pageContext.request.contextPath}/admin/new/index?page=${loop.count}">${loop.count}</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath}//admin/new/index?page=${loop.count}">${loop.count}</a></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>

					<c:choose>
						<c:when test="${page == numberOfPage}">
							<li class="active"><a href="#"> >> </a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.request.contextPath}/admin/new/index?page=${page + 1}">
									>> </a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
			<!-- /.pagination -->

		</div>
	</div>
	<!-- /.row -->
</div>