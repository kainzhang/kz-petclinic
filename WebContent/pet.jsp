<%@ page language="java" contentType="text/html; charset=UTF-8" import="model.Pet,java.util.List"
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
	
	<div class="content">
	
	<div class="content-search">
		<div class="content-search-pane">
			<form action="PetServlet?method=searchPet" method="post">
				<input type="text" name="keyword" value=${param.keyword} >
				<input type="submit" value="SEARCH">
			</form>
		</div class="content-search-pane">
	</div>
	
	<%--
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
					<td><label for="link1-trigger" class="modal-link" type="Specialty" title="${item.id}" onclick="setItem(this);setInfo(this)" href="">修改</label></td>
					<td><a href="PetServlet?method=deletePet&id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	 --%>
	
	
	<div class="content-picshow">
		<c:forEach items="${list}" var="item">
			<a href="PetServlet?method=showPet&id=${item.id}" class="content-picshow-item">
				<img src="img/monroe.jpg" alt="">
				<div class="content-picshow-item-info">
					<span>${item.name}&nbsp;|&nbsp;</span>
					<span>${item.species}&nbsp;
				</div>
			</a>	
		</c:forEach>
	</div>
	</div>
	
	
	<%--
	<div class="modal">
		<input type="checkbox" id="link1-trigger" class="check">
		<div id="link1" class="container">
			<div class="content">
        	    <div class="modal-body">
            		<h2>UPDATE</h2>
                	<p><!--SPACER-->&nbsp;<!--SPACER--></p>
                	
                	<p>name:<input type="text" name="upName" value="Nimeia"></p>
                	<p>Birthday:<input type="text" name="upBday" ></p>
                	<p>species:<select name="upSpecies">
						<c:forEach items="${splist}" var="item2">
							<option value ="${item2.id }">${item2.name }</option>
						</c:forEach>
						</select>
					</p>
                	<p>owner<select name="upOwner">
						<c:forEach items="${ownerlist}" var="item3">
							<option value ="${item3.id }">${item3.name }</option>
						</c:forEach>
						</select>
                	</p>
                	<p>pic:<input type="text" name="upPic" ></p>
                	
                	
                	<p><!--SPACER-->&nbsp;<!--SPACER--></p>
                	<p><!--SPACER-->&nbsp;<!--SPACER--></p>
	                <a href="" id="popup-btn" onclick="sendUpdate(this)" >确定</a>
	                <label for="link1-trigger" class="modal-link">CLOSE</label>
	            </div>
	        </div>
	    </div>
	</div>
	
	 --%>
	
	
	
	<%@ include file="footer.jsp" %>
	<script>
        function setItem(obj){
            var father=obj.parentNode.parentNode;
            var upName=document.getElementsByName('upName')[0];
            upName.value=father.cells[1].innerText;
            var upBday=document.getElementsByName('upBday')[0];
            upBday.value=father.cells[2].innerText;
            var upPic=document.getElementsByName('upPic')[0];
            upPic.value=father.cells[5].innerText;
            var upSpecies=document.getElementsByName('upSpecies')[0];
            for(var i=0;i<upSpecies.length;i++){
                if(father.cells[3].innerText==upSpecies.options[i].text){
                    upSpecies.options[i].selected=true;
                    break;
                }
            }
            var upOwner=document.getElementsByName('upOwner')[0];
            for(var i=0;i<upOwner.length;i++){
                if(father.cells[3].innerText==upOwner.options[i].text){
                    upOwner.options[i].selected=true;
                    break;
                }
            }
        }
    </script>
	
</body>
</html>