---
layout:     post
title:       "Log4j CVE and JBossWS project"
subtitle:   ""
date:       Jan 6,2022
author:     JBossWS Team
---
After [log4j cve issues](https://logging.apache.org/log4j/2.x/security.html) were reported, we carefully
reviewed the usage in JBossWS projects and found we only use log4j dependencies in command line tools, client side
and testsuite. These are places we use log4j dependency:

- [jbossws-common-tools](https://github.com/jbossws/jbossws-common-tools/blob/jbossws-common-tools-1.3.2.Final)

  It is used to log debug and error messages when running the wsconsume or wsprovide command line tools.
  Because these tools only run on the command line and do not run in the JavaEE/Jakarta EE container
  server, this CVE issue doesn't impact the jbossws projects, however
  we reviewed the log4j usage in the command line tool and decided to remove this log4j 1.2.14 dependency in 
  jbossws-common-tools 1.4.0.Final to 
  directly log message to console or use jdk logger. 

- [testsuite](https://github.com/jbossws/jbossws-cxf/tree/main/modules/testsuite)

  This is only for print some log message from our tests. These testsuite maven module won't be included in 
  runtime or used by other projects, so we are safe. 

- [client](https://github.com/jbossws/jbossws-cxf/tree/main/modules/client) and [jaspi](https://github.com/jbossws/jbossws-cxf/tree/main/modules/jaspi) modules

  The log4j is again used to print information for client side like what jbossws-common-tools does, and this CVE issue doesn't impact 
  the client stuff either. To make sure other project including either of this two dependencies doesn't include the 
  old version log4j we upgraded log4j version to 2.17.1 and released jbossws-cxf-5.5.0.Final. In the near future, we'll
  evaluate if it's possible to remove log4j dependency to directly use jboss logger.

From above log4j usage in jbossws, if your project has dependency or transitive dependencies to any of these follow projects
please upgrade to jbossws-cxf-5.5.0.Final or jbossws-common-tools-1.4.0.Final. If you find any issue in this upgrade, please 
let us know. 


