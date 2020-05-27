---
layout:     post
title:       "JBossWS 3.1.0.GA released"
subtitle:   ""
date:       March 03, 2009
author:     JBossWS Team
---


The first post on this blog is to announce the release of [JBossWS 3.1.0.GA](http://www.jboss.org/jbossws).  
For those who are not familiar to the project, starting from major release 3, JBossWS provides an [integration layer](http://jbossws.jboss.org/mediawiki/index.php?title=WSF) allowing users to leverage different webservice stacks (JBossWS-Native, Apache CXF and Metro) on JBossAS.  

Probably the most important achievement in JBossWS 3.1.0.GA is the improvement in terms of JAX-WS 2.1 support, as that&#39;s now available cross-stack:  

*   the 2.1 implementation has been completed in JBossWS-Native (CXF and Metro already support JAX-WS 2.1);
*   the wsconsume tool now supports target 2.1;
*   WebServiceContext now supports JAX-WS 2.1 specific methods with all stacks.
This means now users can really leverage JAX-WS 2.1 additions (EndpointReference, WebServiceFeature, etc.) on JBoss AS with every supported stacks. You all know how easy and handy is enabling WS-Addressing and MTOM/XOP encoding with 
@Addressing
 and 
@MTOM
 ;-)  

Besides the JAX-WS 2.1 support, JBossWS 3.1.0.GA has further new features, improvements and bug fixes in each stack, please refer to the release notes:  

*   [ReleaseNotes-jbossws-native](http://labs.jboss.com/file-access/default/members/jbossws/downloads/ReleaseNotes-jbossws-native-3.1.0.GA.txt)

*   [ReleaseNotes-jbossws-metro](http://labs.jboss.com/file-access/default/members/jbossws/downloads/ReleaseNotes-jbossws-metro-3.1.0.GA.txt)

*   [ReleaseNotes-jbossws-cxf](http://labs.jboss.com/file-access/default/members/jbossws/downloads/ReleaseNotes-jbossws-cxf-3.1.0.GA.txt)The supported target containers for this release are JBoss 4.2.3.GA, JBoss 5.0.0.GA and JBoss 5.0.1.GA.  

The binaries and sources, including the samples can be obtained [here](http://labs.jboss.com/jbossws/downloads).  

[Installation instructions](http://jbossws.jboss.org/mediawiki/index.php?title=Installation), [quick start](http://jbossws.jboss.org/mediawiki/index.php/Quick_Start) and [user guides](http://jbossws.jboss.org/mediawiki/index.php/JAX-WS_User_Guide) are available on the [JBossWS mediawiki](http://jbossws.jboss.org/mediawiki):  


[http://jbossws.jboss.org/mediawiki](http://jbossws.jboss.org/mediawiki)  






