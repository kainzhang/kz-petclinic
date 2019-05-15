<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LOKKA's Pet Clinic</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<a href="VetServlet?method=showVets">查看兽医</a> <br>
	<a href="SpeciesServlet?method=showSpecies">查看物种</a> <br>
	<a href="SpecialtyServlet?method=showSpecialties">查看专业</a> <br>
	<a href="OwnerServlet?method=showOwners">查看顾客</a> <br>
	<a href="PetServlet?method=showPets">查看宠物</a> <br>
</body>
</html>