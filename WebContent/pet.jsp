<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PET</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	<div id=pet-message>
		<font color="green" size="18">
		<%
		if(request.getAttribute("message")!= null)
			out.print(request.getAttribute("message")+"<br>");
		%>
		</font>
	</div>
	<div id="pet-search">
		<form action="PetServlet?method=searchPet" method="post">
			<label>关键字：</label>
			<input type="text" name="keyword" value=${param.keyword} >
			<input type="submit" value="查询">
		</form>
	</div>
	<div id="pet-insert">
		<form action="PetServlet?method=insertPet" method="post">
			<label>新宠物：</label>
			名字<input type="text" name="name">
			生日<input type="text" name="bday">
			照片<input type="text" name="pic">
			类型<select name="speciesid">
				<c:forEach items="${splist}" var="item2">
					<option value ="${item2.id }">${item2.name }</option>
				</c:forEach>
			</select>
			主人<select name="ownerid">
				<c:forEach items="${ownerlist}" var="item3">
					<option value ="${item3.id }">${item3.name }</option>
				</c:forEach>
			</select>
			<input type="submit" value="添加">
		</form>
	</div>
	<div id="pet-show">
		<table border="1">
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.id }</td>
					<td>${item.name }</td>
					<td>${item.bday }</td>
					<td>${item.species }</td>
					<td>${item.owner }</td>
					<td>${item.pic }</td>
					<td><a href="PetServlet?method=toUpdatePet&id=${item.id}">修改</a></td>
					<td><a href="PetServlet?method=deletePet&id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>