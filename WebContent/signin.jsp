<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIGN IN</title>
<style type="text/css">
	form{padding:15px; width: 300px;height:300px;text-align: center;}
	#submit{padding: 10px}
	#submit input{width: 50px;height: 25px;}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%
    	if(user != null)
    		response.sendRedirect("index.jsp");
    %>
	<div class="wrapper">
		<form action="UserServlet?method=signIn" method="post">
			<label>用户名:</label>
				<input type="text" name="username" value="${param.username}"/><br><br>
			<label>密码：</label>
				<input type="password" name="password"/><br>
				
			<font color="red">
				<%
					if(request.getAttribute("message")!= null){
						out.print(request.getAttribute("message"));
					}
				%>
			</font>
			
			<div id="submit">
				<input type="submit" value="登录"/>
			</div>
		</form>
	
	</div>
</body>
</html>
