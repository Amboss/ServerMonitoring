<#-- =====================================================================
     Server assignment page
        - will appear after sign in page with server info purpose
        - for admin_role the layout wil contain service menu on side bar
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<#import "/layout/admin.ftl" as com />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@com.page title="Server assignment">

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
            $(function() {
                var assigned = "";
                var available = "";

                $('#sortable1').sortable({
                    connectWith: '#sortable2',
                    update: function(event, ui) {
                        assigned = $(this).sortable("toArray", { key: "id" });
                        }
                }).disableSelection();

                $('#sortable2').sortable({
                    connectWith: '#sortable1',
                    update: function(event, ui) {
                        available = $(this).sortable("toArray", { key: "id" });
                        }
                }).disableSelection();

                $('#assign').click(function(){
                    $.ajax({
                        type: "POST",
                        url: "/ServerMonitoring/employee_management/serv_assignment/2",
                        data: JSON.stringify({
                             serversListModel: {
                                 "assignedServers": assigned,
                                 "availableServers": available
                             }
                         }),
                        datatype: "jsondata",
                        contentType: 'application/json',
                        success: function(data) {
                            alert("Success");
                        },
                        error: function (e) {
                            alert("Error: " + e);
                        }
                    });
                    return false;
                });
                //debugger
            });
        </script>

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Server assignment</h2>
    </div>

    <#-- ===================== Row for assigned servers table ========================= -->
    <div class="row-fluid">
        <h3>Assigned servers for user: <i>${employee.employee_name}</i></h3>
    </div>
    <hr>

    <div class="row-fluid">
        <form id="serverAssignmentForm" method="post" >
            <div class="span5" id="listBox">
                <h3>Assigned Servers</h3>
                <#if assignedServers?has_content>
                    <ul id="sortable1" class="connectedSortable">
                        <#list assignedServers as responsible >
                            <li class="ui-state-default"
                                id="${responsible.id}" >${responsible.server_name}</li>
                        </#list>
                    </ul>
                <#else>
                    <h4>This employee have no assigned servers yet.</h4>
                </#if>
            </div>

            <div class="span5" id="listBox">
                <h3>Available Servers</h3>
                <#if availableServers?has_content>
                    <ul id="sortable2" class="connectedSortable">
                        <#list availableServers as available >
                            <li class="ui-state-default"
                                id="${available.id}" >${available.server_name}</li>
                        </#list>
                    </ul>
                <#else>
                    <h4>There is no available servers to assign.</h4>
                </#if>
            </div>
            <#-- ===================== Buttons ================================== -->


            <#--@spring.formHiddenInput "settings.id"/-->

            <div class="span2">
                <div class="control-group">
                    <div class="controls">
                        <center>
                            <p><input class="btn btn-large btn-primary"
                                    id="assign"
                                    type='submit'
                                    name='update'
                                    value='Assign'/></p>
                            <p><input class="btn btn-large"
                                    type='submit'
                                    name='cancel'
                                    value='Cancel'/></p>
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
            <h3>List</h3>
            <p>On left side of upper row you can find Assigned Servers for current employee, if
            employee have to assigned server - the row will contain message no notify about that.</P>
        </div>
        <div class="span6">
            <h3>How to</h3>
            <p>The right row holds available servers for assignment. To make assignment - simply drag the name
            of server to the left row and click "Assign" button.</P>
        </div>
    </div><!--/row-->
</@com.page>