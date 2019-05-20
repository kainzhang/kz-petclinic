<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Owner" %>

<% 
	int flag = (Integer)request.getAttribute("flag");
	Owner owner = null;
	String path = request.getContextPath();
   	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path +"/media/";
	String picPath="";
	if(flag==0) {            
		owner = (Owner) request.getAttribute("aimOwner");
		picPath += owner.getPic(); 
		basePath+= owner.getPic(); 
	}      	
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Update Owner</title>
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
		<input type="button" onclick="cutPic(),updataPic('Owner')" value="Confirm">
		<p id="hint" class="jc-hint">Note: Please crop and confirm the picture after uploading it!</p>
	</div>
	<form 
		<% if(flag==0){ %>
		action="OwnerServlet?method=updateOwner"
		<% } else { %>
		action="OwnerServlet?method=insertOwner"
		<% } %>  method="POST" name="thisform" id="ownerForm">
	<div class="detail-page-pane-2">   
		
		<table>
		<tr>
			<td><span></span></td>
			<td colspan="3">
				<input type="hidden" name="id" <% if(flag==0){ %> value="<%=owner.getId()%>" <% } %> readonly="readonly">
			</td>
		<tr>
			<td><span>Name</span></td>
			<td colspan="3">
				<input type="text" name="name" <% if(flag==0){ %> value="<%=owner.getName()%>" <% } %>>
			</td>
		</tr>
		<tr>
			<td><span>telephone</span></td>
			<td colspan="3" >
                <input type="text" name="tel" <% if(flag==0){ %>value="<%=owner.getTel()%>"<% } %>>
		</tr>
		<tr>
			<td><span>Addr</span></td>
			<td colspan="3">
                    <input type="text" name="addr" <% if(flag==0){ %>value="<%=owner.getAddr()%>"<% } %>>
            </td>
		</tr>
		<%if(flag==0){ %>
		<tr>
			<td><span>Pets</span></td>
			<td colspan="3"  style="text-align:left;">
				<div id="owner-pet">
					<c:forEach items="${ppList}" var="pItem">
                	<a href="PetServlet?method=showPet&id=${pItem.getId()}"> ${pItem.getName()}</a>
					</c:forEach>
				</div>
            </td>
		</tr>
			
		<% }%>

		<tr>
			<td></td>
			<td>
			<% if(flag==0){ %>
				<a href="OwnerServlet?method=deleteOwner&id=<%=owner.getId()%>">DELETE</a>
			<% } %>
			</td>
			<td>
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