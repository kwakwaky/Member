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


	form {
		max-width: 600px;
		border-top: 1px double gray;
 		padding: 100px;
	}
	
	.center {
		font-family: 맑은 고딕;
		text-align: center;
		text-shadow: 2px 2px 1px #8C8C8C;
	}
	
	#member {
 		margin: auto auto;
	}
	<%-- ^= gen : 앞이 gen으로 시작하는 문자 --%>
	label[for^=gen], label[for^=hobby] {
		padding-left: 5px;
		padding-right: 10px;
		color: orange;
	}
	
	span[id$='errors'] {
		color: red;
		float: right;
	}
	
	
</style>

<script type="text/javascript">

// 준비(ready) 핸들러
// $(document).ready(function() { // 페이지를 다 읽은 시점에서 이벤트 발생
// 	$('form').slideToggle(1000).slideToggle(1000)
// 			 .fadeOut(1000).fadeIn(1000, function() {
// 				 $('label[for^=gen]').css('background-color', 'gray');
// 			});
	
// });



</script>

</head>
<body>



<h1><a href="regist"><spring:message code="member.regist.title"/></a></h1>

<form:form commandName="member" action="regist" method="POST">

	<!-- Email -->
	<div class="form-group">
	<label for="email"><spring:message code="member.regist.email"/></label>
	<form:errors path="email"/><form:errors/>
	<form:input path="email" cssClass="form-control"/>
	</div>
	
	<!-- Password -->
	<div class="form-group">
	<label for="password"><spring:message code="member.regist.password"/></label>
	<form:errors path="password"/>
	<form:password path="password" cssClass="form-control"/>
<%-- 	<form:input path="password" cssClass="form-control"/> --%>
	</div>
	
	<!-- Name -->
	<div class="form-group">
	<label for="name"><spring:message code="member.regist.name"/></label>
	<form:errors path="name"/>
	<form:input path="name" cssClass="form-control"/>
	</div>
	
	<!-- Gender -->
	<div class="form-group">
	<div><spring:message code="member.regist.gender"/></div>
	
	
	<form:errors path="gender"/>
	<form:radiobuttons path="gender" items="${gender}"/>
	</div>
	
	<!-- Hobby -->
	<div class="form-group">
	<div><spring:message code="member.regist.hobby"/></div>
	<form:checkboxes items="${hobby}" path="hobby" itemLabel="label" itemValue="code"/>
	<form:errors path="hobby"/>
	</div>
	
	<!-- Comment -->
	<div class="form-group">
	<div><spring:message code="member.regist.comment"/><form:errors path="comment"/></div>
	<form:textarea path="comment" cssClass="form-control" rows="10"/>
	
	</div>
	
	<!-- Email Reception true/false -->
	<div class="form-group">
	<spring:message code="member.regist.reception"/>
	<form:errors path="reception"/>
	<form:checkbox path="reception"/>
	</div>
	
	

	<input type="submit" value="회원 가입">
</form:form>





</body>
</html>