<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>
<div class="row">
	<div class="col-md-12 panel-info">
		<div class="content-box-header panel-heading">
			<div class="panel-title ">Profile</div>
		</div>
		<div class="content-box-large box-with-header">
			<form action="${pageContext.request.contextPath}/admin/user/profile/${user.id}"
				method="post">
				<div>
					<div class="row mb-10"></div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="username">Username</label> <input readonly="readonly" name="username"
									value="${user.username}" type="text" class="form-control"
									placeholder="Nhập username">
								<form:errors path="user.username"></form:errors>
							</div>
							<div class="form-group">
								<label for="fullname">Fullname</label> <input name="fullname"
									value="${user.fullname}" type="text" class="form-control"
									placeholder="Nhập fullname">
								<form:errors path="user.fullname"></form:errors>
							</div>
							<div class="form-group">
								<label for="password">Password</label> <input name="password"
									type="password" class="form-control"
									placeholder="Nhập password">
								<form:errors path="user.password"></form:errors>
							</div>
							<div class="form-group">
								<label>Role</label> <select name="roleid" class="form-control">
									<c:forEach items="${list}" var="role">
										<c:choose>
											<c:when test="${role.id == user.role.id}">
												<option selected="selected" value="${role.id}">${role.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${role.id}">${role.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Sửa" class="btn btn-success" /> <input
								type="reset" value="Nhập lại" class="btn btn-default" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>