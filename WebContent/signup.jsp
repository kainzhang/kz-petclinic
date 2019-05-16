<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIGN UP</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    
    <%
    	if(user != null)
    		response.sendRedirect("index.jsp");
    %>
    
		<%--
			 <font color="red">
            <%
                if(request.getAttribute("message-username")!= null)
                    out.print(request.getAttribute("message-username"));
            %>
	        </font>
	        <font color="red">
	                <%
	                    if(request.getAttribute("message-password")!= null)
	                        out.print(request.getAttribute("message-password"));
	                %>
	        </font>
	        <font color="red">
	                <%
	                    if(request.getAttribute("message-confpass")!= null)
	                        out.print(request.getAttribute("message-confpass"));
	                %>
       		</font>
		 --%>
    
      <div id="signup-content">
        <div id="signup-content-banner">
            <h1>Create Your LOKKA ID</h1>
        </div>
        <div id="signup-content-pane">
		<form action="UserServlet?method=signUp" method="post">
            <table>
                <tr><td colspan="4"><input id="username" type="text" name="username" placeholder="Username" maxlength="16"></td></tr>
                <tr><td colspan="4" id="username-hint">This will be your new LOKKA ID</td></tr>
                <tr><td colspan="4"><input id="password" type="password" name="password" placeholder="Password" maxlength="16"></td></tr>
                <tr><td colspan="4"><input id="confpass" type="password" name="confpass" placeholder="Confirm password" maxlength="16"></td></tr>
                <tr><td></td></tr>
                <tr><td colspan="4"><input type="submit" value="Sign up" ></td></tr>
            </table>
            </form>
        </div>
    </div>
    
    
    <%@ include file="footer.jsp"%>
</body>
</html>