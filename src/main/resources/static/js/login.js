$(document).ready(function () {

    var token=$.cookie("XSRF-TOKEN");

    $("#cs-csrf-input-id").attr("value",token);

});