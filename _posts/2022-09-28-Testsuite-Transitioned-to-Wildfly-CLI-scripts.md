---
layout:     post
title:       "Testsuite Transitioned to Wildfly CLI scripts"
subtitle:   ""
date:       September 28, 2022
author:     JBossWS Team
---

Starting with jbossws-cxf version 6.1.0.Final the testsuite has been
transitioned from using groovy scripts to configure the Wildfly server
to using Wildfly's CLI scripts.

Wildfly's configuration files, standalone-*.xml, are XML based.  Before
the advancements made in CLI scripting it was more effective to automate
configuration file changes using tools that provided DOM processing utilities.
Groovy was chosen for that task.  But now with a facility in
org.wildfly.plugins:wildfly-maven-plugin to execute CLI script files, it is
more efficient use CLI scripts.
 
If you have any issue or question, please let us know. Thanks!


