<#-- =====================================================================
     Server assignment page
        - will appear after sign in page with server info purpose
        - for admin_role the layout wil contain service menu on side bar
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<#import "/layout/admin.ftl" as com />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<#assign pageTitle><@spring.message "serv_assign.title" /></#assign>

<@com.page title="${pageTitle}">

    <#-- ===================== CSS ========================= -->

    <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/ui.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/ui_theme.css'/>"/>
    <style>
        #sortable1, #sortable2 {
            list-style-type: none; margin: 0;
            padding: 0 0 2.5em; float: left;
            margin-right: 10px;
        }

        #sortable1 li, #sortable2 li {
            margin: 0 5px 5px 5px;
            padding: 5px;
            font-size: 1.2em;
            width: 180px;
            cursor:move;
        }
        #listBox {
            border-style:solid;
            border-width:1px;
            border-color: lightblue;
            border-radius: 5px;
            padding: 0 20px 0 20px;
        }
    </style>

    <#-- ===================== JQuery functions ========================= -->

        <script type="text/javascript" charset="utf-8" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script>
            /* function handel's two combined list */
            $(function() {
                var assigned = "";
                var available = "";

                /* handel's first list */
                $('#sortable1').sortable({
                    connectWith: '.connectedSortable',
                    start: function (e, ui) {
                            $('.connectedSortable').css('min-height', '40px');
                            $('.connectedSortable').css('min-width', '190px');
                            $('.connectedSortable').sortable('refreshPositions');
                    },
                    dropOnEmpty: true,
                    update: function(event, ui) {
                        assigned = $(this).sortable("toArray", { key: "id" });
                        }
                }).disableSelection();

                /* handel's second list */
                $('#sortable2').sortable({
                    connectWith: '.connectedSortable',
                    start: function (e, ui) {
                           $('.connectedSortable').css('min-height', '40px');
                           $('.connectedSortable').css('min-width', '190px');
                           $('.connectedSortable').sortable('refreshPositions');
                    },
                    dropOnEmpty: true,
                    update: function(event, ui) {
                        available = $(this).sortable("toArray", { key: "id" });
                        }
                }).disableSelection();

                /* handel's pass by click made by Ajax */
                $('#assign').click(function(){
                    $.ajax({
                        type: "POST",
                        url: "/ServerMonitoring/employee_management/serv_assignment/2",
                        data: JSON.stringify({
                                assignedServers: assigned,
                                availableServers: available
                        }),
                        datatype: "json",
                        'scriptCharset': "utf-8",
                        'contentType': "application/json;charset=UTF-8",
                        success: function(data, textStatus) {
                            window.location.replace("/ServerMonitoring/employee_management/employee_manager");
                            },
                        error: function (e) {
                            //alert("Error: " + e);
                        }
                    });
                    return false;
                });
                //debugger
            });
        </script>

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>${pageTitle}</h2>
    </div>

    <#-- ===================== Row for assigned servers table ========================= -->
    <div class="row-fluid">
        <h3><@spring.message "serv_assign.main_row.title" />: <i>${employee.employee_name}</i></h3>
    </div>
    <hr>

    <div class="row-fluid">
        <form id="serverAssignmentForm" method="post" >
            <div class="span5" id="listBox">
                <h3><@spring.message "serv_assign.assign_list" /></h3>
                <ul id="sortable1" class="connectedSortable">
                    <#list assignedServers as responsible >
                        <li class="ui-state-default" id="${responsible.id}" >${responsible.server_name}</li>
                    </#list>
                </ul>
            </div>

            <div class="span5" id="listBox">
                <h3><@spring.message "serv_assign.available_list" /></h3>
                <ul id="sortable2" class="connectedSortable">
                    <#list availableServers as available >
                        <li class="ui-state-default" id="${available.id}" >${available.server_name}</li>
                    </#list>
                </ul>
            </div>
            <#-- ===================== Buttons ================================== -->

            <div class="span2">
                <div class="control-group">
                    <div class="controls">
                        <center>
                            <p><input class="btn btn-large btn-primary"
                                    id="assign"
                                    type='submit'
                                    name='update'
                                    value='<@spring.message "serv_assign.btn_assign" />'/></p>
                            <p><input class="btn btn-large"
                                    type='submit'
                                    name='cancel'
                                    value='<@spring.message "serv_assign.btn_cancel" />'/></p>
                        </center>
                    </div><!--/controls-->
                </div>
            </div><!--/span2-->
        </form>
    </div><!--/row-->
    <hr>
    <#-- ===================== Icon Information row  ========================= -->

    <div class="row-fluid">
        <div class="span6">
            <h3><@spring.message "serv_assign.list_icon.title" /></h3>
            <p><@spring.message "serv_assign.list_icon.text" /></P>
        </div>
        <div class="span6">
            <h3><@spring.message "serv_assign.how_icon.title" /></h3>
            <p><@spring.message "serv_assign.how_icon.text" /></P>
        </div>
    </div><!--/row-->
</@com.page>