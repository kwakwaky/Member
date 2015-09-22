<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>display.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	span, img {
		border: 1px solid red;
		height: 150px;
		width: 150px;
		float: right;
/* 		display: inline-block; */
	}
	
	

</style>
</head>
<body>
<h1>display : inline | block | inline-block</h1>

<span>display span inline</span>
<img alt="100x100" src="http://www.placehold.it/100x100" style="vertical-align: bottom;">

<p> <!-- p태그는 block 레벨 -->
9월 09, 2015 9:24:16 오전 org.apache.tomcat.util.digester.SetPropertiesRule begin
경고: [SetPropertiesRule]{Server/Service/Engine/Host/Context} Setting property 'source' to 'org.eclipse.jst.jee.server:Member' did not find a matching property.
9월 09, 2015 9:24:16 오전 org.apache.catalina.startup.VersionLoggerListener log
정보: Server version:        Apache Tomcat/8.0.23
9월 09, 2015 9:24:16 오전 org.apache.catalina.startup.VersionLoggerListener log
정보: Server built:          May 19 2015 14:58:38 UTC
9월 09, 2015 9:24:16 오전 org.apache.catalina.startup.VersionLoggerListener log
정보: Server number:         8.0.23.0
9월 09, 2015 9:24:16 오전 org.apache.catalina.startup.VersionLoggerListener log
정보: OS Name:               Windows 7
9월 09, 2015 9:24:16 오전 org.apache.catalina.startup.VersionLoggerListener log
정보: OS Version:            6.1
9월 09, 2015 9:24:16 오전 org.apache.catalina.startup.VersionLoggerListener log
정보: Architecture:          x86
9월 09, 2015 9:24:16 오전 org.apache.catalina.startup.VersionLoggerListener log
정보: Java Home:             C:\Program Files\Java\jdk1.7.0_76\jre
9월 09, 2015 9:24:16 오전 org.apache.catalina.startup.VersionLoggerListener log
정보: JVM Version:           1.7.0_76-b13
9월 09, 2015 9:24:16 오전 org.apache.catalina.startup.VersionLoggerListener log
</p>

</body>
</html>