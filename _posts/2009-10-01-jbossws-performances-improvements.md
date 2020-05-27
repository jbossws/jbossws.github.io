---
layout:     post
title:       "JBossWS performances: improvements coming in 3.2.1 and stacks comparison"
subtitle:   ""
date:       October 01, 2009
author:     JBossWS Team
---


I&#39;ve recently added a few classes for running some performance tests to jbossws-framework.  
Performance testing and tuning are really complex topics; generally speaking I agree with those saying that you need to establish your goals/reasons before starting dealing with optimizations, profiling, etc. My ultimate goal this time was to 
look for potential bottlenecks badly affecting performances
, both in the JBossWS-Native stack and in the whole JBossWS framework (considering we plug CXF and Metro stacks too into JBoss AS). Needless to say, I also wanted to get a better idea of 
how the supported ws stack integrations compare in terms of performances
... ;-)  

For these reasons I basically chose a black box approach and tested the whole ws stack performing concurrent invocations to a given endpoint. Tests have been performed in two different scenarios, with the client and server hosts either living on the same LAN or on the Internet, to cope with the different latency (which you really need to consider when looking for bottlenecks / bugs in this field).  

I&#39;ve actually been able to work on 
improvements
 and a couple of 
bug fixes
, which basically 
boosted all stacks performances
, especially the Native one. 
Fixes are currently on trunk and already ported to the JBoss EAP branches, the next JBossWS community version including them will be the 3.2.1.GA.
 The 3.2.1 release is currently scheduled for the end of October.  

I guess you&#39;re now looking for the numbers... Unfortunately I&#39;m not going to write any of them here, as the results are of course influenced by the testing conditions and my goals were not to stress test all the stack in multiple conditions and come to a thorough picture of the JBossWS performances.  
Anyway I do have some general considerations coming out of my tests and you can read them below. After all the tests [available to the public](http://anonsvn.jboss.org/repos/jbossws/framework/trunk/testsuite/test/java/org/jboss/test/ws/jaxws/benchmark/) and you are free to run them in your environment (and perhaps add more of them ;-) )  
As of today (fixes will be available in 3.2.1.GA):  

*   the supported stacks comes with different default logging configurations. More in details, 
Native is much more verbose
 than CXF and Metro, both client and server side. You should really want to set 
org.jboss.ws
 Log4j category (and 
org.jboss.wsf
 too) at INFO level;
*   in the medium / high latency scenario, the three stacks show comparable performances, with CXF actually giving the best results, Metro being in the middle and Native performing slightly worse than the others;

*   in the low latency scenario (LAN tests), CXF and Metro stacks run almost the same and are faster than Native stack (the gap here is bigger than in the medium/high latency scenario though).




