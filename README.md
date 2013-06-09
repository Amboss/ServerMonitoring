Server Monitoring Service
=========================

### Application can be initiated:
* maven-war-plugin:     war:run
* maven-jetty-plugin:   jetty:run
* tomcat7-maven-plugin: tomcat7:deploy
  - for tomcat7 usage, entity credentials must be localised in configuration teg in pom.xml:
    ...
   <groupId>org.apache.tomcat.maven</groupId>
   <artifactId>tomcat7-maven-plugin</artifactId>
   <version>2.0</version>
   <configuration> ... </configuration>

### Access URL for application:
http://localhost:8080/ServerMonitoring/auth/login