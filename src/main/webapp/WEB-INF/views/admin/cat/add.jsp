<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp" %>
<div class="row">
	<div class="col-md-12 panel-info">
		<div class="content-box-header panel-heading">
			<div class="panel-title ">Thêm danh mục</div>
		</div>
		<div class="content-box-large box-with-header">
			<form action="${pageContext.request.contextPath}/admin/cat/add"
				method="post">
				<div>
					<div class="row mb-10"></div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="name">Tên danh mục</label> 
								<input type="text" class="form-control" placeholder="Nhập tên danh mục" name="cname">
								<form:errors path="category.cname"></form:errors>
							</div>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Thêm" class="btn btn-success" /> <input
								type="reset" value="Nhập lại" class="btn btn-default" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>