<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.Pet" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Update Pet</title>
	<script type="text/javascript" src="js/date.js" ></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<% 
		int flag = Integer.parseInt(request.getParameter("flag"));
		Pet pet = null;
		String[] dateArray={};
		if(flag==0) {            
			pet = (Pet) request.getAttribute("aimPet");
			String bday = pet.getBday();
			dateArray = bday.split("-");
		}      
	%> 
	<%
     	String path = request.getContextPath();
     	String picPath="/media/";
     	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + picPath;
     	basePath += pet.getPic(); 
     %>
	<script src="js/jquery.Jcrop.js"></script>
    <script type="text/javascript">
      var arr = new Array();
      var image2 = new Image();
      var jcrop_api,boundx,boundy,$pcnt,$pimg,xsize,ysize,shade,targetP,picFile;
      function selectPic(file) {
        if (!file.files || !file.files[0]) {
          return;
        }
        shade=document.getElementById("shade");
        targetP=document.getElementById("target");
        shade.style.display="none";
        var reader = new FileReader();
        reader.onload = function(evt) {//加载图片事件
          targetP.src = evt.target.result;
          jcrop_api.setImage(evt.target.result);
          image2.src = evt.target.result;
        };
        reader.readAsDataURL(file.files[0]);
        InitJquery();
      }
      function InitJquery(){//初始化jcrp
          $preview = $("#preview-pane");
          $pcnt = $("#preview-pane .preview-container");
          $pimg = $("#preview-pane .preview-container img");
          xsize = $pcnt.width();
          ysize = $pcnt.height();
        console.log("init", [xsize, ysize]);
        $("#target").Jcrop(
          {
            onChange: updatePreview,
            onSelect: updatePreview,
            boxWidth:500,
            boxHeight:500,
            aspectRatio:1,
            aspectRatio: xsize / ysize
          },
          function() {
            jcrop_api = this;
            $preview.appendTo(jcrop_api.ui.holder);
          }
        );
      }
      function updatePreview(c) {//修改略缩图大小
        if (parseInt(c.w) > 0) {
          var rx = xsize / c.w;
          var ry = ysize / c.h;
          boundx = image2.width;
          boundy = image2.height;
          $pimg.css({
            width: Math.round(rx * boundx) + "px",
            height: Math.round(ry * boundy) + "px",
            marginLeft: "-" + Math.round(rx * c.x) + "px",
            marginTop: "-" + Math.round(ry * c.y) + "px"
          });
        }
        arr[0] = c.x;
        arr[1] = c.y;
        arr[2] = c.w;
        arr[3] = c.h;
      }
      function convertBase64UrlToBlob(urlData,type){
        var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte
        //处理异常,将ascii码小于0的转换为大于0
        var ab = new ArrayBuffer(bytes.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < bytes.length; i++) {
            ia[i] = bytes.charCodeAt(i);
        }
        return new Blob( [ab] , {type : 'image/'+type});
    }


      function cutPic(){
        var canvas = document.createElement("canvas");
        canvas.width=arr[2];
        canvas.height=arr[3];
        var context = canvas.getContext("2d");
        context.drawImage(image2,-arr[0],-arr[1]);
        var url=canvas.toDataURL("image/jpeg");
        shade.style.display="block";
        jcrop_api.destroy();
        targetP.src="";
        $("#shade").css({
          "background-image": "url("+url+")",
          "background-size": "cover"
        });
        picFile=convertBase64UrlToBlob(url,"jpeg");
      }
      function updataPic(){
          var formData=new FormData();
          var picName="petPic"+parseInt(new Date().getTime()/1000)+".jpeg";
          document.getElementById("pic").value=picName;
          formData.append(picName,picFile,picName);
          $.ajax({
            url:  'PetServlet?method=updatePic',
            type: 'post',
            async: false,
            data: formData,
            processData: false,// 告诉jQuery不要去处理发送的数据
            contentType: false,// 告诉jQuery不要去设置Content-Type请求头
            beforeSend: function () {//过程...
                console.log('正在进行，请稍候')
            },
            success: function () {
                  console.log('导入成功');
            },
            error:function(){
                console.log('导入错误');
            }
          })
        }
    </script>
    <link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" />
    <style type="text/css">
      /* Apply these styles only when #preview-pane has
       been placed within the Jcrop widget */
      .jcrop-holder #preview-pane {
        
        
        display: none;
        position: absolute;
        z-index: 2000;
        top: 10px;
        right: -280px;
        padding: 6px;
        border: 1px rgba(0, 0, 0, 0.4) solid;
        background-color: white;

        -webkit-border-radius: 6px;
        -moz-border-radius: 6px;
        border-radius: 6px;

        -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
        -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
        box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
      }

      /* The Javascript code will set the aspect ratio of the crop
       area based on the size of the thumbnail preview,
       specified here */
      #preview-pane .preview-container {
        width: 250px;
        height: 250px;
        overflow: hidden;
        display: none;
      }
      #shade{
        width:500px;
        height: 500px;
        position: relative;
        color: #000;
        z-index: 9999;
        background-size: cover;
        background-image:url(<%=basePath%>);
      }
    </style>
    
	<div id="detail-page">

		
	<div id="detail-page-pane-1">
			<%//选择图片 %>
	    <div class="container">
	      <div class="row">
	        <div class="span12">
	          <input type="file" onchange="selectPic(this)" id="selectBtn" />
	          <label onclick="cutPic()">剪切</label>
	          <label onclick="updataPic()">上传</label>
	          <div class="jc-demo-box">
	            <div id="shade"> </div>
	            <img src="" id="target" alt="" />
	            <div id="preview-pane">
	              <div class="preview-container">
	                <img src="" class="jcrop-preview" alt="" id="pic2" />
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	</div>
	<form 
		<% if(flag==0){ %>
		action="PetServlet?method=updatePet"
		<% } else { %>
		action="PetServlet?method=insertPet"
		<% } %>  method="POST" name="thisform" id="petForm">
	<div id="detail-page-pane-2">   
		
		<table>
		<tr>
			<td><span></span></td>
			<td colspan="4">
				<input type="text" name="id" <% if(flag==0){ %> value="<%=pet.getId()%>" <% } %> readonly="readonly">
			</td>
		<tr>
			<td><span>Name</span></td>
			<td colspan="4">
				<input type="text" name="name" <% if(flag==0){ %> value="<%=pet.getName()%>" <% } %>>
			</td>
		</tr>
		<tr>
			<td><span>Species</span></td>
			<td colspan="4">
				<select name="speciesid">
				<% if(flag==0){ %>
				<option value='<%=pet.getSpeciesId()%>' selected style='display:none;'><%=pet.getSpecies()%></option>
				<% } %>
				<c:forEach items="${splist}" var="item2">
					<option value ="${item2.id }">${item2.name }</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td><span>B-Day</span></td>
			<td colspan="2">
			<select class="select-m" name="YY" onchange="ynd(this.value)">
             	<% if(flag==0) {%>
             		<option value="<%=dateArray[0]%>" selected><%=dateArray[0]%></option>
             	<%} else { %>
               	<option value="" disabled selected>YYYY</option>
               	<% } %>
            </select>
            <%-- <input type="text" name="bday" <% if(flag==0){ %>value="<%=pet.getBday()%>"<% } %>> --%>
			</td>
            <td>
            <select class="select-s" name="MM" onchange="mnd(this.value)">
                <% if(flag==0) {%>
              		<option value="<%=dateArray[1]%>" selected><%=dateArray[1]%></option>
              	<%} else { %>
                	<option value="" disabled selected>MM</option>
                <% } %>
            </select>
            </td>
            <td>
            <select class="select-s" name="DD" >
                 <% if(flag==0) {%>
              		<option value="<%=dateArray[2]%>" selected><%=dateArray[2]%></option>
              	<%} else { %>
                	<option value="" disabled selected>DD</option>
                <% } %>
            </select>
            </td>
		</tr>
		<tr>
			<td><span>Owner</span></td>
			<td colspan="4">
				<select name="ownerid">
				<% if(flag==0){ %>
				<option value='<%=pet.getOwnerId()%>' selected style='display:none;'><%=pet.getOwner()%></option>
				<% } %>
				<c:forEach items="${ownerlist}" var="item3">
					<option value ="${item3.id }">${item3.name }</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<% if(flag==0){ %>
				<a href="PetServlet?method=deletePet&id=<%=pet.getId()%>">DELETE</a>
			<% } %>
			</td>
			<td >
				<a href="PetServlet?method=showPets">BACK</a>
			</td>
			<td colspan="2">
                <input type="text" name="pic" style="display:none;" value="<%=basePath%>" id="pic">
				<input type="submit" value="SUBMIT">
			</td>
		</tr>
		</table>
	</div>
	</form>
	</div>
	<%@ include file="footer.jsp" %>

</body>
</html>