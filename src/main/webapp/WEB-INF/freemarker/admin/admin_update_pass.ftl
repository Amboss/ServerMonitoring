<#-- =====================================================================
     Password update page for first admin entrance
        - force admin to change existing password
     ===================================================================== -->
<#import "/spring.ftl" as spring />
<#assign security=JspTaglibs["/WEB-INF/tlds/spring-security.tld"] />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
    <!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
    <!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
    <!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <link rel="shortcut icon" href="<@spring.url '/static/img/favicon_02.ico'/>" type="image/x-icon"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/bootstrap-responsive.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/bootstrap-responsive.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/bootstrap.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/bootstrap.min.css'/>"/>
        <style type="text/css">
                    body {
                        padding-top: 60px;
                        padding-bottom: 40px;
                    }
                    .hero-unit {
                        background-image: url("<@spring.url'/static/img/header_default2.png'/>");
                        background-repeat: repeat;
                    }
                </style>
        <script type="text/javascript" src="<@spring.url '/static/js/bootstrap.js'/>" ></script>
        <script type="text/javascript" src="<@spring.url '/static/js/bootstrap.min.js'/>" ></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"; charset="utf-8">
        <meta name="description" content="Server Monitoring Service">
        <title>Password update</title>
    </head>
    <body>
        <div class="container">
            <!--[if lt IE 7]>
                <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">
                Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">
                install Google Chrome Frame</a> to better experience this site.</p>
            <![endif]-->
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container">
                        <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="brand" href="#">Server Monitoring Service</a>
                        <div class="nav-collapse collapse">
                            <p class="navbar-text pull-right" style="padding-right: 20px;">Welcome ${username!"Anonymous" }!</p>
                        </div>
                    </div><!-- /.container -->
                </div><!-- /.navbar-inner -->
            </div><!-- /.navbar navbar-inverse -->
            <div class="hero-unit">
                <h1>Admin first entrance</h1>
            </div>
            <div class="row-fluid">
                <form class="form-horizontal" method="post" autocomplete="off" >
                    <h3>Please enter new password:</h3>
                        <#-- ===================== New Password ============================= -->
                        <div class="control-group info">
                            <label class="control-label" for='new_password'>New Password</label>
                            <div class="controls">
                                <input class="input-xlarge"
                                id="new_password"
                                name='new_password'
                                type='password'
                                autofocus/>
                                <#if RequestParameters['newPassword']??>
                                    <span class="help-inline alert alert-error" path="newPassword">
                                        <@spring.message "<br>", "errors"/>
                                    </span>
                                </#if>
                            </div>
                        </div>
                        <#-- ===================== Confirm Password ========================= -->
                        <div class="control-group info">
                            <label class="control-label" for="confirm_password">Confirm Password</label>
                            <div class="controls">
                                <input class="input-xlarge"
                                id="confirm_password"
                                name='confirm_password'
                                type='password' />
                                <#if RequestParameters['confirmPassword']??>
                                    <span class="help-inline alert alert-error" path="confirmPassword">
                                        <@spring.message "<br>", "errors"/>
                                    </span>
                                </#if>
                            </div>
                        </div>
                        <#-- ===================== Buttons ================================== -->
                        <div class="control-group">
                            <div class="controls">
                                <input class="btn btn-primary"
                                type='submit'
                                name='update'
                                value='Change password'  />
                            </div>
                        </div>
                </form>
            </div><!--/row-->
            <hr>
            <div class="row-fluid">
                <div id="toplink">
                    <a href="#top" class="top-link" title="Back to top">Back To Top <i class="icon-chevron-up"></i></a></div>
                    <div class="subfooter">
                        <div class="span6">
                            <#import "../common/message/info.ftl" as my>
                            <#include "../common/footer/copyright.ftl">
                        </div>
                    </div>
                </div><!-- /.toplink -->
            </div><!-- /.row-fluid -->
        </div><!-- /.container -->
    </body>
</html>
