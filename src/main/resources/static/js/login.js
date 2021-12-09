function Login() {
    var LoginInfo = {Name: "", Pwds: ""};
    LoginInfo.Name = document.getElementById("input-user").value.replace(/(^\s*)|(\s*$)/g, '');
    LoginInfo.Pwds = encryptByDES(document.getElementById("input-passwd").value.replace(/(^\s*)|(\s*$)/g, ''));
    if ((LoginInfo.Name || LoginInfo.Pwds) === "" || (LoginInfo.Name || LoginInfo.Pwds) === undefined || (LoginInfo.Name || LoginInfo.Pwds) == null) {
        EchoLoginError("用户名或密码不能为空");
    } else {
        post("/api/Login", LoginInfo);
        // xhttp = new XMLHttpRequest();
        // xhttp.open("POST", "/api/Login");
        // xhttp.send(JSON.stringify(LoginInfo));
        // timeout = 0;
    }
}
//DES加密
function encryptByDES(message, key){
    var keyHex = CryptoJS.enc.Utf8.parse(key);
    var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.ciphertext.toString();
}

function CheckLoginToken() {
    // if (document.cookie.indexOf("Token") !== -1) {
    //     var xmlhttp = new XMLHttpRequest();
    //     xmlhttp.open("GET", "/api/Token");
    //     xmlhttp.onreadystatechange = function () {
    //         if (xmlhttp.status == 200 && xmlhttp.readyState == 4) {
    //             window.location.href = "./html/home.html";
    //             return;
    //         } else if ((xmlhttp.status != 200) && (xmlhttp.readyState == 4)) {
    //             document.cookie = "Token=;" + "expires=Thu, 01 Jan 1970 00: 00: 00 GMT";
    //             return;
    //         }
    //     }
    // } else {
    //     return;
    // }
    // xmlhttp.send();
}

function EchoLoginError(str) {
    var LoginInfo = document.getElementById("login-error-info");
    LoginInfo.innerHTML = str;
    LoginInfo.style.display = "block";
}

function post(url, data) {
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        dataType: "json",
        timeout: 4000,
        success: function (message) {
            var json = JSON.parse(message);
            if (json.Message === "OK") {
                // var day = new Date();
                // day.setHours(day.getHours() + 24);
                // var cook = "Token=" + json.Data + ";" + "expires=" + day.toUTCString();
                // var Username = "UserName=" + document.getElementById("input-user").value + ";";
                // document.cookie = cook;
                // document.cookie = Username;
                // window.location.href = "./html/home.html";
            } else {
                EchoLoginError("用户名或密码错误");
            }
        },
        error: function (message) {
            EchoLoginError("提交数据失败！");
        },
        complete: function (XMLHttpRequest, status) { //请求完成后最终执行参数
            if (status === 'timeout') {//超时,status还有success,error等值的情况
                EchoLoginError("连接超时");
            }
        }
    });
}