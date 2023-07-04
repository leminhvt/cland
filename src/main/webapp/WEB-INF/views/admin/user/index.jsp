<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-box-large">
	<div class="row">
		<div class="panel-heading">
			<div class="panel-title ">Quản lý người dùng</div>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-8">
			<a href="${pageContext.request.contextPath}/admin/user/add"
				class="btn btn-success"><span class="glyphicon glyphicon-plus"
				aria-hidden="true"></span>&nbsp;Thêm</a>
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
						<th>Usernam</th>
						<th>Fullname</th>
						<th>Role</th>
						<th>Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty users}">
						<c:forEach items="${users}" var="user">
							<tr class="odd gradeX">
								<td>${user.id}</td>
								<td>${user.username}</td>
								<td>${user.fullname}</td>
								<td>${user.role.name}</td>
								<td class="center text-center"><a
									href="${pageContext.request.contextPath}/admin/user/edit/${user.id}"
									title="" class="btn btn-primary"> <span
										class="glyphicon glyphicon-pencil "></span> Sửa
								</a> <c:if
										test="${user.username ne pageContext.request.userPrincipal.name}">
										<a
											onclick="return confirm('Bạn có muốn xóa ${user.username}')"
											href="${pageContext.request.contextPath}/admin/user/del/${user.id}"
											title="" class="btn btn-danger"> <span
											class="glyphicon glyphicon-trash"></span> Xóa
										</a>
									</c:if></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<!-- /.row -->
</div>