---
layout:     post
title:       "JBossWS 5.0.0.Final is available!"
subtitle:   ""
date:       April 27, 2015
author:     JBossWS Team
---


**I&#39;m happy to announce that JBossWS 5.0.0.Final is out!**  

The release comes with lots of new features and bug fixes. To get an idea of the new functionalities you can have a look at previous posts [here](http://jbossws.blogspot.com/2015/03/moving-towards-jbossws-5.html), [here](http://jbossws.blogspot.com/2014/09/wildfly-9-and-jbossws-5-future-is-coming.html) and [here](http://jbossws.blogspot.com/2014/09/how-to-kick-start-ws-project-in-few.html).  

Besides for the upgrade to [Apache CXF](http://cxf.apache.org/) 3 series, which brings a lot of new and interesting [features](http://cxf.apache.org/docs/30-migration-guide.html), this release mainly focuses on providing simplified configurations options to the user. In particular:  

*   it makes very easy to have predefined client and endpoint configurations set for user WS client and endpoints; this is achieved by [sensible defaults to the configuration names](https://docs.jboss.org/author/display/JBWS/Predefined+client+and+endpoint+configurations#Predefinedclientandendpointconfigurations-Automaticconfigurationfromdefaultdescriptors)
*   it allows declaring Apache CXF [interceptors](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-ApacheCXFinterceptors) and [features](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-Features) using configuration properties
*   it allows [tuning the default Apache CXF HTTP Conduit](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-HTTPConduitconfiguration), effectively making it possible to control HTTP transport options (like e.g. chunk mode) through system properties.
With the above items (as well as [other new functionalities](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-Propertiesdrivenbeancreation)) covering most of the scenarios in which users would have needed Spring based descriptors, the JBossWS integration with Spring is now declared deprecated.  

It&#39;s now time for you to download JBossWS 5.0.0.Final, install and give it a try! Feedback is welcome as usual!  

The documentation is included in the [release](http://jbossws.jboss.org/downloads/latest) (together with the sample testsuite) and is also available at [https://docs.jboss.org/jbossws/5.0.0.Final/](https://docs.jboss.org/jbossws/5.0.0.Final/) in its latest version.  

The supported target containers for this release are WildFly 8.0.0.Final, WildFly 8.1.0.Final and WildFly 8.2.0.Final. The current WildFly master has also been updated to the new WS components so that future WildFly releases will come with it.  

Have fun :-)




