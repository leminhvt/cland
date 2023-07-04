<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>

<div class="clearfix content">
	<div class="contact_us">
		<h1>Liên hệ với chúng tôi</h1>
		<p>
			TRUNG TÂM ĐÀO TẠO LẬP TRÌNH VINAENTER EDU<br /> Trụ sở: 154 Phạm Như
			Xương, Liên Chiểu, Đà Nẵng<br /> Web: <a
				href="http://vinaenter.edu.vn" title="">www.vinaenter.edu.vn</a>
		</p>

		<form action="${pageContext.request.contextPath}/contact"
			method="post">
			<p>
				<input type="text" class="wpcf7-text" placeholder="Họ tên *"
					name="fullname" />
				<form:errors path="contact.fullname"></form:errors>
			</p>
			<p>
				<input type="text" class="wpcf7-email" placeholder="Email *"
					name="email" />
				<form:errors path="contact.email"></form:errors>
			</p>
			<p>
				<input type="text" class="wpcf7-text" placeholder="Chủ đề *"
					name="subject" />
				<form:errors path="contact.subject"></form:errors>
			</p>
			<p>
				<textarea class="wpcf7-textarea" placeholder="Nội dung *"
					name="content"></textarea>
				<form:errors path="contact.content"></form:errors>
			</p>
			<p>
				<input type="Submit" class="wpcf7-submit" value="Gửi liên hệ" />
			</p>
		</form>

	</div>

</div>