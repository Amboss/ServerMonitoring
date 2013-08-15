<#-- =====================================================================
     Server assignment page
        - will appear after sign in page with server info purpose
        - for admin_role the layout wil contain service menu on side bar
     ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<#import "/layout/admin.ftl" as com />

<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<@com.page title="Server assignment">

    <#-- ===================== JQuery functions ========================= -->

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
            width: 120px;
        }
    </style>

    <script type="text/javascript" charset="utf-8" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "#sortable1, #sortable2" ).sortable({
                connectWith: ".connectedSortable"
            }).disableSelection();
        });
    </script>

    <#-- ===================== Head with name of page ========================= -->
    <div class="hero-unit">
        <h2>Server assignment</h2>
    </div>

    <#-- ===================== Row for assigned servers table ========================= -->
    <div class="row-fluid">
        <h3>Assigned servers for user ${employee.employee_name}:</h3>
    </div>

    <#-- ===================== Row for available servers table ========================= -->
    <div class="row-fluid">
        <h3>Available servers:</h3>

        <#if assignedServers?has_content>

            <#list assignedServers as responsible >
                <ul id="sortable1" class="connectedSortable">
                    <li class="ui-state-default">${responsible.server_name}</li>
                </ul>
            </#list>

            <#if availableServers?has_content>

                <#list availableServers as available >
                    <ul id="sortable2" class="connectedSortable">
                        <li class="ui-state-default">${available.server_name}</li>
                    </ul>
                </#list>

            <#else>
                <h4>There is no available servers to assign.</h4>
            </#if>
        <#else>
            <h4>This employee have no assigned servers yet.</h4>
        </#if>

        <#-- ===================== Buttons ================================== -->
        <div class="control-group">
            <div class="controls">
                <input class="btn btn-primary"
                        type='submit'
                        name='update'
                        value='Assign'/>
                <input class="btn"
                        type='submit'
                        name='cancel'
                        value='Cancel'/>
            </div>
        </div>
    </div>

    <#-- ===================== Icon Information row  ========================= -->

    <div class="row-fluid">
        <div class="span6">
            <h3>State icons</h3>
            <p><i class="icon-ok" ></i>&nbsp;- icon shows that server have no errors</P>
            <p><i class="icon-warning-sign" ></i>&nbsp;- icon shows that server are working with HTTP
            response other than 200</P>
            <p><i class="icon-ban-circle" ></i>&nbsp;icon shows that server is not responding to request,
             or responding with HTTP 500 error</P>
        </div><!--/span-->
        <div class="span6">
            <h3>Active icons</h3>
            <p><i class="icon-thumbs-up" ></i>&nbsp;- icon shows that current server is under monitoring</P>
            <p><i class="icon-ban-circle" ></i>&nbsp;- icon shows that current server is not under monitoring</P>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>