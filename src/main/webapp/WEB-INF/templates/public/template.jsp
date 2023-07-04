<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>CLand | VinaEnter Edu</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!--Oswald Font -->
	<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/publicUrl/css/tooltipster.css" />
	<!-- home slider-->
	<link href="${pageContext.request.contextPath}/publicUrl/css/pgwslider.css" rel="stylesheet">
			<!-- Font Awesome -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/publicUrl/css/font-awesome.min.css">
	<link href="${pageContext.request.contextPath}/publicUrl/css/style.css" rel="stylesheet" media="screen">
	<link href="${pageContext.request.contextPath}/publicUrl/css/responsive.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="${pageContext.request.contextPath}/publicUrl/js/jquery-3.6.4.min.js"></script>
</head>

<body>

	<section id="header_area"> <tiles:insertAttribute
		name="header"></tiles:insertAttribute> </section>
	<section id="content_area">
	<div class="clearfix wrapper main_content_area">
		<div class="clearfix main_content floatleft">
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
		</div>
		<div class="clearfix sidebar_container floatright">
			<tiles:insertAttribute name="leftbar"></tiles:insertAttribute>
		</div>
	</div>
	</div>
	</section>
	<section id="footer_bottom_area"> <tiles:insertAttribute
		name="footer"></tiles:insertAttribute> </section>
</body>
</html>