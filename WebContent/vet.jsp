<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>VET</title>
	<script type="text/javascript">
	function ale(obj){
		var id = obj.getAttribute("title");
		var type = obj.getAttribute("class");
		//var name = prompt("请输入新名称");
		//if(name!=null && name!=""){
		var path = type+"Servlet?method=delete"+type+"&id="+id;
		//document.form.aim.value = path;
		document.getElementById("aim").value = path;
		document.getElementById("vetForm").submit();
		//alert(path);
	}
	</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="content">

	<div id="vet-message" >
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
					<td><label for="link1-trigger" class="modal-link" title="${item.id}" onclick="setId(this)">修改</label></td>
					<td><a href="VetServlet?method=deleteVet&id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	</div>
	
	<%@ include file="popup.jsp" %>
	<%@ include file="footer.jsp" %>
	<script type="text/javascript">
	
	</script>
</body>
</html>