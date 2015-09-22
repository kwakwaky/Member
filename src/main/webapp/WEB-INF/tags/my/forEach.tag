<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="var" 	required="true" type="java.lang.String"  rtexprvalue="false"%>
<%@ attribute name="begin" 	required="true" type="java.lang.Integer" rtexprvalue="false"%>
<%@ attribute name="end" 	required="true" type="java.lang.Integer" rtexprvalue="false"%>
<%@ variable name-from-attribute="var" 
			 alias="avar" 
			 variable-class="java.lang.Integer"
			 scope="NESTED"%>

<%
	for ( int i = begin ; i <= end ; i++ ){
	jspContext.setAttribute("avar", i);
%>	
	<jsp:doBody/>
<%	
	}
%>