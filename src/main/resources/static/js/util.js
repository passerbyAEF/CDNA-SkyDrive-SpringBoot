function post(url, data,suc) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType: "JSON",
        contentType : "application/json",
        timeout: 4000,
        success:suc
    });
}

function get(url,header,suc) {
    $.ajax({
        type: "GET",
        url: url,
        headers:header,
        timeout: 4000,
        success:suc
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