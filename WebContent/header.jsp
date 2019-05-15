<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<%@ page import="model.User" %>
    <p>Welcome To My Clinic</p>
    <a href="index.jsp">homepage</a>
    <%
    	User user = (User) session.getAttribute("authenticated_user");
    	if(user != null) {
    %>
    	<span> | </span> 
    	<span>Hello, <% out.print(user.getUsername()); %></span>
    	<span> | </span>
    	<a href="UserServlet?method=logOut">Logout</a>
    <% 
    	} else {
    %>
    	<span> | </span> 
	    <a href="signin.jsp">Login</a>
	    <span> | </span> 
	    <a href="signup.jsp">Rigister</a>
    <%
    	}
    %>
    <hr>
</header>