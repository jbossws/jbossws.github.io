---
layout:     post
title:       "WS endpoints on OpenShift"
subtitle:   ""
date:       April 12, 2012
author:     JBossWS Team
---


**OpenShift** is the Red Hat&#39;s Cloud Computing Platform as a Service (**PaaS**) offering. OpenShift is an application platform in the cloud where application developers and teams can build, test, deploy, and run their applications.
  


OpenShift supports multiple _cartridges_, one of them being the **JBoss Application Server 7.1** one. In few words, it&#39;s possible to have an AS 7.1 instance run on the cloud in few clicks.


  






_One of the point of implementing WebServices standards is in ensuring interoperability_. But for checking actual interoperability, vendors need to have public endpoints available for those willing to try them. The JBossWS project used to have them, but unfortunately they&#39;ve gone up and down multiple times for multiple reasons and have not been maintained a lot.





  






So, given I actually wanted to check some WS endpoint deployments on OpenShift, yesterday I decided to code a really skinny webapp and upload it together with some endpoints on my OpenShift account. That allows everybody to invoke the endpoints and use few test clients there to call back to their endpoints.





  






The endpoints are some of the JBossWS-CXF testsuite archives, no changes required. The web app is a very simple CDI + JSF application, delegating to very simple JAX-WS clients whose code is again taken from the JBossWS-CXF testsuite. The covered scenarios are WS-Security Policy ones at the moment, but further ones might come in the future.





  






You can give this a try at [1]



  


The webapp sources are at [2] . Those willing to simply run an equivalent openshift application on their account, can simply clone a github mirror of my application I have at [3].

Finally, if you need any direction on creating and managing OpenShift applications, these are good starting points: [4], [5].

  


The first good piece of news is that besides the need for enabling the JMX subsystem on AS7.1 on OpenShift (to workaround [6]), **I found no actual road-blocks to both deploying and invoking the endpoints** (and the ws client webapp). Besides that... some JBossWS interop endpoints are finally back ;-)

  


Happy testing ;-)


  


[1] [http://jbossws-asoldano.rhcloud.com/jbossws-cxf-wsse-interop](http://jbossws-asoldano.rhcloud.com/jbossws-cxf-wsse-interop)

[2] [http://anonsvn.jboss.org/repos/jbossws/projects/interop/cxf/wsse-webapp/](http://anonsvn.jboss.org/repos/jbossws/projects/interop/cxf/wsse-webapp/)

[3] [https://github.com/asoldano/jbossws-cxf-wsse-interop-openshift](https://github.com/asoldano/jbossws-cxf-wsse-interop-openshift)

[4] [https://openshift.redhat.com/app/getting_started](https://openshift.redhat.com/app/getting_started)

[5] [https://www.redhat.com/openshift/community/blogs/how-to-create-an-openshift-github-quick-start-project](https://www.redhat.com/openshift/community/blogs/how-to-create-an-openshift-github-quick-start-project)

[6] [https://issues.jboss.org/browse/JBWS-3456](https://issues.jboss.org/browse/JBWS-3456)





