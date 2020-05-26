---
layout:     post
title:       "Controlling Apache CXF Bus creation for JAXWS clients"
subtitle:   ""
date:       October 23, 2013
author:     JBossWS Team
---


JBossWS 4.2.2.Final has just been [released](http://www.jboss.org/jbossws/news) and [installed](https://github.com/wildfly/wildfly/commit/c8212615a0d1006c1f4c652743493d297eadb1e5) in current [WildFly](http://wildfly.org/) master.  

The new version comes with [multiple bug fixes](http://download.jboss.org/jbossws/ReleaseNotes-jbossws-cxf-4.2.2.Final.txt) as well as a [relevant new feature](https://issues.jboss.org/browse/JBWS-3713) for controlling the [Apache CXF](http://cxf.apache.org/) Bus creation whenever JAXWS clients are built.  

Basing the JAXWS functionalities of JBoss AS / WildFly on Apache CXF opened up interesting integration topics, one of them being when to create new CXF Bus instances and when / how to share them in the container. With the latest JBossWS integration release, the strategy for selecting/creating Bus instances to serve (in-container) JAXWS client can be chosen by the final user. This allows better control and can also be used for performance tuning. You can read more about the new feature in the [documentation](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-BusselectionstrategiesforJAXWSclients).  

The release also comes with upgrades to Apache CXF 2.7.7 and Apache Santuario 1.6.12.  

It&#39;s now time for you to [download](http://www.jboss.org/jbossws/downloads/latest) JBossWS 4.2.2.Final, install it and give it try! Feedback is welcome as usual!  

The supported target containers for this release are JBoss AS 7.1.2.Final, JBoss AS 7.1.3.Final and JBoss AS 7.2.0.Final. Of course, next releases of WildFly are also going to include the new WS components.




