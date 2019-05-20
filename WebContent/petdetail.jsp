<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Pet" %>

<% 
	int flag = (Integer)request.getAttribute("flag");
	Pet pet = null;
	String[] dateArray={};
	String path = request.getContextPath();
   	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path +"/media/";
	String picPath="";
	if(flag==0) {            
		pet = (Pet) request.getAttribute("aimPet");
		String bday = pet.getBday();
		dateArray = bday.split("-");
		picPath += pet.getPic(); 
		basePath+= pet.getPic(); 
	}      	
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Update Pet</title>
	<script type="text/javascript" src="js/date.js" ></script>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	
	<link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" />
	<link rel="stylesheet" href="css/cropPic.css" type="text/css" />
	
	
  
    <style type="text/css">
    	#shade{
        width:350px;
        height: 350px;
        position: relative;
        color: #000;
        z-index: 8;
        background-size: cover;
        background-image:url(<%=basePath%>);
        }
    </style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="detail-page">
	<div class="detail-page-pane-1">
		<div class="jc-demo-box">
		 	<div id="shade"></div>
			<img src="" id="target" alt="" />
		</div>
		<input type="file" onchange="selectPic(this)" id="selectBtn">
		<input type="button" onclick="cutPic(),updataPic('Pet')" value="Confirm">
		<p id="hint" class="jc-hint">Note: Please crop and confirm the picture after uploading it!</p>
	</div>
	<form 
		<% if(flag==0){ %>
		action="PetServlet?method=updatePet"
		<% } else { %>
		action="PetServlet?method=insertPet"
		<% } %>  method="POST" name="thisform" id="petForm">
	<div class="detail-page-pane-2">   
		
		<table>
		<tr>
			<td><span></span></td>
			<td colspan="3">
				<input type="hidden" name="id" <% if(flag==0){ %> value="<%=pet.getId()%>" <% } %> readonly="readonly">
			</td>
		<tr>
			<td><span>Name</span></td>
			<td colspan="3">
				<input type="text" name="name" <% if(flag==0){ %> value="<%=pet.getName()%>" <% } %>>
			</td>
		</tr>
		<tr>
			<td><span>Species</span></td>
			<td colspan="3">
			<select class="select-m" name="speciesid">
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
			<td>
			<select class="select-s" name="YY" onchange="ynd(this.value)">
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
			<td colspan="3">
				<select class="select-m" name="ownerid">
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
				<a href="javascript:history.go(-1);">BACK</a>
			</td>
			<td colspan="2">
                <input type="text" name="pic" style="display:none;" value="<%=picPath%>" id="pic">
				<input type="submit" value="SUBMIT">
			</td>
		</tr>
		</table>
	</div>
	</form>
	</div>
	<%@ include file="footer.jsp" %>
	<script type="text/javascript" src="js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="js/cropPic.js"></script>
</body>
</html>