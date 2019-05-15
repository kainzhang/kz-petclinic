<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIGN UP</title>
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
    
    <form action="UserServlet?method=signUp" method="post">
        <label for="">用户名</label>
        <input type="text" name="username" /><br>
        <font color="red">
            <%
                if(request.getAttribute("message-username")!= null)
                    out.print(request.getAttribute("message-username"));
            %>
        </font>

        <label for="">密码</label>
        <input type="password" name="password"><br>
        <font color="red">
                <%
                    if(request.getAttribute("message-password")!= null)
                        out.print(request.getAttribute("message-password"));
                %>
        </font>

        <label for="">确认密码</label>
        <input type="password" name="confpass"><br>
        <font color="red">
                <%
                    if(request.getAttribute("message-confpass")!= null)
                        out.print(request.getAttribute("message-confpass"));
                %>
        </font>
		
		<div id="submit">
        	<input type="submit" value="注册"/>
       	</div>
    </form>
</body>
</html>