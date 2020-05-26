---
layout:     post
title:       "How to kick start a WS project in few seconds"
subtitle:   ""
date:       September 16, 2014
author:     JBossWS Team
---


One of the goals I committed to when I joined the [JBoss Web Services](http://jbossws.jboss.org/) project back in 2007 was to improve the JBossWS tooling offer and possibly make the users&#39; life easier when they have to deal with WS technologies.  

Some of the solutions we developed in this area during the past years are now surpassed, others are still here and being used (as an example, think about the [Wise](http://wise.jboss.org/) project, which gets very limited attention from us due to lack of time, but currently powers the [Web Service Tester](http://docs.jboss.org/tools/4.1.0.Final/en/ws_soap_reference/html/web_service_test_view.html) within [JBoss Tools](http://tools.jboss.org/), just to say).  

In the last years Maven has been adopted as the _de-facto_ standard solution for building projects; as a consequence, the JBossWS team made available a couple of [Maven plugins](http://jbossws.blogspot.it/2013/02/maven-plugin-for-jaxws-tools-again.html) for running its [JAX-WS tools](https://docs.jboss.org/author/display/JBWS/JAX-WS+Tools) ([wsconsume](https://docs.jboss.org/author/display/JBWS/wsconsume) and [wsprovide](https://docs.jboss.org/author/display/JBWS/wsprovide)). As part of a customer project build it is hence possible to automatically generate a JAX-WS client or Service Endpoint Interface from a given WSDL contract and viceversa.  

In February last year, with the aim of further clarifying how the plugins could be leveraged in final user projects, I wrote a [post on the forum] (https://developer.jboss.org/wiki/JAXWSToolsMavenPluginSample)describing an example of the above mentioned Maven plugins.  

The next step was to simplify the sample project creation and that&#39;s what has been eventually addressed. **The JBossWS project now features a custom [Maven Archetype](http://maven.apache.org/guides/introduction/introduction-to-archetypes.html), basically a WS-enabled project template toolkit.**  

Whenever a user needs to start a new project aiming at providing and/or consuming a JAX-WS endpoint, the [new _jaxws-codefirst_ archetype](https://repository.jboss.org/nexus/content/groups/public/org/jboss/ws/plugins/archetypes/jaxws-codefirst/) allows creating a starting project (including working build and sample helloworld client and endpoint) in few seconds. It&#39;s simply a matter of issuing a command and replying to simple questions on the desired artifact and group ids for the project being generated:  

_mvn archetype:generate -Dfilter=org.jboss.ws.plugins.archetypes:_  

The generated project includes:  

*   a sample HelloWorld code-first POJO endpoint
*   an integration test that gets the WSDL contract for the above service, builds up a client and invokes the endpoint
*   a pom.xml for creating a war archive; the project has proper WS component dependencies and uses both wsprovide and wsconsume maven plugins for generating the contract for the code-first endpoint and then generating the client stubs for such contract
*   a plugin for deploying the archive on WildFly.
The project is built and tested by simply running:  

_mvn wildfly:deploy_  

_mvn integration-test_  

The build processes the various plugins and calls into the JBossWS tools to generate all the required classes for building the deployment archive and client. The user can test the sample, have a look at the project structure and then either trash the sample endpoint and testcase and replace them with his own components, or modify them step-by-step to achieve what he needs. **No need to start from scratch anymore :-)**  

A 1.0.0.Beta1 version of the archetype is currently available; it relies on JBossWS 4.2.3.Final and is out there for early testers. I&#39;ve been fixing few bugs on it recently and the plan is to release a new version, based on JBossWS 5, together with the next release of the webservices stack. So give it a try and send us your feedback; we&#39;ll try to fix any issue before the release.  





