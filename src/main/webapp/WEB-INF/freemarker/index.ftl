<#-- =====================================================================
     Index page
        - will appear as start page with authentication purpose
     ===================================================================== -->

<#import "/layout/default.ftl" as com>

<@com.page title="Login Page">
    <div class="hero-unit">
        <h1>Login page!</h1>
        <#include "common/message/login_message.ftl"/>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <h2>Please login</h2>
            <p>Please login to enter the server monitoring service</p>
        </div><!--/span-->
        <div class="span6">
            <h2>Password recovery</h2>
            <p><p>If you forgot your password you can recover it here</p>
            <p><a class="btn" href="password_recovery.html">Password recovery &raquo;</a></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>