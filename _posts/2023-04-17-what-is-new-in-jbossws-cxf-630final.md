---
layout:     post
title:       "What's New in the Upcoming JBossWS 6.3.0 Release"
subtitle:   ""
date:       Apr 17, 2023
author:     Jim Ma
---
After JBossWS 6.2.0 released, we are thinking about what we should do to improve JBossWS 
project and add new things in the upcoming JBossWS 6.3.0 release. In this blog post,
we will discuss this topic and give an update what will be included in the new release. 

1. JBossWS Galleon Feature Pack
   [WildFly Galleon](https://docs.wildfly.org/galleon) allows users to create custom distributions of 
   the WildFly application server. It uses feature pack to specify a set of components to include in 
   the custom Wildfly server. 
   In JBossWS 6.3.0, the new jbossws-cxf-feature-pack will be provided. User can install this feature
   pack in any of released WildFly 27 and WildFly 28 or 28-SNAPSHOT version. This feature pack will be
   included in JBossWS dist module and created the custom WildFly server to run whole test suite.

2. Cloud Support
   Containerized java application can be the first step to easily deploy to the cloud and scale up 
   or down based on demand. In the next JBossWS release, we'll improve JBossWS cloud support and create 
   examples/docs about how to run a webservice endpoint in docker container. 
   The Openshift platform provides numerous benefits for running applications like scalability,high 
   availability,resource efficiency, rollout and rollback. To allow user to easily deploy webservice 
   application to the Openshift, we'll provide better support and guide to run webservice on OpenShift.

3. Clean build
   In the previous JBossWS releases, we have to use many arguments and maven profiles to run all jbossws
   tests and build all artifacts. This will be cleaned and improved in the upcoming 6.3.0 release. We'll 
   deprecate the old maven profiles and make JBossWS can be build with the simple maven build command line
   `mvn clean install`. 

4. More Jakarta EE support
   From JBossWS 6.x, we already changed the javax package name to jakarta and imported the new jakarta 
   dependencies. For the third party dependencies, the bug fix release is also coming, especially CXF 4.0.1
   is one of our interested dependency upgrades. In the next release, we'll update as many jakarta dependency
   as we can.

This is a preliminary version for our project roadmap. It is subject to change as we continue to look at what else 
we can bring in the next release. We welcome your comments and would appreciate any feedback you have. Please leave us 
a message on [github discussion](https://github.com/jbossws/jbossws-cxf/discussions).


   
   



