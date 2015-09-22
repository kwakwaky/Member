<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout1.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	#world {
		width: 150px;
		position: absolute;
	}
	
	#employee {
		width: 150px;
		position: absolute;
	}
	
	#main {
		margin-left: 180px !important;
	}

</style>
<script type="text/javascript">

var zIndex = 100;

$(document).ready(function() {
	$('nav.w3-topnav > a').on('click', function() {
		//alert("link click...");
		var menu = $(this).attr('href');
		
		switch (menu) {
		case "#world":
			$('#world').css('z-index', zIndex++);
//			$('#employee').css('z-index', 100);
			return false; // default 액션을 하지 마시오 라는 뜻
			break;
		case "#employee":
//			$('#world').css('z-index', 100);
			$('#employee').css('z-index', zIndex++);
			return false;
			break;

		default:
			break;
		}
	});
	
	
});


</script>


</head>
<body>
<nav class="w3-topnav w3-red w3-margin w3-card-16">
	<a href="#home">Home</a>
	<a href="#world">World</a>
	<a href="#employee">Employee</a>
	<a href="#login" class="w3-right">Login</a>
</nav>

<nav id="world" class="w3-container w3-sidenav w3-pink w3-margin w3-card-16">
	<a href="#">World 1</a>
	<a href="#">World 2</a>
	<a href="#">World 3</a>
	<a href="#">World 4</a>
	<a href="#">World 5</a>
	<a href="#">World 6</a>
</nav>

<nav id="employee" class="w3-container w3-sidenav w3-purple w3-margin w3-card-16">
	<a href="#">Employee 1</a>
	<a href="#">Employee 2</a>
	<a href="#">Employee 3</a>
	<a href="#">Employee 4</a>
	<a href="#">Employee 5</a>
	<a href="#">Employee 6</a>
</nav>

<div id="main" class="w3-container w3-card-16">
	<header class="w3-row w3-orange w3-margin">
		<h1>Header</h1>
		<h1>Header</h1>
		<h1>Header</h1>
	</header>
	
	
	
	<article class="w3-row">
		<div class="w3-col m4 w3-padding w3-padding"><img class="w3-card-16" alt="" src="http://www.placehold.it/300x300/ff0000/000000?text=Article1" width="100%"></div>
		<div class="w3-col m4 w3-padding w3-padding"><img class="w3-card-16" alt="" src="http://www.placehold.it/300x300/00ff00/000000?text=Article2" width="100%"></div>
		<div class="w3-col m4 w3-padding w3-padding"><img class="w3-card-16" alt="" src="http://www.placehold.it/300x300/0000ff/ffffff?text=Article3" width="100%"></div>
	</article>
	
	<article class="w3-row">
			<div class="w3-col m3 w3-padding w3-padding"><img class="w3-card-16" alt="" src="http://www.placehold.it/300x500/0000ff/ffffff?text=Article4" width="100%"></div>
			<div class="w3-col m3 w3-padding w3-padding"><img class="w3-card-16" alt="" src="http://www.placehold.it/300x500/0000ff/ffffff?text=Article5" width="100%"></div>
			<div class="w3-col m3 w3-padding w3-padding"><img class="w3-card-16" alt="" src="http://www.placehold.it/300x500/0000ff/ffffff?text=Article6" width="100%"></div>
			<div class="w3-col m3 w3-padding w3-padding"><img class="w3-card-16" alt="" src="http://www.placehold.it/300x500/0000ff/ffffff?text=Article7" width="100%"></div>
	</article>

</div>

<footer class="w3-row w3-brown w3-margin">
		<h1>Footer</h1>
</footer>


</body>
</html>