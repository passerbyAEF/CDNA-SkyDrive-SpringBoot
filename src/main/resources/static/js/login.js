function Login() {
    var LoginInfo = {Name: "", Pwds: ""};
    LoginInfo.Name = document.getElementById("input-user").value.replace(/(^\s*)|(\s*$)/g, '');
    LoginInfo.Pwds = hex_md5(document.getElementById("input-passwd").value.replace(/(^\s*)|(\s*$)/g, ''));
    if ((LoginInfo.Name || LoginInfo.Pwds) === "" || (LoginInfo.Name || LoginInfo.Pwds) === undefined || (LoginInfo.Name || LoginInfo.Pwds) == null) {
        EchoLoginError("用户名或密码不能为空");
    } else {
        var form = $("<form id='form' style='opacity: 1' action='/api/login' method='post'>" +
            "<input type='text' style='opacity: 1' name='Name' value='" + LoginInfo.Name + "'>" +
            "<input type='password' style='opacity: 1' name='Pwds' value='" + LoginInfo.Pwds + "'>" +
            "</form>")
        $(document.body).append(form);
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