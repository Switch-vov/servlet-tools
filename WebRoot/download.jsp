<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>DownLoad</title>
	</head>
	<body>
		<h2>直接指向路径下载</h2>
		<%-- 直接指向路径下载会将中文进行编码,所以下载不会指向正确的中文路径，可以通过将路径修改为英文进行修复 --%>
		<a href="download/飞机.gif">飞机.gif</a><br/>
		<%-- 但是可以发现，如果这种类型是浏览器能够识别的类型，那么将不会启动下载器，而是直接显示文件 --%>
		<a href="download/Book.pdf">Book.pdf</a><br/>
		<%-- 不能识别，则会显示下载器 --%>
		<a href="download/java_ee_api.chm">java_ee_api.chm</a><br/>
		<h2>Servlet实现下载</h2>
		<%-- get方式中文使用的是ios-8859-1编码 --%>
		<a href="DownloadServlet?fileName=飞机.gif">飞机.gif</a><br/>
		<a href="DownloadServlet?fileName=Book.pdf">Book.pdf</a><br/>
		<a href="DownloadServlet?fileName=java_ee_api.chm">java_ee_api.chm</a><br/>
	</body>
</html>