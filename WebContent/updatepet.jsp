<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Pet" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Pet</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<% 
		Pet pet = (Pet) session.getAttribute("aimPet");
		//session.removeAttribute("aimPet");
	%>
	<div id="pet-update">
		<form action="PetServlet?method=updatePet" method="POST">
			<label>ID</label>
			<input type="text" value="<%=pet.getId()%>" readonly="readonly"> <br>
			<label>Name</label>
			<input type="text" name="name" value="<%=pet.getName()%>"> <br>
			<label>Species</label>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
			<input type="text" name="species" value="<%=pet.getSpecies()%>"> <br>
			<label>BirthDay</label>
			<input type="text" name="bday" value="<%=pet.getBday()%>"> <br>
			<label>Owner</label>
			<select name="ownerid">
				<c:forEach items="${ownerlist}" var="item3">
					<option value ="${item3.id }">${item3.name }</option>
				</c:forEach>
			</select>
			<br>
			<input type="submit" value="确认修改">
		</form>
		<%String ref = request.getHeader("REFERER");%>
	    <input type="button" id="backBtn" name="button" class="button_return" value="返回"  
		onclick="javascript:window.location='<%=ref%>'">
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>