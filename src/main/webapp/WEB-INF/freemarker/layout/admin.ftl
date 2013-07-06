<#-- ==============================================================
     ADMIN layout
        - for administrative pages
        - with access ROLE_ADMIN
     ============================================================== -->
<#macro page title>
<#include "../common/header.ftl"/>
    <div class="container">
        <div class="row-fluid">
            <div class="span3">
                <#include "../common/header/admin_sidebar1.ftl"/>
            </div>
            <div class="span9">
                <#nested/>
            </div>
        </div><!--/row-->
    </div><!--/container-->
<#include "../common/footer.ftl"/>
</#macro>