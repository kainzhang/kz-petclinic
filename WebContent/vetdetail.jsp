<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Vet" %>

<% 
	int flag = (Integer)request.getAttribute("flag");
	Vet vet = null;
	String path = request.getContextPath();
   	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path +"/media/";
	String picPath="";
	if(flag==0) {            
		vet = (Vet) request.getAttribute("aimVet");
		picPath += vet.getPic(); 
		basePath+= vet.getPic(); 
	}      	
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Vet Detail</title>
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
		<input type="button" onclick="cutPic(),updataPic('Vet')" value="Confirm">
		<p id="hint" class="jc-hint">Note: Please crop and confirm the picture after uploading it!</p>
	</div>
	<form 
		<% if(flag==0){ %>
		action="VetServlet?method=updateVet"
		<% } else { %>
		action="VetServlet?method=insertVet"
		<% } %>  method="POST" id="vetForm">
	<div class="detail-page-pane-2">   
		
		<table>
		<tr>
			<td><span></span></td>
			<td colspan="3">
				<input type="hidden" name="id" <% if(flag==0){ %> value="<%=vet.getId()%>" <% } %> readonly="readonly">
			</td>
		<tr>
			<td><span>Name</span></td>
			<td colspan="3">
				<input type="text" name="name" <% if(flag==0){ %> value="<%=vet.getName()%>" <% } %>>
			</td>
		</tr>
		<tr>  
		<% if(flag==0){ %>
			<td><span>Specialty</span></td>
			<td colspan="3">
				<ul class="common-list">
				<c:forEach items="${splist}" var="item">
					<li>
						<span>${item.name}</span>
						<a href="VetServlet?method=deleteVetSpecialty&vetid=<%=vet.getId()%>&specid=${item.id}">Del</a>
					</li>
				</c:forEach>
				</ul>
			</td>
		<% } %>
		</tr>
		<tr>
			<% if(flag==0){ %>
			<td>
				<a href="VetServlet?method=deleteVet&id=<%=vet.getId()%>">Delete Vet</a>
			</td>
			<td align="center">
				<label for="link1-trigger" class="modal-link">Add Specialty</label>
			</td>
			<% } %>
			<td >
				<a href="VetServlet?method=showVets&pageIndex=1">BACK</a>
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
	
	<div class="modal">
	<input type="checkbox" id="link1-trigger" class="check">
	<div id="link1" class="container">
		<div class="content">
			<h2>Update
           		<p class="modal-exit"><label for="link1-trigger" class="modal-link">
           			<img src="img/exit-1.png">
           		</label></p>
           	</h2>
            <div class="modal-body">
                <p class="popup-hint">Note: Please select the new specialty below.</p>
                 <select id="aim">
					<c:forEach items="${splist2}" var="item2">
						<option value ="${item2.id }">${item2.name }</option>
					</c:forEach>
					
					</select>
                <a href="" id="popup-btn" onclick="sendUpdate(this)" >Confirm</a>
            </div>
        </div>
    </div>
	</div>
	
	<%@ include file="footer.jsp" %>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="js/cropPic.js"></script>
    <% if(flag==0){ %>
    <script type="text/javascript">
	    function sendUpdate(obj){
	    	var aimobj = document.getElementById("aim");
	    	var path = "VetServlet?method=insertVetSpecialty&vetid="+<%=vet.getId()%>+"&specid="+aimobj.value;
	    	obj.href=path;
	    }
    </script>
    <% } %>
</body>
</html>