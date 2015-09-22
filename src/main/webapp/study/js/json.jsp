<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>template.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>

<style type="text/css">
	p {
		transition: transform 2s, background-color 2s;
	}
	
	
</style>
<script type="text/javascript">
	var member = "";
	
// 	{					// { };  <-- 이게 하나의 객체
// 			// JSON (JavaScript Object Notation)
// 			email : "xxx@webapp.com",
// 			password : "1234",
// 			name : "홍길동",
// 			gender : "female",
// 			hobby : ["toto", "tv"],
// 			comment : "열공하세요",
// 			reception : "false"
// 	};

	$(document).ready(function() {
		
		$('button:first').on("click", function() {
			
			//Ajax(Asyncronous Javascript and xml)
			
			$.getJSON("member.json", function(member) {
				
				console.log(member);
				var message = "email = " + member.email + "<br>" +
							  "name = " + member.name + "<br>" +
							  "password = " + member.password + "<br>" +
							  "gender = " + member.gender + "<br>" +
							  "hobby = " + member.hobby + "<br>" +
							  "reception = " + member.reception;
				
				$('p:first').text(message);
				$('p:eq(1)').append(message);
				$('p:last').html(message);
				
				$.each(member.hobby, function(index, value) {
					
//					console.log("bobby [" + index + "] = " + member.hobby[index]);
					console.log("bobby [" + index + "] = " + value);
					
				});
				
				for (var i=0; i<member.hobby.length; i++) {
					console.log("bobby [" + i + "] = " + member.hobby[i]);
				}
			
			});
			
		});
		
		$('button:last').on("click", function() {
			var message = "";
			$('p').text(message);
		});
		
		$('p').on("click", function() {
			
			$(this).css({'transform':'translate(50px,1px)', 'background-color':'blue'});
			
		});
		
		
	});
	
	


</script>


</head>
<body>
<h1>template</h1>
<button>member print1</button>
<button>member print2</button>
<p>
	print first
</p>
<p>
	print
</p>
<p>
	print last
</p>



</body>
</html>