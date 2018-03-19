$(document).ready(function () {
    $.get("api/health",function (data) {
        console.log(data);
    });

    var token=$.cookie("XSRF-TOKEN");

    $("#cs-csrf-input-id").attr("value",token);

    $("body").topBar({
        title: "Scheduler"
    });

    $("#cs-sidebar-id").csSideBar();

    $("#cs-main-content-id").csSchedule();



});