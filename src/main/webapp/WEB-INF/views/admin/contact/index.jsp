<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>
<div class="content-box-large">
	<div class="row">
		<div class="panel-heading">
			<div class="panel-title ">Quản lý liên hệ</div>
		</div>
	</div>
	<hr>
	<div class="row"></div>
	${msg}
	<div class="row">
		<div class="panel-body">
			<table cellpadding="0" cellspacing="0" border="0"
				class="table table-striped table-bordered" id="example">
				<thead>
					<tr>
						<th>ID</th>
						<th>Fullname</th>
						<th>Email</th>
						<th>Subject</th>
						<th>Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty listC}">
							<c:forEach items="${listC}" var="con">
								<tr class="odd gradeX">
									<td>${con.id}</td>
									<td>${con.fullname}</td>
									<td>${con.email}</td>
									<td>${con.subject}</td>
									<td class="center text-center"><a
										href="${pageContext.request.contextPath}/admin/contact/edit/${con.id}"
										title="" class="btn btn-primary"> <span
											class="glyphicon glyphicon-pencil "></span> Sửa
									</a> <a onclick="return confirm('Bạn có muốn xóa ${con.fullname}')"
										href="${pageContext.request.contextPath}/admin/contact/del/${con.id}"
										title="" class="btn btn-danger"> <span
											class="glyphicon glyphicon-trash"></span> Xóa
									</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							Không có liên hệ nào
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<!-- /.row -->
</div>