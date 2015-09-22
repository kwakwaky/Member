<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>display.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	span, div {
		border: 1px solid gray;
		height: 100px;
		width: 100px;
<%-- 		margin: 50px 20px; 위 아래를 줌 --%>
	}

	
	
	
	
	
	
	
</style>
</head>
<body>
<h1>display : inline | block | inline-block</h1>
<!-- inline : content size만큼만 공간을 차지, 높이는 적용안됨 -->
<!-- block : 높이가 적용됨 -->
<span>inline1</span>
<!-- <span>inline-block</span> -->
<span style="display: inline-block; vertical-align: bottom;">inline-block</span>
<span>inline3</span>
<div>block1</div>
<div style="display: nonet">block2</div>
<div>block3</div>

</body>
</html>