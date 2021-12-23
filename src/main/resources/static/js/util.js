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


function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}