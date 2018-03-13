$(document).ready(function () {
    $.get("api/health",function (data) {
        $("#cs-main-content-id").html("<div>"+data+"</div>");
    });

    var token=$.cookie("XSRF-TOKEN");

    $("#cs-csrf-input-id").attr("value",token);

})