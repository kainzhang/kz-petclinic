function checkSignUpForm(){
    var fn = document.getElementById("fn");
    var ln = document.getElementById("ln");
    var mail = document.getElementById("mail");
    var pass = document.getElementById("pass");
    var cpas = document.getElementById("cpas");

    if(fn.value === "") {
        alert("请输入 First Name!");
    } else if (ln.value === "") {
        alert("请输入 Last Name!");
    } else if (mail.value === "") {
        alert("邮箱不能为空!");
    } else if (!checkEmail(mail)){
        alert("邮箱地址不合法!");
    } else if (pass.value === "") {
        alert("请输入 Password!");
    } else if(cpas.value ==="") {
        alert("请再次输入密码!");
    } else if (pass.value != cpas.value) {
        alert("两次输入密码不一致!");
    } else {
        alert("验证通过!");
    }
}