---
layout:     post
title:       "JBossWS 5.1.0.Final is available!"
subtitle:   ""
date:       September 02, 2015
author:     JBossWS Team
---


One month after the beta release, **JBossWS 5.1.0** is eventually **final**!  

This time, we have gone for a time-boxed release approach and limited the number of new features on top of previous release. The reason is that we really wanted the upcoming **WildFly 10** to include the latest and greatest WS components. As a matter of fact, JBossWS 5.1.0 brings **Apache CXF 3.1** series into the WildFly container; the latest [Apache CXF](http://cxf.apache.org/) versions, in turn, come with few **performance improvements** that we directly contributed to, while working on optimizing the whole WildFly WS stack.  

On top of that and besides for a bunch of bug fixes, an interesting new feature has just been added to JBossWS 5.1, which is support for _jboss-webservices.xml_ descriptor in EAR deployment archives. This allows setting a given behaviour of the stack for all sub-deployments included in an EAR archive packaged application, preventing the user from having to specify the same options in multiple _jboss-webservices.xml_ descriptors.  

You can have a look at the [release notes](http://download.jboss.org/jbossws/ReleaseNotes-jbossws-cxf-5.1.0.Final.txt) for a list of the improvements and fixed issues.  

JBossWS 5.1.0.Final is available for direct [download](http://jbossws.jboss.org/downloads/latest) and on both the JBoss and Maven Central repositories. The release documentation is [here](https://docs.jboss.org/jbossws/5.1.0.Final/).  

The supported target containers are WildFly 8.2.1.Final, WildFly 9.0.0.Final and WildFly 9.0.1.Final... and of course WildFly 10.0.0.CR1 and later will be including it by default already.  

Enjoy!




