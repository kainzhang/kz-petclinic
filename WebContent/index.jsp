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
		<a href="PetServlet?method=showPets&pageIndex=1" id="index-content-img-1">
			<div id="index-content-img-title">
				<p>Our little</p>
				<p>&nbsp;&nbsp;Customers</p>
			</div>
		</a>
		<a href="VetServlet?method=showVets&pageIndex=1" id="index-content-img-2">
			<div id="index-content-img-title">
				<p>&nbsp;&nbsp;Super</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;Ace Vets</p>
			</div>
		</a>
		<a href="OwnerServlet?method=showOwners&pageIndex=1" id="index-content-img-3">
			<div id="index-content-img-title">
				<p>Loyal VIP</p>
				<p>&nbsp;&nbsp;Customers</p>
			</div>
		</a>
		<a href="" id="index-content-img-4">
			<div id="index-content-img-title">
				<p>About</p>
				<p>LOKKA Clinic</p>
			</div>
		</a>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>