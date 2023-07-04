<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-box-large">
	<div class="row">
		<div class="panel-heading">
			<div class="panel-title ">Quản lý danh mục</div>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-8">
			<a href="${pageContext.request.contextPath}/admin/cat/add"
				class="btn btn-success"><span class="glyphicon glyphicon-plus"
				aria-hidden="true"></span>&nbsp;Thêm</a>

		</div>
	</div>

	<div class="row">
		<div class="panel-body">
			${msg}
			<table cellpadding="0" cellspacing="0" border="0"
				class="table table-striped table-bordered" id="example">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tên Danh Mục</th>
						<th>Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty list}">
						<c:forEach items="${list}" var="cat">
							<tr class="odd gradeX">
								<td>${cat.cid}</td>
								<td>${cat.cname}</td>
								<td class="center text-center">
								<a href="${pageContext.request.contextPath}/admin/cat/edit/${cat.cid}" title="" class="btn btn-primary"> <span class="glyphicon glyphicon-pencil "></span> Sửa </a>
								<a onclick="return confirm('Bạn có muốn xóa ${cat.cname}')" href="${pageContext.request.contextPath}/admin/cat/del/${cat.cid}" title="" class="btn btn-danger"> <span class="glyphicon glyphicon-trash"></span> Xóa </a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<!-- /.row -->
</div>