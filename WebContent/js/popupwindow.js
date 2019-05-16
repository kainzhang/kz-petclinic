var id = 0;
var type = "";
function setInfo(obj){
	id=obj.getAttribute("title");
	type = obj.getAttribute("type");
}
function sendUpdate(obj){
	var aimobj = document.getElementById("aim");
	var path = type+"Servlet?method=update"+type+"&id="+id+"&name="+aimobj.value;
	obj.href=path;
}