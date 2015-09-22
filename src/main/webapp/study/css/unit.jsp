<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>unit.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	.box {
		margin: 10pt;
		background-color: green;
		height: 20pt;
		display: block;
	}
	
	p {
		font-size: 10pt;
	}

</style>
</head>
<body>
<h1>unit</h1>
<h2>
px:							<br/>
inch: 2.54cm<br/>
cm:<br/>
mm: <br/>
pt: 1/72inch<br/>
pc: 12pt<br/>
em:	1 em = 현재폰트 1배<br/>
ex: 1 ex = 현재폰트 1/2배<br/>


</h2>

<div class="box" style="width: 1in">box</div>
<div class="box" style="width: 2.54cm">box</div>
<div class="box" style="width: 25.4mm">box</div>
<div class="box" style="width: 72pt">box</div>

<p>Hello Unit</p>
<p style="font-size: 1em;">Hello Unit 1em</p>
<p style="font-size: 2em;">Hello Unit 2em</p>
<p style="font-size: 1ex;">Hello Unit 1em</p>
<p style="font-size: 2ex;">Hello Unit 2em</p>

</body>
</html>