<#-- =====================================================================
     index page will appear as start page with authentication purpose
     ===================================================================== -->
<#import "/layout/employee.ftl" as com>

<@com.page title="Login Page">
     <#include "common/message/login_message.ftl"/>
     <div class="welcome">

        <p>Please login to enter to the service</p>
        <p>If you forgot your password you can recover it here</p>
     </div>
</@com.page>