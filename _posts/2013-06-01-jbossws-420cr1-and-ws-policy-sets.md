---
layout:     post
title:       "JBossWS 4.2.0.CR1 and the WS-Policy sets"
subtitle:   ""
date:       June 26, 2013
author:     JBossWS Team
---


Another [WildFly release](http://lists.jboss.org/pipermail/wildfly-dev/2013-June/000382.html) has just been announced... and it comes with the first 4.2.0 candidate release of JBossWS!  

The latest JBossWS release comes with some bug fixes over the former Beta build as well as with the last  new feature we planned for addition in 4.2.0, that is additional WS-Policy [functionalities](https://issues.jboss.org/browse/JBWS-3648) for code-first development.  

I&#39;ve just written [some documentation](https://docs.jboss.org/author/display/JBWS/WS-Policy#WS-Policy-Policysets), but let me summarize the new features here: some users noticed that writing policy assertions for endpoint wsdl contracts is usually quite difficult and often represents an obstacle to quick development of ws-policy enabled endpoints prototypes.  

So we basically decided to provide means for users to choose desired policy assertions among a list of pre-defined groups serving well known needs / scenarios. The result is a custom _@PolicySets_ annotation to list groups of policy assertions; those will be automatically attached to the proper elements in the wsdl contract that is generated at deployment time and the ws stack will behave accordingly at runtime.  



The JBossWS 4.2.0.CR1 artifacts come with some initial sets, more are to be added before going final (the feature is implemented, it&#39;s just a matter of deciding which set/combination of policies to grant a label). So this is yet another example where feedback is welcome... if you have suggestions for the pre-defined set to add, just post on the [JBoss forum](https://community.jboss.org/en/jbossws?view=overview)!  

You can try JBossWS 4.2.0.CR1 either consuming it from the recent [WildFly 8.0.0.Alpha2](http://www.wildfly.org/download/) or by pulling the artifacts from the Maven repository.  

Enjoy!




