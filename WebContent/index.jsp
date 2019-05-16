<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LOKKA's Pet Clinic</title>
	<style type="text/css">
	#content {
		height: 600px;
	}
	</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="index-content">
		<a href="PetServlet?method=showPets" id="index-content-img-1">
		</a>
		<a href="VetServlet?method=showVets" id="index-content-img-2">
			
		</a>
		<a href="OwnerServlet?method=showOwners" id="index-content-img-3">
			
		</a>
		<a href="" id="index-content-img-4">
			
		</a>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>