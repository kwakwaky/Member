<%@tag import="java.security.InvalidParameterException"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty"%>
<%@ attribute name="var" required="true" rtexprvalue="false"%> <%-- type은 디폴트가 String --%>
<%@ attribute name="scope"%>
<%-- var로 들어온 변수의 값을 EL변수로 사용 --%>
<%@ variable name-from-attribute="var"  
 			 scope="AT_BEGIN"
			 alias="avar"
 			 variable-class="java.lang.String"%> 

<%
	if( scope == null) {
		jspContext.removeAttribute("avar");
		request.removeAttribute(var);
		session.removeAttribute(var);
		application.removeAttribute(var);
	} else {
		jspContext.setAttribute("avar", null);
		switch (scope) {
		case "page" :
			jspContext.removeAttribute("avar");
			break;
		case "request" :
			request.removeAttribute(var);
			break;
		case "session" :
			session.removeAttribute(var);
			break;
		case "application" :
			application.removeAttribute(var);
			break;
		default :
			throw new InvalidParameterException("scope error");
		}
	}
%>