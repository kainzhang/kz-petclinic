<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet/less" type="text/css" href="css/styles.less" />
	<link rel="stylesheet" type="text/css" href="css/popupwindow.css" />
	<script>
		less = {
		  env: "development",
		  async: true,
		  fileAsync: true,
		  poll: 1000,
		  functions: {},
		  dumpLineNumbers: "comments",
		  relativeUrls: false,
		  rootpath: ":/a.com/"
		};
	</script>
</head>
<header>
    <a id="logo" href="index.jsp">LOKKA</a>
    <ul id="nav">
    	<li class="nav-item"><a class="fc-item" href="#">WELCOME TO LOKKA<span class="arrow arrow-down"></span></a>&nbsp;
            <ul class="nav-item-child"> 
                <li><a href="VetServlet?method=showVets&pageIndex=1">Check Veterinarians</a></li> 
                <li><a href="SpeciesServlet?method=showSpecies">Check Species</a></li> 
                <li><a href="SpecialtyServlet?method=showSpecialties">Check Specialties</a></li> 
                <li><a href="OwnerServlet?method=showOwners&pageIndex=1">Check Customers</a></li>
                <li><a href="PetServlet?method=showPets&pageIndex=1">Check Pets</a></li>
                <li><a href="RecordServlet?method=showRecords&pageIndex=1">Check Records</a></li>
            </ul> 
		</li>
        <li class="nav-item"><a class="fc-item" href="#">COMMUNITY</a></li> 
        <li class="nav-item"><a class="fc-item" href="#">IDEAS</a></li> 
        <li class="nav-item"><a class="fc-item" href="#">SUPPORT</a></li> 
    </ul>
    <div id="user-interaction">
    	<%
	    	User user = (User) session.getAttribute("authenticated_user");
	    	if(user != null) {
	    %>
	    	<span>Hello, <% out.print(user.getUsername()); %></span>
	    	<a href="UserServlet?method=logOut"><span>Logout</span></a>
	    <% 
	    	} else {
	    %>
		    <a href="signin.jsp"><span>Login</span></a>
		    <a href="signup.jsp"><span>Register</span></a>
	    <%
	    	}
	    %>
    </div>
    <script type="text/javascript" src="js/less.min.js" ></script>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/lokka.js"></script>
</header>