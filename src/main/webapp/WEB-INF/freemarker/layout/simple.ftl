 <#-- =====================================================================
      SIMPLE layout
         - for submission and error pages
      ===================================================================== -->
<#import "/util/spring.ftl" as spring />

<#macro page title>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
    <!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
    <!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
    <!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <link rel="shortcut icon" href="<@spring.url '/static/img/favicon_02.ico'/>" type="image/x-icon"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/static/css/bootstrap.css'/>"/>

        <script type="text/javascript" src="<@spring.url '/static/js/bootstrap.js'/>" ></script>
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

         <div class="container">
             <#nested/>
         </div>

    </body>
</html>

</#macro>