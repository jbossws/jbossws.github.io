---
layout:     post
title:       "Ready for JBoss AS 6... and then?"
subtitle:   ""
date:       December 17, 2010
author:     JBossWS Team
---


Community members watching recent JBossWS subversion repository activity might have noticed that we&#39;ve been having two open 3.4.x branches for quite some time. What&#39;s the meaning of that?  



From one of the branches, 
JBossWS 3.4.0
 was released just last week and had many new features, available for already released AS containers (including AS 6 CR1 and AS 5.1.0.GA). That came with JAXWS 2.2, JSR 109 v1.3, etc. - you can read all the details on [my previous blog post](http://jbossws.blogspot.com/2010/12/jbossws-340-has-landed.html).  



On the other side, the efforts above on webservices technologies are of course meant for eventually satisfying the general 
JBoss Application Server
 EE6 [certification needs](http://community.jboss.org/wiki/AS600FinalStatusExecutiveSummary). Because of that, a lot of work is being done on JBoss AS 6 trunk. In particular we had to review part of the internal webservice integration layer with the AS, to accommodate other components requirements and changes. This all happened on the branches for 
JBossWS 3.4.1
, 
which has finally been released on yesterday and is ready for being the final ws stack of 
JBoss Application Server 6

.  
So 3.4.1 basically introduces just few minor additions over 3.4.0 for the end users (see the release notes [[1](https://issues.jboss.org/secure/ReleaseNote.jspa?projectId=12310050&amp;version=12314348)][[2](https://issues.jboss.org/secure/ReleaseNote.jspa?projectId=12310050&amp;version=12314346)]), but includes some internal changes that are really relevant for AS 6. Besides that, we verified the testsuite with 
IPv6
 addresses ;-)  
The only supported target container for 3.4.1 is the current JBoss AS 6.0.0-SNAPSHOT, which (hopefully) will soon turn into AS 6 final.  


The future: JBossWS 4 and JBoss AS 7
  

So, what are we going to work on once we&#39;re back from X-mas time? ;-)  
You might have heard on the November 17th [JBoss Community Asylum](http://asylum.libsyn.com/podcast-14-j-boss-application-server-7-is-looking-shockingly-good) that AS 7 development is really on... well, it&#39;s time for the WS team to focus on it.  
Major new features and changes are coming with JBossWS 4... take a look at [JIRA](https://issues.jboss.org/browse/JBWS?selectedTab=com.atlassian.jira.plugin.system.project%3Aroadmap-panel), the [dev forum](http://community.jboss.org/thread/159182?tstart=0) and feel free to comment/participate :-)




