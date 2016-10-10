<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset=utf-8">
		<title>Login</title>
	</head>
	<!-- 引入jquery库 -->
	<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resource/js/jquery-1.11.0.js"></script>
	<!-- 写一个验证码点击刷新功能 -->
	<script type="text/javascript">
		$(function() {
			$("#verifyImg").click(function() {
				$(this).prop("src", "${pageContext.servletContext.contextPath }/VerifyServlet?t=" + new Date().getTime());
			});
		});
	</script>
	<body>
		<%-- 演示验证码功能 --%>
		<form action="#" method="post">
				用户名：<input type="text" name="username" /><br /><br />
				密码：<input type="password" name="password" /><br /><br />
				验证码：<input type="text" name="verifyCode" />
				<img src="${pageContext.servletContext.contextPath }/VerifyServlet" id="verifyImg"><br />
				<input type="submit" value="登录" />
		</form>
	</body>
</html>