<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/popupwindow.css" />
	<title>SPECIALTY</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="page">
	<div class="content">
	<div id="specialty-search" class="content-search">
		<div class="content-search-pane">
		<form action="SpecialtyServlet?method=searchSpecialties" method="post">
			<input type="text" name="keyword" value=${param.keyword}>
			<input type="submit" value="SEARCH">
		</form>
		</div>
	</div>
	<div id="specialty-insert"  class="content-insert">
		<div class="content-insert-pane">
		<form action="SpecialtyServlet?method=insertSpecialty" method="post">
			<input type="text" name="name">
			<input type="submit" value="INSERT">
		</form>
		</div>
	</div>
	
	<div id=specialty-message class="content-message">
		<%
		if(request.getAttribute("message")!= null)
			out.print(request.getAttribute("message")+"<br>");
		%>
	</div>
	
	
	
	<div id="specialty-show" class="content-table">
	<table id="hor-minimalist-b">
    <thead>
    	<tr>
        	<th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Operation</th>
        </tr>
    </thead>
   	<tbody>
		<c:forEach items="${list}" var="item">
		<tr>
			<td>${item.id }</td>
			<td><a href="VetServlet?method=searchVetsBySpecid&keyword=${item.id }&name=${item.name}">${item.name }</a></td>
			<td>
				<label class="modal-link" for="link1-trigger" title="${item.id}" onclick="setInfo(this)">Edit</label>
				<span>&nbsp;|&nbsp;</span>
				<a href="SpecialtyServlet?method=deleteSpecialty&id=${item.id}">Delete</a>
			</td>	
		</tr>
		</c:forEach>
   	</tbody>
	</table>
	</div>
	
	</div>
	</div>
	
	<div class="modal">
	<input type="checkbox" id="link1-trigger" class="check">
	<div id="link1" class="container">
		<div class="content">
			<h2>UPDATE
           		<p class="modal-exit"><label for="link1-trigger" class="modal-link">
           			<img src="img/exit-1.png">
           		</label></p>
           	</h2>
            <div class="modal-body">
                <p class="popup-hint">Note: Please enter the new name below.</p>
                <input type="text" id="aim">
                <a href="" id="popup-btn" onclick="sendUpdate(this)" >Confirm</a>
            </div>
        </div>
    </div>
	</div>

	<%@ include file="footer.jsp" %>
	<script type="text/javascript">
	var id = 0;
	function setInfo(obj){
		id=obj.getAttribute("title");
	}
	function sendUpdate(obj){
		var aimobj = document.getElementById("aim");
		var path = type+"SpecialtyServlet?method=updateSpecialty&id="+id+"&name="+aimobj.value;
		obj.href=path;
	}
	</script>
</body>
</html></html>