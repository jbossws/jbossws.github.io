---
layout:     post
title:       "JBossWS 3.4.0.CR1 is out!"
subtitle:   ""
date:       October 13, 2010
author:     JBossWS Team
---


I&#39;m happy to announce that the first candidate release of 
JBossWS 3.4.0
 has just been released, including 
JBossWS-CXF
 ([release notes](https://jira.jboss.org/secure/ReleaseNote.jspa?projectId=12310050&amp;version=12314598)) and 
JBossWS-Native
 ([release notes](https://jira.jboss.org/secure/ReleaseNote.jspa?projectId=12310050&amp;version=12314600)) stacks.  


This is a major step forward in the direction of 

JavaEE 6

 compliance.
 While the current release supports 
JAXWS 2.2
 (JSR 224) and most of the additions in JSR 109 v.1.3, we&#39;re targetting passing all the webservice related modules of JavaEE 6 CTS TCK with the final 3.4.0 release.  

JBossWS-CXF 3.4.0.CR1 includes the recently released 
Apache CXF 2.3.0
, which in turn comes with multiple [major new features](http://cxf.apache.org/apache-cxf-230-release-notes.html) (as well as bug fixes).  

Finally, the 
Maven plugin
 for the 
JBossWS JAXWS tools
 (
wsconsume / wsprovide
) has also been released in a new [1.0.1.GA version](https://jira.jboss.org/secure/ReleaseNote.jspa?projectId=12310050&amp;version=12315112), which supports the SPI of the latest available 3.4.0.CR1 stacks and adds a 
fork mode option for effectively using the tools with JAXWS 2.2
.  

The Maven artifacts for all the released components are available on the JBoss public repository; please give them a try!  

*   [JBossWS-CXF](https://repository.jboss.org/nexus/content/groups/public/org/jboss/ws/cxf/)
*   [JBossWS-Native](https://repository.jboss.org/nexus/content/groups/public/org/jboss/ws/native/)
*   [maven-jaxws-tools-plugin](https://repository.jboss.org/nexus/content/groups/public/org/jboss/ws/plugins/maven-jaxws-tools-plugin/)




