[#ftl]
[#-- loading the "spring.ftl" macro library --]
[#import "/spring.ftl" as spring /]

[#-- loading the JspTaglibs --]
[#assign spring =JspTaglibs["http://www.springframework.org/tags"] /]
[#assign form =JspTaglibs["http://www.springframework.org/tags/form"] /]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]

<link rel="stylesheet" type="text/css" href="<@spring.url '/WEB-INF/static/css/style.css'/>"/>
<link rel="stylesheet" type="text/css" href="<@spring.url value="/WEB-INF/static/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<@spring.url value="/WEB-INF/static/css/bootstrap-responsive.min.css"/>">