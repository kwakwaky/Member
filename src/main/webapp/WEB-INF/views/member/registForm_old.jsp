<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registForm.jsp</title>
<!-- 일반적으로 head태그 안에 위치시킴 -->
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
/* 	body { */
/* 		display: inline-block; */
/* 		transform: rotate(-180deg); */
/* 		padding-left: 300px; */
/* 		padding-top: 200px; */
/* 	} */

	.fade {
		opacity: 0.5;
	}

	form {
		max-width: 400px;
		border-top: 1px double gray;
 		padding: 100px;
	}
	
	h1 {
		font-family: 맑은 고딕;
		text-align: center;
		text-shadow: 2px 2px 1px #8C8C8C;
	}
	
	#member {
 		margin: auto auto;
	}
	
	.none {
		display: none;
	}
	
	.block {
		display: block;
	}
	
</style>
</head>
<body>

<button id="xxx1">display none</button>
<button id="xxx2">display block</button>
<script type="text/javascript">
	$('#xxx1').click(function() {
		
		$('h1').addClass('none')
			   .removeClass('block');
	});
	$('#xxx2').click(function() {
		$('h1').addClass('block')
			   .removeClass('none');
	});

</script>

<h1><spring:message code="member.regist.title"/></h1>

<form:form commandName="member" action="/member/regist" method="POST">

	<!-- Email -->
	<div class="form-group">
	<label for="email"><spring:message code="member.regist.email"/></label>
	<form:errors path="email"/>
	<form:input path="email" cssClass="form-control"/>
	</div>
	
	<!-- Name -->
	<div class="form-group">
	<label for="name"><spring:message code="member.regist.name"/></label>
	<form:errors path="name"/>
	<form:input path="name" cssClass="form-control"/>
	</div>
	
	<!-- Password -->
	<div class="form-group">
	<label for="password"><spring:message code="member.regist.password"/></label>
	<form:errors path="password"/>
	<form:input path="password" cssClass="form-control"/>
	</div>

	<input type="submit" value="회원 가입">
</form:form>




</body>
</html>