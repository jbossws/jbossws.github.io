---
layout:     post
title:       "Log4j CVE and JBossWS project"
subtitle:   ""
date:       Jan 6,2022
author:     JBossWS Team
---
After [log4j cve issues](https://logging.apache.org/log4j/2.x/security.html) are reported, we carefully
reviewed the usage in JBossWS projects and found we only use log4j dependencies in command line tools, client side
and testsuite. These are places we use log4j dependency:
- [jbossws-common-tools](https://github.com/jbossws/jbossws-common-tools)

  It is used to log debug or error message when run wsconsume or wsprovide command line tool. 
  Because this tool will be finally used in command line and out of JavaEE/Jakarta EE container 
  server without touching anything about JNDI and JMS, so these CVE issues won't impact jbossws project. 
  But in some edge or some unluck case and if other project has a dependency or transitive dependency to org.jboss.ws:jbossws-common-tools or run this
  client tool in server side, we already upgraded log4j version to the latest 2.17.1.

- [testsuite](https://github.com/jbossws/jbossws-cxf/tree/main/modules/testsuite)

  This is only for print some log message from our tests. These testsuite maven module won't be included in 
  runtime or used by other projects, so we are safe. 

- [client](https://github.com/jbossws/jbossws-cxf/tree/main/modules/client) and [jaspi](https://github.com/jbossws/jbossws-cxf/tree/main/modules/jaspi) modules

  The log4j is again used to print information for client side like what jbossws-common-tools does. 

From above log4j usage in jbossws, if your project has dependency or transitive dependencies to any of these follow projects
please upgrade to jbossws-cxf-5.5.0.Final or jbossws-common-tools-1.3.3.Final. If you find any issue in this upgrade, please 
let us know. 


