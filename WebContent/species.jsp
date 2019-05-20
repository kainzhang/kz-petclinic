<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SPECIES</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div class="page">
	<div class="content">
	<div id="species-search">
		<form action="SpeciesServlet?method=searchSpecies" method="post">
			<label>Keyword：</label>
			<input type="text" name="keyword" value=${param.keyword}>
			<input type="submit" value="查询">
		</form>
	</div>
	<div id=species-message>
		<%
		if(request.getAttribute("message")!= null)
			out.print(request.getAttribute("message")+"<br>");
		%>
	</div>
	
	<div id="species-insert">
		<form action="SpeciesServlet?method=insertSpecies" method="post">
			<label>New Species;</label>
			<input type="text" name="name">
			<input type="submit" value="添加">
		</form>
	</div>
	<div id="species-show">
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
					<td><a href="PetServlet?method=searchPets&pageIndex=1&keyword=${item.name }">${item.name }</a></td>
					<td>
					<label for="link1-trigger" class="modal-link" type="Species" title="${item.id}" onclick="setInfo(this)">Edit</label>
					<span>&nbsp;|&nbsp;</span>
					<a href="SpeciesServlet?method=deleteSpecies&id=${item.id}">Delete</a>
					</td>
					
				</tr>
			</c:forEach>
   	</tbody>
	</table>
	</div>
	</div>
	</div>
	
	<%--<%@ include file="popup.jsp" %> --%>
	
	<%@ include file="footer.jsp" %>
</body>
</html></html>