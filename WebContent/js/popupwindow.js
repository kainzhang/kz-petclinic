var id=0;
function setId(obj){
	id=obj.getAttribute('title');
}
function sendUpdate(obj){
	var type = obj.getAttribute('class');
	var aimobj = document.getElementById("aim");
	var path = type+"Servlet?method=update"+type+"&id="+id+"&name="+aimobj.value;
	obj.href=path;
}