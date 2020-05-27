---
layout:     post
title:       "com.sun.net.httpserver transport using JAXWS 2.2 HTTP SPI"
subtitle:   ""
date:       August 26, 2010
author:     JBossWS Team
---


In the [previous post](http://jbossws.blogspot.com/2010/08/implementing-jaxws-22-http-spi-on.html) I&#39;ve written about Apache CXF and JBossWS-CXF implementation of JAXWS 2.2 HTTP SPI. From a container point of view, a bit of additional coding is required for making an existing http server compliant too.  

Similarly to what Jitendra Kotamraju did for [Grizzly](http://www2.java.net/blog/jitu/archive/2010/07/09/grizzly-transport-using-jax-ws-22-http-spi), I&#39;ve just created a [small project](http://anonsvn.jboss.org/repos/jbossws/projects/jaxws-httpserver-httpspi/) for using the [JDK6 httpserver](http://download.oracle.com/javase/6/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/package-summary.html) with this HTTP SPI, basically allowing 
any
 JAXWS 2.2 stack implementation to be used on top of it (not just the JAXWS RI as mentioned [here](http://www2.java.net/blog/2006/07/31/how-use-endpointpublishobject)). The project 
only
 runtime dependency is the JAXWS 2.2 API, so it&#39;s 
completely vendor agnostic
 (the project testsuite uses Apache CXF just for the sake of testing with a JAXWS 2.2 impl).  
So, the following is now possible using any JAXWS 2.2 compliant implementation:  
`  
import com.sun.net.httpserver.HttpServer;  
import javax.xml.ws.spi.http.HttpContext;  
import org.jboss.ws.httpserver_httpspi.``HttpServerContextFactory
;
`

  
`..  

HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);  
HttpContext context = HttpServerContextFactory.createHttpContext(server, &#34;/ctx&#34;, &#34;/echo&#34;);  

Endpoint endpoint = Endpoint.create(new EndpointBean());  
endpoint.publish(context); // Use httpserver context for publishing  
server.start();  
//invoke endpoint  
endpoint.stop();  
server.stop(0);  
`  
The [binaries](https://repository.jboss.org/nexus/content/groups/public/org/jboss/ws/projects/jaxws-httpserver-httpspi/) for the project are on JBoss&#39; Maven repository.




