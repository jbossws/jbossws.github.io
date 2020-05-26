---
layout:     post
title:       "JBossWS 4.3.0.Final is available!"
subtitle:   ""
date:       March 20, 2014
author:     JBossWS Team
---


A couple of days ago [JBossWS](http://www.jboss.org/jbossws) 4.3.0.Final has been released.  

The latest minor release of the JBoss Web Services stack improves stability, by preventing concurrency issues and ensuring thread safety whenever required. Besides for that, the embedded Apache libraries have been updated, in particular [Apache CXF](http://cxf.apache.org/) 2.7.10 and [Apache WSS4J](https://ws.apache.org/wss4j/) 1.6.14 are now included.  

Finally, the SOAP Profile of JSR-196 (Java Authentication Service Provider Interface for Containers - JASPIC) is now supported and the JBossWS integration code comes with a [predefined server authentication module](https://docs.jboss.org/author/display/JBWS/Authentication#Authentication-JASPIAuthentication) for relying on credentials coming in a WS-Security UsernameToken.  

As usual, multiple bug fixes and testsuite improvements are also part of the release.   

The binary and source distributions are available for [download](http://www.jboss.org/jbossws/downloads/latest) and the latest Maven artifacts have been added to the [repository](https://repository.jboss.org/nexus/content/groups/public/org/jboss/ws/).  

Speaking of Maven repositories, it&#39;s some months now since the JBossWS artifacts are properly mirrored to the [Maven Central repository](http://central.maven.org/maven2/org/jboss/ws/cxf/jbossws-cxf-client/), meaning everybody can use JBossWS even without setting the JBoss Nexus repository in local Maven _settings.xml_ :-)  

The supported target containers for this release are JBoss AS 7.2.0.Final and WildFly 8.0.0.Final. Due to the required [changes in the JBossWS SPI](https://community.jboss.org/wiki/JBossWS430MigrationNotes), the next version of WildFly which will be coming with JBossWS 4.3.0.Final by default will most likely be WildFly 9.  Enjoy!




