(function ($) {
    $.fn.topBar = function (data) {
        var title=data.title;

        $(this).prepend("<div class='cs-topbar-wrapper'><div class='cs-topbar-title'>"+title+"</div>" +
            "<div id='cs-topbar-username-id'></div>" +
            "<div id='topbar-logout-id'><span id='topbar-logout-text-id' class='cs-linkish-white top-bar-logout-label'>Logout</span></div></div>");

        $("#topbar-logout-id").append("" +
            "<form id='topbar-logout-form-id' action='/logout' method='post' name='logout'>\n" +
            "\t<input id='topbar-csrf-input-id' type='hidden' name='_csrf' value=''>\n" +
            "\t<button class='cs-topbar-logout' type='submit' name='logout' value='logout'>Logout</button>\n" +
            "</form>" +
            "");
        $("#topbar-logout-form-id").css("display","none");

        var token=$.cookie("XSRF-TOKEN");

        $("#topbar-csrf-input-id").attr("value",token);

        $("#topbar-logout-text-id").click(function () {
            $("#topbar-logout-form-id").submit();
        });

        $.get("api/user",function (data) {
            $("#cs-topbar-username-id").html(data);
        })



    }
})(jQuery);
