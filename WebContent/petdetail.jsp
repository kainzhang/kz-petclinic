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
	<link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<% 
		int flag = (Integer)request.getAttribute("flag");
		Pet pet = null;
		String[] dateArray={};
		String path = request.getContextPath();
     	String picPath="/media/";
     	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + picPath;
		
		if(flag==0) {            
			pet = (Pet) request.getAttribute("aimPet");
			String bday = pet.getBday();
			dateArray = bday.split("-");
			basePath += pet.getPic(); 
		}      
     	
     	
     %>
<script src="js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="js/cropPic.js"></script>
    <link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" />
    <style rel="stylesheet" href="css/cropPic.css" type="text/css" ></style>
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
	<div id="detail-page">

		
	<div id="detail-page-pane-1">
			<%//选择图片 %>
	    <div class="container">
	      <div class="row">
	        <div class="span12">
	          <input type="file" onchange="selectPic(this)" id="selectBtn" />
	          <a onclick="cutPic(),updataPic()">剪切</a>
	          <div class="jc-demo-box">
	            <div id="shade"> </div>
	            <img src="" id="target" alt="" />
	            <div id="preview-pane">
	              <div class="preview-container">
	                <img src="" class="jcrop-preview" alt="" id="pic2" />
	              </div>
	            </div>
	            <p id="hint"> </p>
	          </div>
	        </div>
	      </div>
	    </div>
	</div>
	<form 
		<% if(flag==0){ %>
		action="PetServlet?method=updatePet"
		<% } else { %>
		action="PetServlet?method=insertPet"
		<% } %>  method="POST" name="thisform" id="petForm">
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
				<a href="javascript:history.go(-1);">BACK</a>
			</td>
			<td colspan="2">
                <input type="text" name="pic" style="display:none;" value="<%=basePath%>" id="pic">
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