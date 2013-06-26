<#-- ==============================================================
     HEADER part of common layout
     ============================================================== -->
<#import "/spring.ftl" as spring />

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

        <script type="text/javascript" src="<@spring.url '/static/js/bootstrap.js'/>" ></script>
        <script type="text/javascript" src="<@spring.url '/static/js/bootstrap.min.js'/>" ></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"; charset="utf-8">
        <meta name="description" content="Server Monitoring Service">
        <title>${title?html}</title>
    </head>
    <body>
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
                    <a class="brand" href="../employee/monitoring.html">Server Monitoring Service</a>
                    <div class="nav-collapse collapse">
                        <div class="header_menu">
                            <#include "header/header_menu.ftl">
                        </div>
                        <div class="nav-collapse collapse">
                            <#include "header/login_form.ftl"/>
                        </div>
                    </div>
                </div><!-- /.container -->
            </div><!-- /.navbar-inner -->
        </div><!-- /.navbar navbar-inverse -->

