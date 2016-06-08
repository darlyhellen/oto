info = new Object();
info.name = "name";
info.icon = "icon";
info.tel = "tel";
info.sex = "sex";
info.idCard = "idCard";
info.money = "money";
info.token = "token";
info.same = "same";
info.pass = "pass";
info.sim = "sim";
function init() {
	document.getElementById("name").innerHTML = info.name;
	document.getElementById("icon").innerHTML = info.icon;
	document.getElementById("tel").innerHTML = info.tel;
	document.getElementById("sex").innerHTML = info.sex;
	document.getElementById("idCard").innerHTML = info.idCard;
	document.getElementById("money").innerHTML = info.money;
	document.getElementById("token").innerHTML = info.token;
	document.getElementById("same").innerHTML = info.same;
	document.getElementById("pass").innerHTML = info.pass;
	document.getElementById("sim").innerHTML = info.sim;
}
function getinfo(num) {
	info.name=	document.getElementById("name"+num);
	info.icon = document.getElementById("icon"+num);
	info.tel =document.getElementById("tel"+num);
	info.sex= document.getElementById("sex"+num);
	info.idCard =document.getElementById("idCard"+num);
	info.money= document.getElementById("money"+num);
	info.token= document.getElementById("token"+num);
	info.same =document.getElementById("same"+num);
	info.pass= document.getElementById("pass"+num);
	info.sim =document.getElementById("sim"+num);
	return info;
}