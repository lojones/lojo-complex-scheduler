$(document).ready(function () {
    // console.log("requesting to api/health to get the csrf token");

    // var url=window.location.href;
    //
    // console.log(url);
    //
    // $.get("api/health",function (data,status,request) {
    //     console.log("response from api/health",data,status,request);
    //
    //     var token=request.getResponseHeader('X-CSRF-TOKEN');
    //
    //     console.log("token is ",token);
    //
    //
    // });

    var token=$.cookie("XSRF-TOKEN");

    $("#cs-csrf-input-id").attr("value",token);



    // $("#cs-login-btn").click(function () {
    //     $.ajax({
    //         type: "POST",
    //         headers: { "X-XSRF-TOKEN": token, "Authorization": "Basic "+btoa("user:pass") },
    //         url: "/login.html",
    //         success: function(msg) {
    //             console.log("success! logged in!");
    //             window.location.href = "http://localhost:8080/index.html"
    //         }
    //     });
    // });
    //
    // var url=window.location.href;
    // if (url.endsWith("error=true")) {
    //     alert("error!");
    // }


});