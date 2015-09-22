<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>template.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	.home-animation {
		animation: scale 3s infinite;
	}
	
	.scale-ani {
	
	}
	
	.move-ani {
	
	}
	
	.rotate-ani {
	
	}
	
	@keyframes scale {
		from {
			transform: scale(0.5, 0.5);
		}
		to {
			transform: scale(1.5, 1.5);
		}
	}
	
	@keyframes move {
		from {
			transform: move(0.5, 0.5);
		}
		to {
			transform: move(2, 2);
		}
	}
	
	@keyframes rotate {
		from {
			transform: rotateX(0deg);
		}
		to {
			transform: rotateX(360deg);
		}
	}

</style>
<script type="text/javascript">
$(document).ready(function() {
	var anis = ["scale-animation",
	            "move-animation",
	            "rotate-animation"];
	
	var index=0;
	
	setInterval(function() {
		index++;
		/*
		 * removeClass
		 */
		$.each(anis, function(index, value) {
			$('#home').removeClass(value);
		});
		
		/*
		 * removeClass
		 */
		
		
		
		console.log("index[" + index + "] = " + anis[index]);
	}, 1000);
	
});
</script>


</head>
<body>
<h1>index</h1>
<%-- <c:redirect url="/member/regist"/> --%>

<div class="panel panel-default">
	<h1 class="panel-head home-animation">World</h1>
</div>


</body>
</html>