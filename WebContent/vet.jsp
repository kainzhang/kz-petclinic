 <%@ page language="java" contentType="text/html; charset=UTF-8" import="model.Pet,java.util.List"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String picPath="/media/";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + picPath;
	
	String method = (String) request.getAttribute("method");
	Integer maxPageIndex = (Integer) request.getAttribute("maxPageIndex");
	Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
	Integer prevPageIndex = pageIndex - 1;
	Integer nextPageIndex = pageIndex + 1;
	String prevPage = "VetServlet?method="+method+"&pageIndex="+prevPageIndex;
	String nextPage = "VetServlet?method="+method+"&pageIndex="+nextPageIndex; 
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PET</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	
	<div class="page">
	<div class="content">
	<div class="content-search">
		<div class="content-search-pane">
			<form action="VetServlet?method=searchVets&pageIndex=1" method="post">
				<a href="VetServlet?method=toInsertVet">ADD NEW</a>
				<input type="text" name="keyword" value=${param.keyword} >
				<input type="submit" value="SEARCH">
			</form>
		</div>
	</div>
	<span  class="content-message">
	<%
	if(request.getAttribute("message")!= null)
		out.print(request.getAttribute("message")+"<br>");
	%>
	</span>
	
	<div class="content-picshow">
		<c:forEach items="${list}" var="item">
			<a href="VetServlet?method=showVet&id=${item.id}" class="content-picshow-item">
				<img src="<%=basePath %>${item.pic}" alt="">
				<div class="content-picshow-item-info">
					<span>${item.name}</span>
				</div>
			</a>	
		</c:forEach>
	</div>
	
	<div class="content-pager">
		<div class="content-pager-pane">
		<% if( prevPageIndex > 0){ %>
			<a href="<%=prevPage %>" class="content-pager-btn">Prev</a>
		<% } else { %>
			<span style="border:0;" class="content-pager-btn">&nbsp;</span>
		<% } %>
		<% if( nextPageIndex <= maxPageIndex){ %>
			<a href="<%=nextPage %>" class="content-pager-btn">Next</a>
		<% } else { %>
			<span style="border:0;" class="content-pager-btn">&nbsp;</span>
		<% } %>
		</div>
	</div>
	
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>