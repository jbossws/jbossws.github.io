---
layout:     post
title:       "Publishing WS endpoints through AS7 services"
subtitle:   ""
date:       July 28, 2011
author:     JBossWS Team
---



JBoss AS 7.0.0 Final
 has been released and is available to the community, so we&#39;re currently working on the 7.1.0 version of the application server, which is coming with many webservices functionalities additions. While most of them will be aim at covering 
JCP
 specification requirements (mainly 
JSR-109
 and 
JSR-101
), some management and general use new features are also coming soon.  

Recently it&#39;s been the turn of [JBoss XTS](http://www.jboss.org/jbosstm/resources/product_overview/wst.html) for being integrated in 
JBoss AS 7
.  

XTS
 provides transaction support for web services, implementing 
WS-AtomicTransaction
 and 
WS-BusinessActivity
 specifications. In order for providing such functionalities, 
XTS
 needs to start a given number of WS endpoints to take part into the transactions management. According to the 
JBoss AS 7
 design, this is to be achieved using 
JBoss AS 7
 
services
.  

Publishing a WS endpoints on 
JBoss AS7
 was previously directly bound to the processing of a given deployment unit having webservices endpoint implementation classes in it. So the required steps for achieving our goal were:  

1.  abstracting the endpoint publish process away from the deployment processing; that was possible thanks to the already existing convenient split of JBossWS endpoint publish logic into 
deployment aspect
 blocks, completely hidden behind the 
JBossWS SPI
 (iow the JBossWS internals do not directly rely on JBoss AS classes)
2.  defining a simple [API for publishing POJO endpoints](http://anonsvn.jboss.org/repos/jbossws/spi/tags/jbossws-spi-2.0.0.Beta6/src/main/java/org/jboss/wsf/spi/publish/EndpointPublisher.java) given the endpoint class names, classloader and publish address
3.  providing a
 JBoss AS7
 [implementation for such an API](https://github.com/jbossas/jboss-as/blob/master/webservices/server-integration/src/main/java/org/jboss/as/webservices/publish/EndpointPublisherImpl.java)
4.  serving that through an [AS7 
service
](https://github.com/jbossas/jboss-as/blob/master/webservices/server-integration/src/main/java/org/jboss/as/webservices/service/EndpointPublishService.java)  
We ended up with a solution that might appear pretty much equivalent to the JAX-WS 
Endpoint.publish(..)
 API at first sight, except it  

*   allows for publishing an endpoint to the HTTP server of the currently running 
JBoss AS 7
 instance
*   is embedded into a 
JBoss AS 7
 service, allowing 
efficient
, 
concurrent
 and perhaps even 
lazy/on-demand
 start/stop of endpoints as part of AS7 operations (for instance, the boot)  

The 
XTS
 integration easily leveraged the new JBossWS feature, [installing and setting up dependencies on multiple WS endpoint publisher services](https://github.com/jbossas/jboss-as/blob/master/xts/src/main/java/org/jboss/as/xts/XTSSubsystemAdd.java#L138).  

You can have a look at this by checking out the latest 
JBoss AS 7
 master from [github](https://github.com/jbossas/jboss-as) and starting the 
standalone-xts.xml
 profile. During the boot, 
XTS
 subsystem and its 
14
 required WS endpoints are concurrently started in something like 2.5s on a my mid-level laptop ;-)  
If you&#39;re writing your own AS7 component and need publishing a WS POJO endpoints as part of that, consider giving this new feature a try.




