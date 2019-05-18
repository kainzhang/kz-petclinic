<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Pet" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Update Pet</title>
	<script type="text/javascript" src="js/date.js" ></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<% 
		int flag = Integer.parseInt(request.getParameter("flag"));
		Pet pet = null;
		String[] dateArray={};
		if(flag==0) {            
			pet = (Pet) request.getAttribute("aimPet");
			String bday = pet.getBday();
			dateArray = bday.split("-");
		}      
	%> 
	<div id="detail-page">
	<form 
		<% if(flag==0){ %>
		action="PetServlet?method=updatePet"
		<% } else { %>
		action="PetServlet?method=insertPet"
		<% } %>  method="POST" name="thisform">
		
	<div id="detail-page-pane-1">
		<p>占位</p>
	</div>
	<div id="detail-page-pane-2">   
		
		<table>
		<tr>
			<td><span></span></td>
			<td colspan="4">
				<input type="hidden" name="id" <% if(flag==0){ %> value="<%=pet.getId()%>" <% } %> readonly="readonly">
			</td>
		<tr>
			<td><span>Name</span></td>
			<td colspan="4">
				<input type="text" name="name" <% if(flag==0){ %> value="<%=pet.getName()%>" <% } %>>
			</td>
		</tr>
		<tr>
			<td><span>Species</span></td>
			<td colspan="4">
				<select name="speciesid">
				<% if(flag==0){ %>
				<option value='<%=pet.getSpeciesId()%>' selected style='display:none;'><%=pet.getSpecies()%></option>
				<% } %>
				<c:forEach items="${splist}" var="item2">
					<option value ="${item2.id }">${item2.name }</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td><span>B-Day</span></td>
			<td colspan="2">
			<select class="select-m" name="YY" onchange="ynd(this.value)">
             	<% if(flag==0) {%>
             		<option value="<%=dateArray[0]%>" selected><%=dateArray[0]%></option>
             	<%} else { %>
               	<option value="" disabled selected>YYYY</option>
               	<% } %>
            </select>
            <%-- <input type="text" name="bday" <% if(flag==0){ %>value="<%=pet.getBday()%>"<% } %>> --%>
			</td>
            <td>
            <select class="select-s" name="MM" onchange="mnd(this.value)">
                <% if(flag==0) {%>
              		<option value="<%=dateArray[1]%>" selected><%=dateArray[1]%></option>
              	<%} else { %>
                	<option value="" disabled selected>MM</option>
                <% } %>
            </select>
            </td>
            <td>
            <select class="select-s" name="DD" >
                 <% if(flag==0) {%>
              		<option value="<%=dateArray[2]%>" selected><%=dateArray[2]%></option>
              	<%} else { %>
                	<option value="" disabled selected>DD</option>
                <% } %>
            </select>
            </td>
		</tr>
		<tr>
			<td><span>Owner</span></td>
			<td colspan="4">
				<select name="ownerid">
				<% if(flag==0){ %>
				<option value='<%=pet.getOwnerId()%>' selected style='display:none;'><%=pet.getOwner()%></option>
				<% } %>
				<c:forEach items="${ownerlist}" var="item3">
					<option value ="${item3.id }">${item3.name }</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<% if(flag==0){ %>
				<a href="PetServlet?method=deletePet&id=<%=pet.getId()%>">DELETE</a>
			<% } %>
			</td>
			<td >
				<a href="PetServlet?method=showPets">BACK</a>
			</td>
			<td colspan="2">
				<input type="submit" value="SUBMIT">
			</td>
		</tr>
		</table>
		
	</div>
	</form>
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>