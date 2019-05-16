<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIGN IN</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%
    	if(user != null)
    		response.sendRedirect("index.jsp");
    %>
	<div id="signin-content">
        <div id="signin-content-pane">
            <h1>Please sign in.</h1>
            <form action="UserServlet?method=signIn" method="post">
                <input type="text" placeholder="LOKKA ID" name="username" value="${param.username}"><br>
                <span id="username-hint"></span><br>
                <input type="password" placeholder="PASSWORD" name="password"><br>
                <span id="password-hint"></span><br>
                
                <font color="red">
				<%
					if(request.getAttribute("message")!= null){
						out.print(request.getAttribute("message"));
					}
				%>
				</font>
                
                <input type="submit" value="Sign in">
                <a href="signup.jsp">No account? Create one now!</a>
            </form>
        </div>
    </div>
    <%@ include file="footer.jsp"%>
</body>
</html>
