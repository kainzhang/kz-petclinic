<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>RECORD</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="page">
	<div class="content">
	<div id="record-search" class="content-search">
		<div class="content-search-pane">
			<form action="RecordServlet?method=searchRecord" method="post">
				<input type="text" name="keyword" value=${param.keyword}>
				<input type="submit" value="SEARCH">
			</form>
		</div>
	</div>
	
	<div id="record-insert" class="content-insert">
		<div class="content-insert-pane">
		<form action="RecordServlet?method=insertRecord" method="post">
			<label>Pet:</label>
			<select class="select-s" name="petid">
				<c:forEach items="${pl}" var="item">
					<option value ="${item.id }">${item.name }</option>
				</c:forEach>
			</select>
			<label>Vet:</label>
			<select class="select-s" name="vetid" >
				<c:forEach items="${vl}" var="item2">
					<option value ="${item2.id }">${item2.name }</option>
				</c:forEach>
			</select>
			<label>Descr:</label>
			<input type="text" name="descr">
			<input type="submit" value="INSERT">
		</form>
		</div>
	</div>
	
	<span id="record-message" class="content-message">
		<%
		if(request.getAttribute("message")!= null)
			out.print(request.getAttribute("message")+"<br>");
		%>
	</span>
	
	<div id="record-show" class="content-table">
		<table id="hor-minimalist-b">
		<thead>
			<tr>
	        	<th scope="col">ID</th>
	            <th scope="col">Pet</th>
	            <th scope="col">Vet</th>
	            <th scope="col">Date</th>
	            <th scope="col">Description</th>
	            <th scope="col">Operation</th>
	        </tr>
        </thead>
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.id }</td>
					<td><a href="PetServlet?method=showPet&id=${item.petid}">${item.petname}</a></td>
					<td><a href="VetServlet?method=showVet&id=${item.vetid}">${item.vetname}</a></td>
					<td>${item.date }</td>
					<td>${item.descr }</td>
					<td><a href="RecordServlet?method=deleteRecord&id=${item.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html></html>