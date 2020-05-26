---
layout:     post
title:       "JBoss AS 7 webservices features"
subtitle:   ""
date:       July 07, 2011
author:     JBossWS Team
---



JBoss Application Server 7
 is [highly configurable](https://docs.jboss.org/author/display/AS7/General+configuration+concepts) and allows for running different 
profiles
, including one or more 
subsystems
. The optional webservices subsystem is based on 
JBossWS-CXF 4.x
.  

JBoss AS7 users can turn on full webservices capabilities by enabling the 
org.jboss.as.webservices
 module extension and the 
webservices
 subsystem in their 
standalone.xml
 / 
domain.xml
 descriptors:  

`  
&lt;server name=&#34;foo&#34; xmlns=&#34;urn:jboss:domain:1.0&#34;&gt;  
    &lt;extensions&gt;  
        [...]  
        &lt;extension module=&#34;org.jboss.as.webservices&#34;/&gt;  
        [...]  
    &lt;/extensions&gt;  
    [...]  
    &lt;profile&gt;  
        [...]  
        &lt;subsystem xmlns=&#34;urn:jboss:domain:webservices:1.0&#34; xmlns:javaee=&#34;http://java.sun.com/xml/ns/javaee&#34; xmlns:jaxwsconfig=&#34;urn:jboss:jbossws-jaxws-config:4.0&#34;&gt;  
            &lt;wsdl-host&gt;localhost&lt;/wsdl-host&gt;  
            &lt;modify-wsdl-address&gt;true&lt;/modify-wsdl-address&gt;  
        &lt;/subsystem&gt;  
        [...]  
    &lt;/profile&gt;  
    [...]  
&lt;/server&gt;  
`  
On JBoss AS 7 Final, the 
webservices
 extension / subsystem is already enabled and available in a separate domain configuration, 
domain-preview.xml
 / 
standalone-preview.xml
. Users can run a given server configuration as follows:  
`  
./bin/standalone.sh -server-config standalone-preview.xml  
`  

Once the webservices capabilities are enabled, basic 
JAXWS
 features as well as advanced 
WS-*
 functionalities are available.  
The [JBossWS 4.x documentation](https://docs.jboss.org/author/display/JBWS) covers all the details, including [full JAXWS user guide] (https://docs.jboss.org/author/display/JBWS/JAX-WS+User+Guide), [JAXWS tooling](https://docs.jboss.org/author/display/JBWS/JAX-WS+Tools) and [quick start](https://docs.jboss.org/author/display/JBWS/Quick+Start) sections.  

Moreover, examples are also provided on advanced topics:  

*   [WS-Addressing usage](https://docs.jboss.org/author/display/JBWS/WS-Addressing)
*   [WS-Security / WS-Security Policy usage](https://docs.jboss.org/author/display/JBWS/WS-Security)
*   [WS-Reliable Messaging / WS-Reliable Messagin policy usage](https://docs.jboss.org/author/display/JBWS/WS-Reliable+Messaging)
*   [SOAP-over-JMS usage](https://docs.jboss.org/author/display/JBWS/SOAP+over+JMS)




