---
layout:     post
title:       "Moving towards JBossWS 5..."
subtitle:   ""
date:       March 24, 2015
author:     JBossWS Team
---


Even if I&#39;ve been silent here lately, my team is actively working on **JBossWS 5** and we&#39;re getting closer to the first non-Beta release.  

The first Beta release ([JBossWS 5.0.0.Beta1](https://issues.jboss.org/issues/?jql=project%20%3D%20JBWS%20AND%20fixVersion%20%3D%20jbossws-cxf-5.0.0.Beta1%20ORDER%20BY%20issuetype%20DESC)) was tagged in September last year; it brought Apache CXF 3 series in JBossWS / WildFly and eventually dropped JAX-RPC support ;-)  

[JBossWS 5.0.0.Beta2](https://issues.jboss.org/issues/?jql=project%20%3D%20JBWS%20AND%20fixVersion%20%3D%20jbossws-cxf-5.0.0.Beta2%20ORDER%20BY%20issuetype%20DESC) was tagged a couple of months later and featured [improvements in the pre-defined client/endpoint configuration area](https://docs.jboss.org/author/display/JBWS/Predefined+client+and+endpoint+configurations#Predefinedclientandendpointconfigurations-Automaticconfigurationfromdefaultdescriptors) and the [options for declaring Apache CXF interceptors through simple configuration properties](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-ApacheCXFinterceptors).  

The latest Beta release so far, [JBossWS 5.0.0.Beta3](https://issues.jboss.org/issues/?jql=project%20%3D%20JBWS%20AND%20fixVersion%20%3D%20jbossws-cxf-5.0.0.Beta3%20ORDER%20BY%20issuetype%20DESC), has been tagged and integrated in current WildFly master last month; the most notable change in it is the completely [new build and testsuite framework](https://docs.jboss.org/author/display/JBWS/Build+and+testsuite+framework), which now fully relies on [Arquillian](http://arquillian.org/) and ShrinkWrap for WS endpoints testing.  

More interesting additions, in the direction of enabling [configuring Apache CXF proprietary features without recurring to Spring descriptors](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-Propertiesdrivenbeancreation), have already been implemented and are coming soon.  

Now, you&#39;re welcome to go, [checkout JBossWS 5.0.0.Beta3 tag](http://anonsvn.jboss.org/repos/jbossws/stack/cxf/tags/jbossws-cxf-5.0.0.Beta3/) and play with it. Or you can simply hack on the [latest WildFly master](https://github.com/wildfly/wildfly). (also note that WildFly 9 Beta1 is being released very soon!)  

Alternatively, you could also play with another recent WS release, the 1.1.0.Beta1 version of the our _jaxws-codefirst_ Maven Archetype, which has just been moved to rely on JBossWS 5 Beta artifacts. You can read about it in a [previous post](http://jbossws.blogspot.it/2014/09/how-to-kick-start-ws-project-in-few.html) of mine, but it basically boils down to simply using the following command line to kick start a WS project in few seconds:  



_mvn archetype:generate -Dfilter=org.jboss.ws.plugins.archetypes:_ 


That&#39;s all for now, stay tuned, JBossWS 5 is coming... :-)




