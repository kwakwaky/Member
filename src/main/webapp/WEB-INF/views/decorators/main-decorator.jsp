<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" tagdir="/WEB-INF/tags/sitemesh"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><sitemesh:write property="title"/></title>

<sitemesh:write property="head"/>

<style type="text/css">
	#world, #employee {
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

<c:url value="/login" var="login"/>
<c:url value="/logout" var="logout"/>

<nav class="w3-topnav w3-red w3-margin w3-card-16">
	<a href="/Member/">Home</a>
	<a href="#world">World</a>
	<a href="#employee">Employee</a>
	<c:choose>
		<c:when test="${not empty auth}">
<%-- 		<c:when test="${empty auth}"> --%>
			<a href="${logout}" class="w3-right">Logout</a>
			<a href="#" class="w3-right">${auth.name}</a>
		</c:when>
		<c:otherwise>
			<a href="${login}" class="w3-right">Login</a>
		</c:otherwise>
	</c:choose>
	
	
</nav>


<nav id="world" class="w3-container w3-sidenav w3-pink w3-margin w3-card-16">
	<a href="#">World 1</a>
	<a href="#">World 2</a>
	<a href="#">World 3</a>
	<a href="#">World 4</a>
	<a href="#">World 5</a>
	<a href="#">World 6</a>
</nav>

<c:url value="/member/regist" var="member_regist"/>
<nav id="employee" class="w3-container w3-sidenav w3-purple w3-margin w3-card-16">
	<a href="${member_regist}">Regist</a>
	<a href="#">Employee 2</a>
	<a href="#">Employee 3</a>
	<a href="#">Employee 4</a>
	<a href="#">Employee 5</a>
	<a href="#">Employee 6</a>
</nav>

<section id="main" class="w3-container w3-card-16">
	<sitemesh:write property="body"/>
</section>




</body>
</html>