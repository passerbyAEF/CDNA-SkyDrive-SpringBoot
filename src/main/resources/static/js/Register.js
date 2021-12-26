function Register() {
    var name = document.getElementById("reg-input-user");
    var pwds = document.getElementById("reg-input-passwd");
    var phoneNumber = document.getElementById("reg-input-phone-number");
    if ((name.value || pwds.value || phoneNumber.value) === "" || (name.value || pwds.value || phoneNumber.value) === undefined || (name.value || pwds.value || phoneNumber.value) == null) {
        EchoRegistryError("信息不能为空");
        return;
    }
    if (pwds.value !== document.getElementById("reg-input-confirm-passwd").value) {
        EchoRegistryError("重复密码不匹配");
        return;
    }
    if (phoneNumber.value.length !== 11) {
        EchoRegistryError("手机号码长度错误");
        return;
    }
    var form = $("#form")
    pwds.value=hex_md5(pwds.value);
    form.submit();
}

function EchoRegistryError(error) {
    var html = document.getElementById("register-error");
    html.innerHTML = error;
    html.style.display = "block";
}