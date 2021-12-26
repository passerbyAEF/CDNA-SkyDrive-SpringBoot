function Login() {
    var username = document.getElementById("input-user");
    var passwd = document.getElementById("input-passwd");
    username.value=username.value.replace(/(^\s*)|(\s*$)/g, '');
    passwd.value=passwd.value.replace(/(^\s*)|(\s*$)/g, '');
    if ((username.value || passwd.value) === "" || (username.value || passwd.value) === undefined || (username.value || passwd.value) == null) {
        EchoLoginError("用户名或密码不能为空");
    } else {
        var form = $("#form")
        passwd.value=hex_md5(passwd.value);
        form.submit();
    }
}

function start() {
    var msg = getQueryString("msg");
    if (msg != null) {
        EchoLoginError(msg);
    }
}

function EchoLoginError(str) {
    var LoginInfo = document.getElementById("login-error-info");
    LoginInfo.innerHTML = str;
    LoginInfo.style.display = "block";
}