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
        var a=new Array();
        a[0]=a[1]=25;
        a[2]=a[3]=325;
        jcrop_api.setSelect(a);
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
            boxWidth:350,
            boxHeight:350,
            aspectRatio:1,
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
                console.log('正在进行，请稍候');
            },
            success: function () {
            	document.getElementById("hint").innerText="上传成功";
            },
            error:function(){
            	document.getElementById("hint").innerText="上传失败";
            }
          })
        }
      