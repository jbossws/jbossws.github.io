---
layout:     post
title:       "JBossWS 4.1.0.Final has been released!"
subtitle:   ""
date:       October 16, 2012
author:     JBossWS Team
---


It&#39;s been a long ride, but today I can finally announce that **JBossWS 4.1.0.Final** is out!  

This release is the result of 6+ months of work on integrating most of the latest [Apache CXF](http://cxf.apache.org/) additions as well as providing new options and mechanisms for better stack configuration on **JBoss AS**.  

On June I [introduced](http://jbossws.blogspot.it/2012/06/looking-at-next-future-jbossws-41.html) some of 4.1 news just after having released the first Beta; below is a brief summary of what has finally been included in Final.  

####  Apache CXF 2.6

We&#39;ve moved the integration to **Apache CXF 2.6.3**, which comes with relevant improvements and new features in multiple areas, including WS-Security, WS-MetadataExchange and WS-ReliableMessaging.  

####  WS-Security work

First of all, I finally had chance to go through most of the WS-Trust recent improvements in CXF and verify the integration on JBoss AS 7. In particular, I tried some of the most common WS-Trust scenarios and verified it&#39;s possible to deploy and successfully use a Security Token Service (STS) based on either the Apache CXF STS implementation or the [PicketLink](http://www.jboss.org/picketlink) STS one. A sample application (whose sources are also included in the binary release distribution) is [covered in the documentation](https://docs.jboss.org/author/display/JBWS/WS-Trust+and+STS).  

Besides WS-Trust, I&#39;ve also enriched the JBossWS testsuite to verify most of the [WS-SecurityPolicy Examples 1.0](http://docs.oasis-open.org/ws-sx/security-policy/examples/ws-sp-usecases-examples.html) scenarios (already [implemented in Apache CXF testsuite](http://coheigea.blogspot.com/2011/12/ws-securitypolicy-examples-in-apache.html)) are also easily implemented on top of JBoss AS leveraging the JBossWS integration; once again, this shows how many (amazing) security policy functionatities Apache CXF brings into our application server.  

Finally, speaking of security, it&#39;s important to mention that this release (including transitive dependencies), comes with few security vulnerabilities fixes in multiple areas, so you should definitely upgrade.  

####  Configuration improvements

The _jboss-webservices.xml_ descriptor [has been enhanced](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-Deploymentdescriptorproperties) to allow passing in properties for controlling some of the Apache CXF internals potentially affecting runtime integration, e.g. the queue depth of the asynchronous (@OneWay) work queue, or the server side preferred policy alternative selector.  

Besides that, we&#39;ve completed the work around the [predefined client/endpoint configurations](https://docs.jboss.org/author/display/JBWS/Predefined+client+and+endpoint+configurations) mechanism that was started in 4.0.x series. This comes with a great flexibility and enables interesting user scenarios; you can e.g. define a common handler to be plugged in any handler chain of clients / endpoints of a given group (even spanning multiple deployments), or enable schema validation on messages exchanged by those clients / enpoints... all without touching the user deployments, but by simply editing the referred configuration in the JBoss domain (e.g. in standalone.xml) or in a file descriptor.  

####  Other improvements

There&#39;s of course much more in this release, including:  

*   verified IPv6 support
*   rewritten JAX-WS POJO invocation mechanism, to enable CDI injections in WS endpoints in the next future
*   WSProvide option for specifying soap:address location to use in generated wsdl
*   improved testsuite (execution time, memory footprint, code &amp; feature coverage)
*   a lot of bug fixes


Please have a look at the [release notes](http://download.jboss.org/jbossws/ReleaseNotes-jbossws-cxf-4.1.0.Final.txt) for the complete list of changes.








Finally a note on the JBossWS multiple stack integration efforts; as previously announced JBossWS-Native reached EOL and is not being released as full stack to the community anymore. Its sub-components have been slimmed down to the minimum required for still supporting JAX-RPC and it is included as support library for JBossWS-CXF into JBoss AS.








It&#39;s now time for you to [download](http://www.jboss.org/jbossws/downloads) JBossWS 4.1.0.Final, install and give it a try! Feedback is welcome as usual!



The documentation is included in the release distribution (together with the sample testsuite) and is also available at [https://docs.jboss.org/author/display/JBWS](https://docs.jboss.org/author/display/JBWS) in its latest version (might include not-released-yet features in future).



The supported target containers for this release are JBoss AS 7.1.0.Final, JBoss AS 7.1.1.Final and JBoss AS 7.1.2.Final. The current AS master is also being updated to the new WS components so that future AS releases will come with it.



Have fun :-)





