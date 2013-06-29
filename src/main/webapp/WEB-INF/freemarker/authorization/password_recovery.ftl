<#-- =====================================================================
     Password recovery
        - give ability to recover password if user profile is active in DB
     ===================================================================== -->

<#import "/layout/default.ftl" as com>

<@com.page title="Password recovery">
    <div class="hero-unit">
        <h1>Password recovery</h1>
        <#--include "common/message/login_message.ftl"/-->
    </div>
    <div class="row-fluid">
        <div class="span12">
            <h2>Password recovery</h2>
            <p><p>If you forgot your password you can recover it here</p>
            <p><a class="btn" href="password_recovery.html">Password recovery &raquo;</a></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>