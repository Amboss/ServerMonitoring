<#-- =====================================================================
     Index page
        - will appear as start page with authentication purpose
     ===================================================================== -->

<#import "/layout/default.ftl" as com>

<@com.page title="Login Page">
    <div class="hero-unit">
        <h1>Login page!</h1>
        <#include "common/message/login_message.ftl"/>
        <p>Please login to enter to the service</p>
        <p>If you forgot your password you can recover it here: <a href="password_recovery.html">Password recovery</a></p>
    </div>
    <div class="row-fluid">
        <div class="span4">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
            <p><a class="btn" href="#">View details &raquo;</a></p>
        </div><!--/span-->
        <div class="span4">
           <h2>Heading</h2>
           <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
           <p><a class="btn" href="#">View details &raquo;</a></p>
        </div><!--/span-->
        <div class="span4">
           <h2>Heading</h2>
           <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
           <p><a class="btn" href="#">View details &raquo;</a></p>
        </div><!--/span-->
    </div><!--/row-->
</@com.page>