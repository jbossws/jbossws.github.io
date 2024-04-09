---
layout:     post
title:      "The new webserivce http client in jbossws-cxf-7.1.0.Final"
subtitle:   ""
date:       Mar 27, 2024
author:     Jim Ma
---
From jbossws-cxf-7.1.0.Final, we upgraded the CXF to 4.0.4. With this CXF upgrade, 
the new webservice client is included in jbossws-cxf-7.1.0.Final and WFLY 32.0.0.Beta1.
This new CXF webservice client is going to replace the old webservice http client which is 
using URLConnection with the new java.net.http.HttpClient. This new CXF http client mainly brings the HTTP/2 
support without other dependency, and user doesn't need to set anything else if server supports HTTP/2.
Due to some important issues like the [massive threads created](https://issues.apache.org/jira/browse/CXF-8951) and 
[performance issue](https://bugs.openjdk.org/browse/JDK-8277519) in this new http client, 
jbossws-cxf disable this new webserivce http client by default and still switch to the old http client based on HTTPURLConnection.
To enable this new webservice client, user should explicitly set the `force.urlconnection.http.conduit` to `false` 
in system property or bus property like:

```
 BusFactory.getDefaultBus().setProperty("force.urlconnection.http.conduit", false);
 BusFactory.getDefaultBus().setProperty("org.apache.cxf.transport.http.forceVersion", "2");
 HelloWorld port = getPort();
 String response = port.echo("hello");
```

Due to this isn't mature and possibly more issues will be found by the community user.
It isn't recommended to use this in production unless performance isn't a factor and HTTP/2 is required to support.

