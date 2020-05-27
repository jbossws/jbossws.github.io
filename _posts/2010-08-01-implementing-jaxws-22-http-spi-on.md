---
layout:     post
title:       "Implementing JAXWS 2.2 HTTP SPI on Apache CXF"
subtitle:   ""
date:       August 26, 2010
author:     JBossWS Team
---


JAXWS 2.2 specification introduced a compact [HTTP SPI](https://jax-ws.dev.java.net/nonav/jaxws-api/2.2/javax/xml/ws/spi/http/package-summary.html); that defines the minimal set of required information a http server container and a JAXWS stack implementation need to share for allowing an endpoint deployment and invocation.  

Previous JAXWS specification already included API for simplified endpoint deployment in a JSE environment:  
`  
Endpoint endpoint = Endpoint.create(new EndpointBean());  
endpoint.publish(&#34;http://localhost:8080/jaxws-endpoint1&#34;);  
//invoke endpoint...  
endpoint.stop();  
`  
.. as well as a 
Endpoint publish(Object obj)
 method for allowing the JAXWS RI (only) to deploy an endpoint [on top of JDK6 httpserver](http://www2.java.net/blog/2006/07/31/how-use-endpointpublishobject). However, this was not portable and different JAXWS implementation could actually support different and vendor specific http context implementations passed in that object instance in 
publish(Object obj)
. For instance, Apache CXF - despite being JAXWS 2.1 compliant - didn&#39;t have a specific implementation for the 
publish(Object obj)
 method, but just of the 
publish(String s)
 one.  

With JAXWS 2.2 the specification added a 
Endpoint publish(HttpContext ctx)
 method and the other classes/interfaces defining the HTTP SPI together with the 
HttpContext
. A JAXWS 2.2 implementation is supposed to be able to deploy an endpoint on top of 
any
 http container that also supports the JAXWS 2.2 HTTP SPI.  

Thanks to a joint work of Daniel Kulp and me, 
the current trunk for Apache CXF now supports this JAXWS 2.2 HTTP SPI
. This means you&#39;ll soon be able to perform a quick deployment and test of your endpoint using Apache CXF (or JBossWS-CXF, of course ;-)) on top of the JAXWS 2.2 compatible http server you prefer.  

As a proof of that, I&#39;ve added a [testcase](http://svn.apache.org/repos/asf/cxf/trunk/systests/container-integration/grizzly/src/test/java/org/apache/cxf/systest/grizzly/EndpointAPITest.java) leveraging Jitendra Kotamraju&#39;s [Grizzly transport bridge project](http://www2.java.net/blog/jitu/archive/2010/07/09/grizzly-transport-using-jax-ws-22-http-spi) for deploying on top of Grizzly http server, while still having the whole ws invocation being handled by CXF:  
`  
import javax.xml.ws.spi.http.HttpContext;  
import com.sun.grizzly.http.embed.GrizzlyWebServer;  
import org.jvnet.jax_ws_commons.transport.grizzly_httpspi.GrizzlyHttpContextFactory;  

..  

GrizzlyWebServer server = new GrizzlyWebServer(8080);  
HttpContext context = GrizzlyHttpContextFactory.createHttpContext(server, &#34;/ctx&#34;, &#34;/echo&#34;);  

Endpoint endpoint = Endpoint.create(new EndpointBean());  
endpoint.publish(context); // Use grizzly HTTP context for publishing  
server.start();  
//invoke endpoint  
endpoint.stop();  
server.stop();  
`  
.. but the same would have worked with other compatible containers (just to mention one, there&#39;s a [project](http://docs.codehaus.org/display/JETTY/J2se6HttpServerSPI) for making Jetty compatible with the jaxws 2.2 http spi).  


The whole JAXWS 2.2 support is being included in Apache CXF 2.3 and JBossWS-CXF 3.4.0 which should come out soon...
 in the mean time you can give the latest snapshots a try! Feedback is welcome :-)




