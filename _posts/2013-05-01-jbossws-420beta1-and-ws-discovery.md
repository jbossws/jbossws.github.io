---
layout:     post
title:       "JBossWS 4.2.0.Beta1 and WS-Discovery support"
subtitle:   ""
date:       May 15, 2013
author:     JBossWS Team
---


[Just in time](http://lists.jboss.org/pipermail/wildfly-dev/2013-May/000062.html) for the first release of **WildFly**, I&#39;ve cut the first Beta release of **JBossWS 4.2** series and had it [pulled](https://github.com/wildfly/wildfly/pull/4501) into the application server.  

The latest build includes the additions I provided a preview of in the [previous post](http://jbossws.blogspot.it/2013/04/jbossws-42-feature-preview.html) as well as integration of few new features from _Apache CXF_. The most notable one is probably the [WS-Discovery](http://docs.oasis-open.org/ws-dd/discovery/1.1/os/wsdd-discovery-1.1-spec-os.html) support, which you can get some preliminary doc on [here](https://docs.jboss.org/author/display/JBWS/WS-Discovery).  

Users can now have ws endpoints from selected deployments be automatically registered in a WS-Discovery service endpoint; that in turn can be queried to locate existing endpoints over the network. Nice, isn&#39;t it? :-)  

Feel free to give the Beta a try, artifacts are available on the Maven repo as usual. Feedback is welcome, as always!  





