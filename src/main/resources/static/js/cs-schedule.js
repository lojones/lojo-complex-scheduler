(function ($) {

    $.fn.shiftSeries = function (data) {
        var name=data.name;

        // if ($("#cs-shiftseries-name"))
    };

    $.fn.csSchedule = function (data) {
        var token=$.cookie("XSRF-TOKEN");
        var parent=this;
        $(parent).html("<div id='cs-depts-dropdown-id'></div>");

        var csrfHeaders = {
            'X-CSRF-HEADER' : 'X-XSRF-TOKEN',
            'X-CSRF-PARAM' : '_csrf',
            'X-XSRF-TOKEN' : token
        };

        function buildDeptsDropdown(data) {

            console.log("Getting existing departments",data);

            var i=0;

            if (data != null) {

                var keys = Object.keys(data);

                var html = "" +
                    "<div>" +
                    "<label for='cs-dept-list' class='cs-debug' style='display: block;'>Select Company/Department</label>" +
                    "<select name='cs-dept-list' id='cs-dept-list'>" +
                    "<option value='0'>New Company/Department</option>";

                keys.forEach(function (key) {

                    html += "<option value='" + key + "'>" + data[key].companyName + " - " + data[key].departmentName + "</option>";
                    i++;

                });

                html += "</select></div>";

                $("#cs-depts-dropdown-id").html(html);

                $("#cs-dept-list").selectmenu();

            }
        }

        function refreshDeptsDropdown() {
            $("#cs-dept-list").selectmenu("refresh");
        }

        function newDept() {
            var html="<div id='cs-schedule-toplevelinfo-id'>" +
                "   <div class='cs-h1'>Company/Department Details</div>" +
                "   <div>Company Name</div>" +
                "   <div><input id='cs-schedule-company-id' type='text'></div>" +
                "   <div>Department</div>" +
                "   <div><input id='cs-schedule-department-id' type='text'></div>" +
                "   <div>Department Description</div>" +
                "   <div><input id='cs-schedule-departmentdesc-id' type='text'></div>" +
                "   <div id='cs-add-dept-submit-button-id' class='cs-submit-button cs-linkish-blue'>Add</div>" +
                "</div>";

            $("#cs-department-details").html(html);

            $("#cs-add-dept-submit-button-id").click(function () {

                var addDeptJson={
                    'companyName': $("#cs-schedule-company-id").val(),
                    'departmentName': $("#cs-schedule-department-id").val(),
                    'departmentDesc': $("#cs-schedule-departmentdesc-id").val()
                };

                var addDeptJsonString = JSON.stringify(addDeptJson);

                $.ajax({
                    url: 'api/department',
                    type: 'put',
                    data: addDeptJsonString,
                    headers: csrfHeaders,
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (data) {
                        console.info(data);
                        $("#cs-add-dept-submit-button-id").hide();
                        $.get('/api/departments',function (data) {
                            buildDeptsDropdown(data);
                            refreshDeptsDropdown();
                            $.notify("Added!",{globalPosition:'top center',className:'success'});
                        });
                    }
                });
            });
        }

        function loadDept(dept) {
            var html="<div id='cs-schedule-toplevelinfo-id'>" +
                "   <div class='cs-h1'>Company/Department Details</div>" +
                "   <div>Company Name</div>" +
                "   <div><input id='cs-schedule-company-id' type='text' value='"+dept.companyName+"'></div>" +
                "   <div>Department</div>" +
                "   <div><input id='cs-schedule-department-id' type='text' value='"+dept.departmentName+"'></div>" +
                "   <div>Department Description</div>" +
                "   <div><input id='cs-schedule-departmentdesc-id' type='text' value='"+dept.departmentDesc+"'></div>" +
                "</div>";

            $("#cs-department-details").html(html);
        }

        $.get('/api/departments',function (data) {

            buildDeptsDropdown(data);

            $(parent).append("<div id='cs-department-details'></div>");

            $("#cs-dept-list").selectmenu({
                select: function( event, ui) {
                    var selected=ui.item;
                    if (selected.value == '0') {

                        newDept();

                    }
                    else {

                        $.get('/api/department/'+selected.value,loadDept);
                    }
                }
            });
            // }

        });

        // $(this).html("" +
        //     "<div id='cs-schedule-toplevelinfo-id'>" +
        //     "   <div class='cs-h1'>Company/Department Details</div>" +
        //     "   <div>Company Name</div>" +
        //     "   <div><input id='cs-schedule-company-id' type='text'></div>" +
        //     "   <div>Department</div>" +
        //     "   <div><input id='cs-schedule-department-id' type='text'></div>" +
        //     "   <div>Department Description</div>" +
        //     "   <div><input id='cs-schedule-departmentdesc-id' type='text'></div>" +
        //     "   <div id='cs-submit-button-id' class='cs-submit-button cs-linkish-blue'>Add</div>" +
        //     "</div>");
        //
        // $(this).append("" +
        //     "<div id='cs-schedule-shiftseriesinput-id'>" +
        //     "   <div class='cs-h1'>Schedule Details</div>" +
        //     "   <div>" +
        //     "       <div id='cs-schedule-shiftseriesinput-name-id' >" +
        //     "           Name " +
        //     "           <input id='cs-schedule-shiftseriesinput-nametext-id' type='text'>" +
        //     "       </div>" +
        //     "   </div>" +
        //     "   <div>" +
        //     "       <div id='cs-schedule-shiftseriesinput-start-id' >" +
        //     "           Schedule Starts On " +
        //     "           <input id='cs-schedule-shiftseriesinput-startdate-id' type='text'>" +
        //     "           <div id='cs-schedule-shiftseriesinput-startdatepicker-id'></div>" +
        //     "       </div>" +
        //     "   </div>" +
        //     "   <div>" +
        //     "       <div id='cs-schedule-shiftseriesinput-end-id' >" +
        //     "           Schedule Ends On " +
        //     "           <input id='cs-schedule-shiftseriesinput-enddate-id' type='text'>" +
        //     "           <div id='cs-schedule-shiftseriesinput-enddatepicker-id'></div>" +
        //     "       </div>" +
        //     "   </div>" +
        //     "   <div>" +
        //     "       <label for='cs-schedule-shiftseriesinput-recur-id'>This schedule has recurring shifts</label>" +
        //     "       <input type='checkbox' id='cs-schedule-shiftseriesinput-recur-id' name='cs-schedule-shiftseriesinput-recur-id'>" +
        //     "   </div>" +
        //     "   <div id='cs-schedule-shiftseriesinput-shiftlengthwrapper-id'>" +
        //     "       <div>Length (Hours) of Each Shift</div>" +
        //     "       <div><input id='cs-schedule-shiftseriesinput-shiftlength-id' type='text'></div>" +
        //     "   </div>" +
        //     "</div>" +
        //     "");
        //
        // $("div",this).each(function (t) {
        //     $(this).addClass("cs-debug");
        // });
        // $("input",this).each(function (t) {
        //     $(this).addClass("cs-debug");
        // })
        //
        // $("#cs-schedule-shiftseriesinput-recur-id").checkboxradio();
        // $("#cs-schedule-shiftseriesinput-shiftlengthwrapper-id").hide();
        //
        // $("#cs-schedule-shiftseriesinput-recur-id").click(function () {
        //     if ($(this).is(":checked")) {
        //         $("#cs-schedule-shiftseriesinput-shiftlengthwrapper-id").show();
        //     } else {
        //         $("#cs-schedule-shiftseriesinput-shiftlengthwrapper-id").hide();
        //     }
        // })
        //
        // $("#cs-schedule-shiftseriesinput-startdatepicker-id").datepicker(
        //     {
        //         onSelect: function (data) { $("#cs-schedule-shiftseriesinput-startdate-id").val(data); }
        //     }
        // );
        // $("#cs-schedule-shiftseriesinput-startdatepicker-id").hide();
        // $("#cs-schedule-shiftseriesinput-enddatepicker-id").datepicker(
        //     {
        //         onSelect: function (data) { $("#cs-schedule-shiftseriesinput-enddate-id").val(data); }
        //     }
        // );
        // $("#cs-schedule-shiftseriesinput-enddatepicker-id").hide();
        //
        //
        // $("#cs-schedule-shiftseriesinput-startdate-id").click(function () {
        //     console.log("start date picker hover enter")
        //     if ($("#cs-schedule-shiftseriesinput-startdatepicker-id").is(":visible")) {
        //         $("#cs-schedule-shiftseriesinput-startdatepicker-id").hide();
        //     }
        //     else {
        //         $("#cs-schedule-shiftseriesinput-startdatepicker-id").show()
        //     }
        // });
        //
        // $("#cs-schedule-shiftseriesinput-end-id").click(function () {
        //     if ($("#cs-schedule-shiftseriesinput-enddatepicker-id").is(":visible")) {
        //         $("#cs-schedule-shiftseriesinput-enddatepicker-id").hide();
        //     }
        //     else {
        //         $("#cs-schedule-shiftseriesinput-enddatepicker-id").show()
        //     }
        // });
        //
        // $("#cs-schedule-shiftseriesinput-shiftlength-id").keyup(function () {
        //     var value=$("#cs-schedule-shiftseriesinput-shiftlength-id").val();
        //     $("#cs-schedule-shiftseriesinput-shiftlength-id").val(value.replace(/\D/g,''));
        //
        // })
        //
        //
        // $("#cs-submit-button-id").click(function () {
        //
        //     var addDeptJson={
        //         'companyName': $("#cs-schedule-company-id").val(),
        //         'departmentName': $("#cs-schedule-department-id").val(),
        //         'departmentDesc': $("#cs-schedule-departmentdesc-id").val()
        //     };
        //
        //     var addDeptJsonString = JSON.stringify(addDeptJson);
        //
        //     $.ajax({
        //         url: 'api/department',
        //         type: 'put',
        //         data: addDeptJsonString,
        //         headers: csrfHeaders,
        //         contentType: 'application/json',
        //         dataType: 'json',
        //         success: function (data) {
        //             console.info(data);
        //         }
        //     });
        // });



    }
})(jQuery);
