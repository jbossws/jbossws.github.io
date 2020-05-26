---
layout:     post
title:       "Looking at the next future... JBossWS 4.1"
subtitle:   ""
date:       June 11, 2012
author:     JBossWS Team
---


With the summer coming, it&#39;s now few months since the last 4.0.x release of JBossWS. While working on finalizing [JBoss Enterprise Application Platform 6](http://www.redhat.com/products/jbossenterprisemiddleware/application-platform/), the WS team here has started the **JBossWS 4.1 dev cycle**.  

Last week, the **first Beta version has been released**, covering a bunch of new features on topics we identified during the last year and which could not fit the former 4.0 cycle. Here is a list of the most noteworthy ones in _4.1.0.Beta1_:  

*   **Upgrade to [Apache CXF](http://cxf.apache.org/) 2.6.1**: this comes with many bug fixes and new features from Apache CXF side, like eg. WS-ReliableMessaging 1.1 support, lots of improvements in WS-Security area, WS-MetadataExchange (Aug 2006 spec) support, ...
*   **Apache CXF Security Token Service (STS) integration**: one of most active area in CXF development lately, which is now verified to work properly under the JBoss WS 4.1 / JBoss AS 7.1 integration
*   **Configurability improvements**: the _jboss-webservices.xml_ descriptor has been enhanced to allow passing in properties for controlling some of the Apache CXF internals potentially affecting runtime integration, e.g. the queue depth of the asynchronous (@OneWay) work queue, or the server side preferred policy alternative selector
*   **IPv6 support**: with the world finally really [moving to IPv6](http://www.worldipv6launch.org/), we&#39;ve gone through yet another IPv6 testing phase and are currently able to successfully run the whole JBossWS testsuite against JBoss AS 7.1 using IPv6
*   **JBossWS-Native cleanup**: after years of coexistence of multiple JBossWS integration stacks, the time for putting the old JBossWS-Native to EOL has come. As a consequence, we&#39;ve slimmed down its sub-components to the minimum required for still supporting JAX-RPC and be included as support library for JBossWS-CXF into JBoss AS. Long life to the JBossWS integration with Apache CXF!


This is a beta, basically because we&#39;ve further new features to add and many tests / verification still to run, so any contribution and feedback from the community is more then welcome, as usual. This time, however, we also come with two development-related only changes that are likely to ease testing:




*   _Parallel / concurrent testsuite run_: the JBossWS testsuite has been revisited and multiple tests are now executed at the same time. No need for any change on user side, a couple of minutes less for a full run and few bugs isolated thanks for the new testing conditions ;-)
*   _Reduced testsuite memory footprint_: I&#39;ve been profiling the testsuite (client side) and optimized it a bit, so that you can have concurrent runs even if you don&#39;t have tons of RAM ;-)


That&#39;s all for now. JBossWS 4.1.0.Beta1 has been installed on the current JBoss AS 7.2 master and the artifacts are available on the [Maven repository](https://repository.jboss.org/nexus/content/groups/public-jboss/org/jboss/ws/). The release notes are [here](https://issues.jboss.org/secure/ReleaseNote.jspa?projectId=12310050&amp;version=12319565).










Expect more to come during the summer!





