---
layout:     post
title:       "Testsuite Transitioned to WildFly CLI scripts"
subtitle:   ""
date:       September 28, 2022
author:     JBossWS Team
---

Starting with jbossws-cxf version 6.1.0.Final the testsuite has been
transitioned from using [Groovy](https://groovy-lang.org/) scripts to configure the WildFly server
to using [WildFly's CLI](https://docs.wildfly.org/26/Admin_Guide.html#Command_Line_Interface) scripts.

WildFly's configuration files, standalone-*.xml, are XML based.  Before
the advancements made in CLI scripting, it was more effective to automate
configuration file changes using tools that provided DOM processing utilities.
Groovy was chosen for that task.  But now with a facility in
_org.wildfly.plugins:wildfly-maven-plugin_ to execute CLI script files, it is
more efficient use CLI scripts.
 
If you have any issue or question, please let us know. Thanks!


