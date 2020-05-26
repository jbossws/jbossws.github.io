---
layout:     post
title:       "Stressing WS features of JBoss AS 6 Final"
subtitle:   ""
date:       January 07, 2011
author:     JBossWS Team
---



JBoss AS 6.0.0.Final
 has been [released](http://www.jboss.org/jbossas/downloads) few days ago. It passes the 
JavaEE 6 Web Profile
 certification testsuite and that&#39;s probably one the most notable achievements of this release. You should really read Dimitris [blog on this topic](http://dandreadis.blogspot.com/2011/01/introducing-brand-new-jboss-as-60.html), which gives a wide view of what&#39;s new with AS 6, especially if compared to the previous final version, JBoss AS 5.1.  

An interesting discussion is currently on on [JBoss forum](http://community.jboss.org/thread/160813?tstart=0) regarding JavaEE 6 Web Profile vs Full Profile... while it&#39;s important for users/customer to provide feedback, please note that in terms of web services features, JBossWS-CXF 3.4.1.GA included in JBoss AS 6 Final is already passing the relevant tck modules of the full profile certification.  
Moreover, you most probably already know that [Apache CXF](http://cxf.apache.org/) 2.3.1, which is consumed by JBossWS-CXF 3.4.1, is JAX-WS 2.2 compliant.  

So, despite having the EE6 Web Profile stamp only on it, JBoss AS 6 really has all you need for safely leveraging the latest revisions of webservices technologies (JSR-224, JSR-109, etc.).  
Moreover, in addition to the standard compliance above, the switch from the Native stack to the Apache CXF based one brings 
improved WS-* support
 (try out the WS-Policy support with security, addressing, reliable messaging, etc.) and 
better performances
.  

... 
just the WS contribution to JBoss AS 6 Final actually being 
more
 than a certified Java EE6 Web Profile implementation
 ...




