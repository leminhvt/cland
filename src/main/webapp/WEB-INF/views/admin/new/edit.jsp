<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>
<div class="row">
	<div class="col-md-12 panel-info">
		<div class="content-box-header panel-heading">
			<div class="panel-title ">Thêm tin tức</div>
		</div>
		<div class="content-box-large box-with-header">
			<form action="${pageContext.request.contextPath}/admin/new/edit/${land.lid}"
				method="post" enctype="multipart/form-data">
				<div>
					<div class="row mb-10"></div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="lname">Tên tin tức</label> <input name="lname"
									value="${land.lname}" type="text" class="form-control"
									placeholder="Nhập tên tin tức">
							</div>
							<div class="form-group">
								<label>Danh mục tin</label> <select name="cid"
									class="form-control">
									<c:choose>
										<c:when test="${not empty listC }">
											<c:forEach items="${listC}" var="cat">
												<c:choose>
													<c:when test="${cat.cid == land.category.cid}">
														<option selected="selected" value="${cat.cid}">${cat.cname}</option>
													</c:when>
													<c:otherwise>
														<option value="${cat.cid}">${cat.cname}</option>
													</c:otherwise>
												</c:choose>

											</c:forEach>
										</c:when>
										<c:otherwise>
											<option>Không có danh mục nào</option>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
							<div class="form-group">
								<label>Thêm hình ảnh</label> <input name="hinhanh" type="file"
									class="btn btn-default" id="exampleInputFile1">
								<p class="help-block">
									<em>Định dạng: jpg, png, jpeg,...</em>
								</p>
								<p>
									<img style="width: 100px"
										src="${pageContext.request.contextPath}/files/${land.picture}">
								</p>
							</div>
							<div class="form-group">
								<label for="area">Diện tích</label> <input name="area"
									value="${land.area}" type="text" class="form-control"
									placeholder="Nhập diện tích">
							</div>
							<div class="form-group">
								<label for="address">Địa chỉ</label> <input name="address"
									value="${land.address}" type="text" class="form-control"
									placeholder="Nhập địa chỉ">
							</div>
							<div class="form-group">
								<label for="description">Mô tả</label>
								<textarea class="form-control" rows="3" name="description">${land.description}</textarea>
							</div>
						</div>
						<div class="col-sm-6"></div>
						<div class="col-sm-12">
							<div class="form-group">
								<label for="detail">Chi tiết</label>
								<textarea class="form-control" id="detail" rows="7" name="detail">${land.detail}</textarea>
							</div>
						</div>
						<script type="text/javascript">
							var editor = CKEDITOR.replace('detail');
							CKFinder.setupCKEditor(editor,'${pageContext.request.contextPath}/libraries/ckfinder/');
						</script>
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