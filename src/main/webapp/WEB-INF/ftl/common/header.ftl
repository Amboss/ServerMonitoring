[#ftl]
[#-- this is header --]
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
    <!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
    <!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
    <!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
		<#include "../common/common_library.ftl"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"; charset="utf-8">
        <meta name="description" content="Server Manager Service">
        <title>${pageTitleFromHttpRequest}</title>
    </head>
    <body>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="/WEB-INF/static/js/bootstrap.min.js"></script>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">
            Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">
            install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <div class="nav-collapse collapse">
                        [#include "../authorization/login_form.ftl"/]
                     </div><!--/.nav-collapse -->
                    [#if active?? ]
                        <div class="header_menu">
                            [#include "../common/header/header_menu.ftl"]
                        </div>
                        <div class="header_status">
                            [#include "../common/header/header_status.ftl"]
                        </div>
                    [/#if]
                </div>
            </div>
        </div>