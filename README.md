ServerMonitoring_2.0
====================

Server Monitoring Service 2.0

In this task, is necessary to create a server application for managing multiple servers, using frameworks such as:

    -Spring Framemork 3.2
    -Hibernate Framemork 4.2
    -FreeMarker Framemork

At this stage, these actions have been produced:

    -established setting for MySQL DataBase
    -Created set of models for employee and the server entity
    -Created the business logic (DAO) for enteties
    -Prodused a base functionality with access levels
    -Established access authorization with access levels on .JSPages
    -Editing is performed on the Intelli IDEA redactor, build are made with Maven, debuging performed on Jetty-maven-plugin.

Scheduled steps:

    -study FreeMarker Framemork
    -Creation of page templates
    -The organization of layout with FreeMarker Framework with .ftlayout
    -Connection of functionality with web controllers

At version 2.0 these actions have been produced:

    -Removed JPA
    -Created FreeMarker template engine tree
    -Set up the controllers by Roles
    -Connected cache to boost performance
    -When you turn creates zero "entity"
    -Established defoult entity creation (first input aims to replace the password)
    -Configuration is divided into thematic modules
    -Encoding password changed to SHA256
    -application is under debug process
    
At this stage, these actions have been produced:

    -application is under debug process
    -there are configuration problems. debugging error message showing conflict at spring-security.xml
    
    ":WARN::Nested in org.springframework.beans.factory.BeanCreationException:
    Error creating bean with name 'org.springframework.security.filterChainProxy': 
    Invocation of init method failed; nested exception is java.lang.IllegalArgumentException:
    A universal match pattern ('/**') is defined  before other patterns in the filter chain, 
    causing them to be ignored. Please check the ordering in your <security:http> namespace 
    or FilterChainProxy bean configuration:java.lang.IllegalArgumentException: 
    A universal match pattern ('/**') is defined  before other patterns in the filter chain,
    causing them to be ignored."

