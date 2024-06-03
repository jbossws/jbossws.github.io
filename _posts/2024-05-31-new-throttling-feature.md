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
and demonstrate, with an example, how to use this feature to limit web service requests.

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
the faultCount reaches 5, it will throttle the request and return 429 (Server Busy) response to the client.
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
      <property-name>##throttlingManager.requestCountThreshold</property-name>
      <property-value>5</property-value>
    </property>
  </endpoint-config>
```
Endpoint metrics require that statistics be enabled for the web service subsystem. So users should execute this CLI command to 
enable statistics:

```

./subsystem=webservices:write-attribute(name=statistics-enabled,value=true)

``` 


The EndpointMetricsThrottlingManager is a basic implementation for web service throttling. Users can extend this class or write 
their own ThrottlingManager. Here is an example of limiting requests based on the request rate limit::

```
public class RateLimitThorttlingManager extends ThrottleResponse implements ThrottlingManager {
    private AtomicLong[] requestTime = new AtomicLong[60];
    private AtomicInteger[] requestCount = new AtomicInteger[60];
    private AtomicBoolean firstMessage = new AtomicBoolean(false);
    private int requestsPerMin = Integer.MAX_VALUE;
    public int getRequestsPerMin() {
        return requestsPerMin;
    }
    public void setRequestsPerMin(int requestsPerMin) {
        this.requestsPerMin = requestsPerMin;
    }
    public RateLimitThorttlingManager() {
        super();
        for (int i = 0; i < 60; i++) {
            requestTime[i] = new AtomicLong(0);
            requestCount[i] = new AtomicInteger(0);
        }
    }

    @Override
    public List<String> getDecisionPhases() {
        return Collections.singletonList(Phase.PRE_STREAM);
    }

    @Override
    public ThrottleResponse getThrottleResponse(String phase, Message m) {
        long currentTime = System.currentTimeMillis();
        int currentIndex = (int) ((currentTime / 1000) % 60);
        requestTime[currentIndex].set(currentTime);
        requestCount[currentIndex].incrementAndGet();
        if (firstMessage.compareAndSet(false, true)) {
            return null;
        } else {
            //reset the count for the previous minutes
            for (int i = 0 ; i < 60 ; i++) {
                AtomicLong item = requestTime[i];
                if (item.get() > 0 && (currentTime -item.get()) > 60000) {
                    requestTime[i].set(0);
                    requestCount[i].set(0);
                }
            }
            int sum = Stream.of(requestCount).mapToInt(AtomicInteger::get).sum();
            if (sum > requestsPerMin) {
                this.setResponseCode(429);
                return this;
            }
        }
        return null;
    }
}
```

The request rate limit can be defined in the jaxws-endpoint-config.xml. Here is an example 
setting the request rate limit to 5 per minute::

```
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
      <property-value>org.jboss.test.ws.jaxws.cxf.throttling.RateLimitThorttlingManager</property-value>
    </property>
    <property>
      <property-name>##throttlingManager.requestsPerMin</property-name>
      <property-value>5</property-value>
    </property>
  </endpoint-config>
</jaxws-config>
```
This jaxws-endpoint-config.xml should be packaged directly in the WAR file (not under META-INF or WEB-INF), 
and the module dependency org.apache.cxf.impl is required to be added to the Manifest file.
```
Manifest-Version: 1.0
Dependencies: org.apache.cxf.impl
```
For the complete example, please look at <https://github.com/jbossws/jbossws-cxf/tree/main/modules/testsuite/cxf-tests/src/test/java/org/jboss/test/ws/jaxws/cxf/throttling>






