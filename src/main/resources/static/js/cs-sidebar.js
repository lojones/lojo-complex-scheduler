(function ($) {
    $.fn.csSideBar = function (data) {
        var html="" +
            "        <div class='sidebar__inner'>\n" +
            "            <div class='cs-sidebar-wrapper cs-boxshadow'>\n" +
            "                <div class='cs-linkish-black' id='cs-create-schedule-id'>Create Schedule</div>\n" +
            "                <div></div>\n" +
            "            </div>\n" +
            "        </div>\n";// +

        $(this).html(html);

        var sidebarId=$(this).attr("id");
        $(this).addClass('sidebar');
        sidebarId="#"+sidebarId;

        console.log("using div ",sidebarId);

        $("#cs-create-schedule-id").click(function () {
            var currentUrl = window.location.protocol+"//"+window.location.host+window.location.pathname;
            var newUrl = window.location.protocol+"//"+window.location.host+"/schedule.html";
            console.log("currently here",currentUrl,"going to ",newUrl);

            window.location = newUrl;
        });

        var sidebar = new StickySidebar(sidebarId, {
            containerSelector: '#cs-main-content-id',
            innerWrapperSelector: '.sidebar__inner',
            topSpacing: 50,
            bottomSpacing: 20
        });

    }

})(jQuery);
