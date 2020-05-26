---
layout:     post
title:       "WildFly 9 and JBossWS 5, the future is coming..."
subtitle:   ""
date:       September 05, 2014
author:     JBossWS Team
---


After few months of silence, here we are again with some webservices updates. We&#39;ve been working on next major release of JBossWS the whole spring and summer and few days ago the first Beta of JBossWS 5 has been released to the [Maven repository](http://central.maven.org/maven2/org/jboss/ws/cxf/).  

So, what&#39;s new and great with JBossWS 5 ? The release notes for the first beta can be [found on Jira](https://issues.jboss.org/secure/ReleaseNote.jspa?projectId=12310050&amp;version=12323761) as usual, however let&#39;s go through the main changes and improvements.  

####  Apache CXF 3

The most important component upgrade with JBossWS 5 is the move from [Apache CXF](http://cxf.apache.org/) 2.7.x series to the 3.0.x series, which also pulls updates to Apache WSS4J 2.0.x and Apache Santuario 2.0.x. Apache CXF 3 brings a bunch of new features. The most notable one is probably the addition of WS-Security streaming support: a new [StAX based implementation of WS-Security](http://ws.apache.org/wss4j/streaming.html) is available as an alternative to the existing DOM based one; the streaming implementation ensures better performances when dealing with large SOAP messages, as less memory is consumed to parse the message headers and the stack can completely avoid the message conversion to DOM if there&#39;s no other reason for doing that.  

Among the other improvements in Apache CXF 3, a more complete support of WS-RM 1.1 is provided and a new asynchronous http transport is available.  

####  WSDL soap:address rewrite improvements

JBossWS features a mechanism for rewriting the soap:address element in the WSDL that&#39;s published for both code-first and contract-first endpoint deployments. This is commonly used to control the endpoint address that&#39;s advertised when the actual application server instances are running behind load balancer or proxy hosts. Multiple [options](https://docs.jboss.org/author/display/WFLY9/Web+services+configuration) are available to define how the address computed by the container has to be tweaked to be a valid address for the user actually invoking the endpoint from outside of the server network. With JBossWS 5, few additional options have been added, like [endpoint path rewrite rules](https://issues.jboss.org/browse/JBWS-3750) (supporting SED scripts) and an attribute to [force the address scheme](https://issues.jboss.org/browse/JBWS-3785) (http or https) regardless of the transport security setup of the server.  

Speaking of published WSDL manipulation, [system property expansion](https://issues.jboss.org/browse/JBWS-3628) is now also supported.  

####  Default CXF bus selection strategy change

The concept of Apache CXF bus [selection strategy](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-BusselectionstrategiesforJAXWSclients) has been introduced in [one of the previous posts](http://jbossws.blogspot.com/2013/10/controlling-apache-cxf-bus-creation-for.html). With JBossWS 5, the default strategy for JAX-WS clients running in-container (that is in WildFly, perhaps invoked by a EE component like a servlet, EJB3 bean or CDI bean) is now [TCCL_BUS](https://docs.jboss.org/author/display/JBWS/Apache+CXF+integration#ApacheCXFintegration-Threadcontextclassloaderbusstrategy%28TCCLBUS%29). The change is going to ensure better performances, reducing the number of Bus creations and the amount of memory used for them. The reason for such a change stands in the container basically defining a different classloader for each deployment, which is usually the proper level of isolation for JAX-WS client busses. The user can still configure the server to use the previous default behavior.  

The new JBossWS 5.0.0.Beta1 components, including the new Apache component dependencies, have been pushed to the current WildFly master. That basically mean that the great upcoming [WildFly](http://wildfly.org/) 9 Beta release is going to ship with the latest webservices stack too. So either wait few days for the new container to be released or deploy the new stack from the [tagged jbossws-cxf sources](http://anonsvn.jboss.org/repos/jbossws/stack/cxf/tags/jbossws-cxf-5.0.0.Beta1/) and get back to us with any feedback :-) Some notes on migration from previous versions of JBossWS are also being added [here](https://developer.jboss.org/wiki/JBossWS5MigrationNotes).  

We do plan to reach 5.0.0.Final by the time WildFly 9 will reach Final too.




