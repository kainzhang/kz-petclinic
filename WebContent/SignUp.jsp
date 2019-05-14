<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
</head>
<body>
    <form action="SignUpServlet" method="post">
        <label for="">用户名</label>
        <input type="text" name="username" /><br>
        <font color="red">
            <%
                if(request.getAttribute("message-username")!= null) {
                    out.print(request.getAttribute("message-username"));
                }
            %>
        </font>

        <label for="">密码</label>
        <input type="password" name="password"><br>
        <font color="red">
                <%
                    if(request.getAttribute("message-password")!= null) {
                        out.print(request.getAttribute("message-password"));
                    }
                %>
        </font>

        <label for="">确认密码</label>
        <input type="password" name="confpass"><br>
        <font color="red">
                <%
                    if(request.getAttribute("message-confpass")!= null) {
                        out.print(request.getAttribute("message-confpass"));
                    }
                %>
        </font>

        <input type="submit" value="注册"/>
    </form>
</body>
</html>