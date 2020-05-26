---
layout:     post
title:       "Maven plugin for JAXWS tools, again!"
subtitle:   ""
date:       February 14, 2013
author:     JBossWS Team
---


Back in March 2010 I [wrote](http://jbossws.blogspot.it/2010/03/maven-plugin-for-jaxws-tools.html) about our **JBossWS Maven plugin** for running [**JAXWS tools**](https://docs.jboss.org/author/display/JBWS/JAX-WS+Tools) ([wsconsume](https://docs.jboss.org/author/display/JBWS/wsconsume) and [wsprovide](https://docs.jboss.org/author/display/JBWS/wsprovide)). The plugin allows embedding tools&#39; invocation into user Maven projects, so that given a wsdl contract you can e.g. automatically generate stub classes and interfaces for a ws endpoint you want to implement or for an endpoint you want to build a client for.  

Yesterday I cut a micro **1.1.1.Final release** of the plugin (
org.jboss.ws.plugins:

maven-jaxws-tools-plugin
), which is [available](https://repository.jboss.org/nexus/content/groups/public-jboss/org/jboss/ws/plugins/maven-jaxws-tools-plugin/1.1.1.Final/) on the usual _JBoss Maven repository_. You&#39;re welcome to go and try it.  

On that purpose, I&#39;ve written a small **sample projec**t showing how a user project can actually use the _wsconsume_ plugin. It&#39;s [available on the wiki](https://community.jboss.org/wiki/JAXWSToolsMavenPluginSample), have a look and see how easy it&#39;s to use it! :-)




