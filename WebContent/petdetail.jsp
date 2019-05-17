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
		Pet pet = (Pet) request.getAttribute("aimPet");
		//session.removeAttribute("aimPet");
	%>
	<div id="detail-page">
	<div id="detail-page-pane-1">
		<p>占位</p>
	</div>
	<div id="detail-page-pane-2">
		<form action="PetServlet?method=updatePet" method="POST" name="petform">
		<table>
		<tr>
			<td><span>ID</span></td>
			<td colspan="4">
				<input type="text" value="<%=pet.getId()%>" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td><span>Name</span></td>
			<td colspan="4">
				<input type="text" name="name" value="<%=pet.getName()%>">
			</td>
		</tr>
		<tr>
			<td><span>Species</span></td>
			<td colspan="4">
				<select name="speciesid">
				<option value='<%=pet.getSpeciesId()%>' disabled selected style='display:none;'><%=pet.getSpecies()%></option>
				<c:forEach items="${splist}" var="item2">
					<option value ="${item2.id }">${item2.name }</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td><span>B-Day</span></td>
			<td colspan="2">
                <select class="select-m" name="YY" onchange="ynd(this.value)" >
                <option class="display-none" value="" disabled selected>YYYY</option>
                </select>
            </td>
            <td>
                <select class="select-s" name="MM" onchange="mnd(this.value)">
                <option class="display-none" value="" disabled selected>MM</option>
                </select>
            </td>
            <td>
                <select class="select-s" name="DD" onload="splitdate(<%=pet.getBday()%>)">
                <option class="display-none" value="" disabled selected>DD</option>
                </select>
            </td>
		</tr>
		<tr>
			<td><span>Owner</span></td>
			<td colspan="4">
				<select name="ownerid">
				<option value='<%=pet.getOwnerId()%>' disabled selected style='display:none;'><%=pet.getOwner()%></option>
				<c:forEach items="${ownerlist}" var="item3">
					<option value ="${item3.id }">${item3.name }</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">
				 <%String ref = request.getHeader("REFERER");%>
			      <input type="button" id="backBtn" name="button" class="button_return" value="BACK" 
				onclick="javascript:window.location='<%=ref%>'">
				
			</td>
			<td colspan="2">
				<input type="submit" value="SUBMIT">
			</td>
		</tr>
		</table>
		
		</form>
		<%--<input type="text" name="bday" value="<%=pet.getBday()%>"> --%>
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>