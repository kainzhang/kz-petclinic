<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>VET</title>
	<style type="text/css">
		div {
			margin: 8px 8px;
		}
		#vet-message {
			height: 60px;
		}
		input {
			margin: 3px 3px;
		}
		td {
			width: 100px;
		}
	</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id=vet-message>
		<font color="green" size="18">
		<%
		if(request.getAttribute("message")!= null)
			out.print(request.getAttribute("message")+"<br>");
		%>
		</font>
	</div>
	<div id="vet-search">
		<form action="VetServlet?method=searchVets" method="post">
			<label>关键字：</label>
			<input type="text" name="keyword" value=${param.keyword}>
			<input type="submit" value="查询">
		</form>
	</div>
	<div id="vet-insert">
		<form action="VetServlet?method=insertVet" method="post">
			<label>新兽医：</label>
			<input type="text" name="name">
			<input type="submit" value="添加">
		</form>
	</div>
	<div id="vet-show">
		<table border="1">
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.id }</td>
					<td>${item.name }</td>
					<td><a href="VetServlet?method=updateVet&id=${item.id}">修改</a></td>
					<td><a href="VetServlet?method=deleteVet&id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>