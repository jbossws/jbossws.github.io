---
layout:     post
title:       "JBossWS 4.0.0.Beta1 is out!"
subtitle:   ""
date:       May 11, 2011
author:     JBossWS Team
---


After months of active development,
 the first Beta release of JBossWS 4 is finally out!
  
The main target of 
JBossWS 4
 is in supporting the upcoming 
JBoss Application Server 7
 and contributing to the innovation that&#39;s coming with it.  
For instance, AS7 comes with a new modular classloading and requires proper isolation between user classes and JBoss implementation details. As a consequence we&#39;ve gone through a complete review of ws integration classloading details, re-defined the [JBossWS public API](https://repository.jboss.org/nexus/content/groups/public/org/jboss/ws/jbossws-api/1.0.0.Beta1/) and really cleaned up a bunch of things all over the WS components. Full documentation is coming with the next beta releases, however a [FAQ for WS AS7](http://community.jboss.org/wiki/JBossWS-AS7FAQ) usage is already available.  

The 
JBossWS-CXF
 stack was also moved to including the recent [Apache CXF 2.4](http://cxf.apache.org/) release. The integration has been completely reviewed as CXF 2.4 is less bound to Spring, hence allowing 
JBossWS
 to avoid most of the tricks that were in place before to support some &#34;non-Spring&#34; scenarios.  


Apache CXF 2.4
 also includes many new features and improvements in the 
WS-Security
 area. Adding this to some additional integration work on [@EndpointConfig](https://issues.jboss.org/browse/JBWS-3282) annotation, we&#39;re finally able to support a completely [&#34;Spring-free&#34; WS-SecurityPolicy scenario](https://issues.jboss.org/browse/JBWS-3284), with signature / encryption / username token based authentication ([sources](http://anonsvn.jboss.org/repos/jbossws/stack/cxf/tags/jbossws-cxf-4.0.0.Beta1/modules/testsuite/cxf-tests/src/test/java/org/jboss/test/ws/jaxws/samples/wsse/policy/), [resources](http://anonsvn.jboss.org/repos/jbossws/stack/cxf/tags/jbossws-cxf-4.0.0.Beta1/modules/testsuite/cxf-tests/src/test/resources/jaxws/samples/wsse/policy/)).  

Finally, 
mainly because of the AS 7 improvements
, in terms of performance handling ws invocations, the current JBoss AS 7 + JBossWS 4 integration is 
up to 35% faster
 then JBoss AS 6 Final + JBossWS 3.4.1.GA  

The Beta1 artifacts are available on the JBoss Maven repository, [sources](http://www.jboss.org/jbossws/sourcecode) can be retrieved from SVN repository as usual.  

While JBoss AS 7 is meant to be the primary (and probably only, once we reach final stage) target of JBossWS 4.x, at the moment JBoss AS 6 Final is also supported as target container.  
Please note that given the AS7 is currently in very active development phase, JBossWS 4.0.0.Beta1 is fully tested against its current (today, May 11) version only. As soon as JBoss AS 7 will reach CR level, we&#39;ll add that release to the released supported target containers list of JBossWS 4.  


Please have a look at the first Beta of JBossWS 4, feedback is always welcome!





