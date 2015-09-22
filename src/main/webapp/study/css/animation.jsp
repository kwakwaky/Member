<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>animation.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
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
	
	.box1_animation {
		animation: box1 3s;
	}
	
	<%-- 애니메이션 클래스 --%>
	<%-- 퍼센트는 시간을 의미, 즉 주어진 시간의 퍼센트 --%>
	@keyframes box1 {
		0% {
			width: 100px;
			transform: scale(0.5, 0.5) rotateY(90deg);
			background-color: #173A42;
		}
		50% {
			width: 200px;
			transform: scale(1, 1) rotateY(180deg);
			background-color: #2E7383;
		}
		100% {
			width: 300px;
			transform: scale(2, 2) rotateY(360deg);
			background-color: #EAF1F3;
		}
	}
	
</style>
<script type="text/javascript">
$(document).ready(function() {
	
	$('div').on('mouseover', function() {
		$(this).addClass('border');
	});
	
	$('div').on('mouseout', function() {
		$(this).removeClass('border');
	});
	
	
	$('.box').on('click', function() {
		
		$(this).removeClass('box1_animation')
			   .addClass('box1_animation');
	});
	
});

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