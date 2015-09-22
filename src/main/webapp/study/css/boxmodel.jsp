<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boxmodel.jsp</title>
<%-- <%@ include file="/WEB-INF/views/common.jspf" %> --%>
<style type="text/css">
	.box {
		width: 100px;
		height: 100px;
		background-color: #4DBFDB;
		display: inline-block;
		vertical-align: bottom;
	}
	
	.border {
		border: 10px solid #2E7383;
	}
	
	.padding {
		padding: 10px;
	}
	
	.boxsizing {
		box-sizing: border-box;
	}
	
</style>
<script type="text/javascript">
// $(document).ready(function() {
	
// 	$('div').addClass('box');
	
// });

</script>

</head>
<body>
<h1>boxmodel</h1>

<div class="box border padding">box1</div>
<div class="box">box2</div>
<div class="box border padding boxsizing">box3</div>
<div class="box">box4</div>
<div class="box">box5</div>
<div class="box">box6</div>
<div class="box">box7</div>
<div class="box">box8</div>
<div class="box">box9</div>
<div class="box">box10</div>

</body>
</html>