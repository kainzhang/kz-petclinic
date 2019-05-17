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

if(document.attachEvent)
	window.attachEvent("onload", ymd);
else
	window.addEventListener('load', ymd, false);

days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

function ymd()
{
	for(var i=1990;i<=2000;i++)
		document.petform.YY.options.add(new Option(i,i));
	for(var i=1;i<10;i++)
		document.petform.MM.options.add(new Option('0'+i,i));
	for(var i=10;i<13;i++)
		document.petform.MM.options.add(new Option(i,i));
}

function ynd(year)
{
	var m = document.petform.MM.options[document.petform.MM.selectedIndex].value;
	if( m == ""){
		document.petform.DD.options.length = 1;
	} else {
		var d = days[m];
		if( m == 2 && isleap(year)) d++;
		getd(d);
	}
}

function mnd(month)
{
	var y = document.petform.YY.options[document.petform.YY.selectedIndex].value;
	if( y == ""){
		document.petform.DD.options.length = 1;
	} else {
		var d = days[month];
		if( month == 2 && isleap(y)) d++;
		getd(d);
	}
}

function isleap(n)
{return(n%4==0&&n%100!=0||n%400==0);}

function getd(d)
{
	var t = document.petform.DD;
	t.options.length = 1;
	for(var i=1;i<10;i++)
		t.options.add(new Option('0'+i,i));
	for(var i=10;i<=d;i++)
		t.options.add(new Option(i,i));
}

function splitdate(date){
	console.log(date);
}
