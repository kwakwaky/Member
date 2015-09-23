<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>template.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>

<style type="text/css">

	
	
</style>
<script type="text/javascript">
window.onload = function() {
	
	var xhr;
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xhr = new ActiveXObject("Msxml.XMLHTTP");
	} else {
		throw new Error("Ajax를 지원하지 않는 브라우저이다.")
	}
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status >= 200 && xhr.status < 300) {
				document.getElementById('container').innerHTML = xhr.responseText;
			}
		}
	}
	
	xhr.open('GET', 'recource.html');
	xhr.send('');
};
</script>


</head>
<body>
<h1>ajax</h1>
<div id="container"></div>

</body>
</html>