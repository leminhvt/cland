<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/templates/taglib.jsp"%>
<html>
<head>
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
<c:url value="/resources/admin" var="contextPath" scope="application"></c:url>
<link rel="shortcut icon" type="image/ico"
	href="${pageContext.request.contextPath}/adminUrl/images/icon-180x180.png" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/adminUrl/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- styles -->
<link href="https://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/adminUrl/css/style1.css"
	rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/libraries/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/libraries/ckfinder/ckfinder.js"></script>

</head>
<body>
	<div class="header">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>

	<div class="page-content">
		<div class="row">
			<tiles:insertAttribute name="leftbar"></tiles:insertAttribute>
			<div class="col-md-10">
				<tiles:insertAttribute name="body"></tiles:insertAttribute>
			</div>
		</div>
	</div>

	<footer>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</footer>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.request.contextPath}/adminUrl/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/adminUrl/js/custom.js"></script>
</body>
</html>
