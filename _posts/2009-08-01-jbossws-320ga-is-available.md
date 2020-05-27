---
layout:     post
title:       "JBossWS 3.2.0.GA is available"
subtitle:   ""
date:       August 28, 2009
author:     JBossWS Team
---


As you can see at the [renewed JBossWS home page](http://www.jboss.org/jbossws), JBossWS 3.2.0.GA has just been released. This release includes the Native stack as well as the Apache CXF based stack and the Glassfish Metro based one. The supported target containers are JBoss AS 5.0.0.GA, JBoss AS 5.0.1.GA and JBoss AS 5.1.0.GA.  

It&#39;s been a hot summer here and I&#39;m not referring to the weather only... the work for 
JBoss EAP 5
 (which of course includes webservice features provided by JBossWS) is proceeding full steam ahead, but at the same time Richard and me are taking care of the community webservice project as always.  

While featuring some major refactoring on the integration layer with the application server -which should make our future development easier-, JBossWS 3.2.0.GA provides many interesting new features.  
On the 
Native stack
 side, the HTTP transport layer -which has historically been based on JBoss Remoting- has been rewritten to leverage [Netty](http://jboss.org/netty/). This has given us chances for performing some client side improvements, like an easier configuration for the chunk size (in chunked-encoding mode) and finally making one-way invocations non-blocking (whenever that&#39;s allowed by the specs). Further improvements should probably come soon thanks to this move.  

The 
Metro stack
 does not come with a lot of news, but the release was probably awaited as there&#39;s been no 3.1.2 release for JBossWS-Metro.  

Much more to say on the 
CXF stack
 side instead ;-) First of all, I&#39;d like to thank the Apache CXF developers for allowing me to [get aboard the project](http://www.nabble.com/-VOTE--Alessio-Soldano-for-committer-td23930622.html#a24035810), I&#39;m going to do my best for the sake of both Apache CXF and the integration in JBossWS.  
For now, my committership has allowed for fast integration of fixes to the CXF codebase, resulting in a really good synchronization of CXF and JBossWS-CXF releases.  
As a matter of fact, some of the interesting features coming in 3.2.0.GA are about the 
soap:address rewrite
.  After my contribution of a couple of missing features to the Apache CXF codebase
 [*]
, JBossWS has now the same behaviour when using the Native stack and the CXF stack.  
The endpoint address in the wsdl as well as the URL shown in the JBossWS deployed services console can be modified according to:  

*   a server configuration allowing for rewrite of host and port of both valid and invalid user provided URLs;
or  

*   the host/port used by the client when actually hitting an endpoint deployed on an application service bound to multiple network interfaces (or simply in the [multiple virtual host](https://jira.jboss.org/jira/browse/JBWS-2227) case)[Detailed documentation](http://www.jboss.org/community/wiki/JBossWS-UserGuide#Address_rewrite) is available in the [renewed JBossWS area](http://www.jboss.org/community/wiki/JBossWS) of the JBoss community wiki.  
We have plans for further contribution to Apache CXF in the future, stay tuned.  

One of the aims of the JBoss Web Service Framework is to add features on top of what the supported stacks already provides. Thanks to a [contribution](https://jira.jboss.org/jira/browse/JBWS-2106) from Andrew Dinn, a colleague involved in the JBoss Transactions project, JBossWS 3.2.0.GA includes a common API for using the JAX-WSA (JSR-261) WS-Addressing functionalities in a stack independent way. The (different) implementations currently supported under the hood are those of Native and CXF stacks.  

Further information available in the release notes. That&#39;s enough for now, [download JBossWS-3.2.0.GA](http://jboss.org/jbossws/downloads/) and give it a try! :-)  


[*] CXF-1996, CXF-2364





