<%@ tag language="java" pageEncoding="UTF-8"%>
<%-- <%@ tag body-content="scriptless" %> 스크립트릿만 안되고 나머지 다 됨 --%>
<%@ tag body-content="empty" %>
<%@ attribute name="test" required="ture" type="java.lang.Boolean"%>
<%@ attribute name="cls"%>

<%
	if (test){
%>
	<button class="${cls}">my:login Log Out</button>
<%
	} else {
%>
	<button class="${cls} btn-primary">my:login Log In</button>
<%
	}
%>
