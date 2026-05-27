---
layout:     post
title:      "JBossWS 7.3.8.Final is released!"
subtitle:   ""
date:       May 06, 2026
author:     Fabio Burzigotti
---

In this bugfix release we added a functionality that lets JBossWS CXF accept encoded URL paths, e.g. those 
including NLS (National Language Symbols) characters in Web Services names.

Please try out this release, and let us know if you have any issues or questions.

### Release notes - JBoss Web Services - jbossws-cxf-7.3.8.Final

#### Bug

- [JBWS-4467](https://redhat.atlassian.net/browse/JBWS-4467) - Tests are searched for in modules/testsuite after 
 maven-surefire-plugin upgrade

- [JBWS-4488](https://redhat.atlassian.net/browse/JBWS-4488) - jbossws-cxf - "JBWS024029: Cannot obtain destination 
 for ..." is thrown when trying to consume a web service having NLS characters in URL path
  
  - JBossWS-CXF can automatically handle URL-encoded paths containing National Language Symbols \(NLS\) or other non-ASCII characters.   
    The stack can automatically try to decode incoming request paths to match the registered endpoints.
    
    This behavior is disabled by default and can be controlled globally via the following system properties:
    
    - `org.jboss.ws.cxf.decodeUrlPath` - controls the default behavior across all deployments. The default value is `false`.
    - `org.jboss.ws.cxf.urlCharset` - controls the charset that will be used to decode the URL path. The default value is `UTF-8`.
    
    Such properties can be overridden per-deployment, that is, via the `jboss-webservices.xml`  
    descriptor, which take precedence over the system properties.  
    
    The `org.jboss.ws.cxf.urlCharset` system property should be aligned with Undertow’s `URL\_CHARSET` configuration option.

#### Task

- [JBWS-4465](https://redhat.atlassian.net/browse/JBWS-4465) - JBossWS CXF "feature-pack" module does not build against WildFly 39.0.0.Beta1

- [JBWS-4471](https://redhat.atlassian.net/browse/JBWS-4471) - jbossws-cxf - Update to jbossws-parent 2.1.1.Final
