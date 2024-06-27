---
layout:     post
title:      "Throttle the webservice request with JBossWSThrottlingFeature "
subtitle:   ""
date:       May 31, 2024
author:     Jim Ma
---
To ensure the web service application can handle a large number of requests, we need to allocate 
sufficient resources and use load balancing approaches to distribute the requests
across different server instances. Besides providing enough resources for the web service 
application, we can still do more within the application server to help manage and control 
the flow of requests to the backend web services, ensuring the stability and performance of 
a certain web service application. In this blog, we will explore the newly added JBossWSThrottlingFeature 
and demonstrate this feature to limit web service requests with some configuration examples.

### Understanding Throttling
Throttling is a technique to control the number of requests a backend service can handle within 
a specified time frame. For example, we can set a limit so that the service can only handle 
10,000 requests in 5 minutes. By limiting the rate of incoming requests, throttling helps prevent 
the server from being overwhelmed and protects it from potential crashes. This ensures that 
server resources are utilized within their designed capacity. Throttling is particularly 
important in environments where services are exposed to numerous clients and handle resource-intensive operations. 
It also acts as a protective measure against Denial of Service (DoS) attacks.
### JBossWSThrottlingFeature
JBossWSThrottlingFeature is the new feature class to enable the throttling in JBossWS-CXF/WildFly which is introduced 
in jbossws-cxf-7.2.0. This class can allow the JBossWSThrottlingFeature to be configured in the jaxws-endpoint-config.xml.
Like CXF's throttling feature, each JBossWSThrottlingFeature needs a ThrottlingManager to check if the request reaches the 
limit and should return the response immediately. The EndpointMetricsThrottlingManager is created to throttle the request 
based on JBossWS endpoint metrics. JBossWS EndpointMetrics collects the different metrics from each endpoint:

- faultCount
- requestCount
- averageProcessingTime
- maxProcessingTime
- minProcessingTime
- totalProcessingTime

User can define the limit number for each metric to limit the request to this endpoint.For example, if we define
the requests number reaches 5, it will throttle the request and return 429 (Server Busy) response to the client.
```
<jaxws-config xmlns="urn:jboss:jbossws-jaxws-config:4.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:javaee="http://java.sun.com/xml/ns/javaee"
  xsi:schemaLocation="urn:jboss:jbossws-jaxws-config:4.0 schema/jbossws-jaxws-config_4_0.xsd">

  <endpoint-config>
    <config-name>org.jboss.test.ws.jaxws.cxf.throttling.HelloWorldImpl</config-name>
    <property>
      <property-name>cxf.features</property-name>
      <property-value>##throttlingFeature</property-value>
    </property>
    <property>
      <property-name>##throttlingFeature</property-name>
      <property-value>org.jboss.wsf.stack.cxf.features.throttling.JBossWSThrottlingFeature</property-value>
    </property>
    <property>
      <property-name>##throttlingFeature.throttlingManager</property-name>
      <property-value>##throttlingManager</property-value>
    </property>
    <property>
      <property-name>##throttlingManager</property-name>
      <property-value>org.jboss.wsf.stack.cxf.features.throttling.EndpointMetricsThrottlingManager</property-value>
    </property>
    <property>
      <property-name>##throttlingManager.requestPermit</property-name>
      <property-value>5</property-value>
    </property>
  </endpoint-config>
```
There is another RateLimitThorttlingManager out-of-the-box to allow user to easily control the request traffic
by defining the `permitsPerMin`. This simply define the number of requests the server can handle in one minute.
Exceed this number of requests, the server will respond 429 immediately.Below is the configuration example which is
limiting the requests is 5 in one minute:

```
<?xml version="1.0" encoding="UTF-8"?>

<jaxws-config xmlns="urn:jboss:jbossws-jaxws-config:4.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:javaee="http://java.sun.com/xml/ns/javaee"
  xsi:schemaLocation="urn:jboss:jbossws-jaxws-config:4.0 schema/jbossws-jaxws-config_4_0.xsd">
  <endpoint-config>
    <config-name>org.jboss.test.ws.jaxws.cxf.throttling.HelloImpl</config-name>
    <property>
      <property-name>cxf.features</property-name>
      <property-value>##throttlingFeature</property-value>
    </property>
    <property>
      <property-name>##throttlingFeature</property-name>
      <property-value>org.jboss.wsf.stack.cxf.features.throttling.JBossWSThrottlingFeature</property-value>
    </property>
    <property>
      <property-name>##throttlingFeature.throttlingManager</property-name>
      <property-value>##throttlingManager</property-value>
    </property>
    <property>
      <property-name>##throttlingManager</property-name>
      <property-value>org.jboss.wsf.stack.cxf.features.throttling.RateLimitThorttlingManager</property-value>
    </property>
    <property>
      <property-name>##throttlingManager.permitsPerMin</property-name>
      <property-value>5</property-value>
    </property>
  </endpoint-config>
</jaxws-config>
```
The other two options `peroid` and `permitsPerPeroid` in RateLimitThorttlingManager can be used to specify any peroid time and number of requests is allowed in this peroid 
time instead of defining the requests limit in one minute which is commonly used. For example, this jaxws-endpoint-config.xml can be used to limit the 5 requests in 30 seconds:
```
<?xml version="1.0" encoding="UTF-8"?>
<jaxws-config xmlns="urn:jboss:jbossws-jaxws-config:4.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:javaee="http://java.sun.com/xml/ns/javaee"
  xsi:schemaLocation="urn:jboss:jbossws-jaxws-config:4.0 schema/jbossws-jaxws-config_4_0.xsd">
  <endpoint-config>
    <config-name>org.jboss.test.ws.jaxws.cxf.throttling.HelloImpl</config-name>
    <property>
      <property-name>cxf.features</property-name>
      <property-value>##throttlingFeature</property-value>
    </property>
    <property>
      <property-name>##throttlingFeature</property-name>
      <property-value>org.jboss.wsf.stack.cxf.features.throttling.JBossWSThrottlingFeature</property-value>
    </property>
    <property>
      <property-name>##throttlingFeature.throttlingManager</property-name>
      <property-value>##throttlingManager</property-value>
    </property>
    <property>
      <property-name>##throttlingManager</property-name>
      <property-value>org.jboss.wsf.stack.cxf.features.throttling.RateLimitThorttlingManager</property-value>
    </property>
    <property>
      <property-name>##throttlingManager.period</property-name>
      <property-value>30</property-value>
    </property>
    <property>
      <property-name>##throttlingManager.permitsPerPeriod</property-name>
      <property-value>5</property-value>
    </property>
  </endpoint-config>
</jaxws-config>
```
Please note ,both these two ThrottlingManagers require the webservice subsystem' statistics is enabled
to get the correct value from Endpoint metrics. To enable the webservice subsystem's statistics, simply 
run the jboss-cli command:
```
./subsystem=webservices:write-attribute(name=statistics-enabled,value=true)
```

The key element of the throttling feature configuration is the jaxws-endpoint-config.xml, and this configuration file should be packaged directly in the WAR file, and ensure it's not under META-INF or WEB-INF.

### Summary
The JBossWS throttling feature can be an easy approach to protect important web services from being 
overwhelmed or crashed. This feature can also be used to serve different user categories, 
such as paid and unpaid users. If you need this feature in your web service application, 
please give it a try. If you find that the out-of-the-box ThrottlingManager 
doesn't meet your requirements, you can extend the throttling api and create 
your own ThrottlingManager. If your created ThrottlingManager could be useful 
for others and want to contribute, don't forget to let us know.






