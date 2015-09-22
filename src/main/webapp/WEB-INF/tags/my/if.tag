<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="test" required="ture" type="java.lang.Boolean"%>

<%
	if (test){
%>
	<jsp:doBody/>
<%
	}
%>


