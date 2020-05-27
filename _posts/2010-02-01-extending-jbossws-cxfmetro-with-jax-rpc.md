---
layout:     post
title:       "Extending JBossWS-CXF/Metro with JAX-RPC"
subtitle:   ""
date:       February 23, 2010
author:     JBossWS Team
---


As you all know, Apache CXF and Glassfish Metro do not include JAX-RPC functionalities. While this is not a major issue considering users should be moving to JAX-WS for many reasons, Java EE 6 still include JAX-RPC and JBoss AS needs to provide implementation for it in order to achieve certification.  

While JBossWS-Native stack includes JAX-RPC features, in the direction of further improving the JBossWS integration layer for running different WS stacks on top of our AS, we need to provide a way of supporting jaxrpc deployments when JBossWS-CXF (or JBossWS-Metro) is installed in the AS.  

We&#39;ve thought about possible ways of achieving this and the most viable one appeared to be leveraging the JBossWS-Native stack for providing JAXRPC functionalities, while leaving JAX-WS processing to the other installed stack. Implementing this idea basically implies being able to install multiple webservice stacks at the same time in the AS. JBossWS-Native and JBossWS-CXF/Metro need to coexist at runtime and deal with requests depending on the kind of endpoint those are meant for.  

In order to deploy multiple stacks on JBoss AS, we needed to:  

1.  fix the deployment process: JAX-RPC deployments need to go through JBossWS-Native deployers, while JAXWS ones need to be dealt with by JBossWS-CXF deployers. There&#39;s also some kind of intersection between the two groups, as JBossWS comes with some common features that need to stay centralized (the endpoint registry, for instance);
2.  ensure libs from the two deployed stacks can live together: this is quite important speaking of WS as a *lot* of things depends on Service API loading (which depends on what libraries define the current classpath).  

**Fixing the deployment**  

The deployment processing issue has been solved basically leveraging the current architecture of ws deployers in JBoss AS. JBossWS comes with a 
parse stage
 deployer (that deals with optional 
webservice.xml
 descriptor parsing) and a quite fine-grained group of 
real stage
 deployers (that performs all the analysis, setup, etc. required for the user endpoint to be properly used on the AS). The former and some of the latters are 
stack agnostic
, meaning they don&#39;t have or do anything specific to the ws stack (Native, CXF, Metro) currently installed. Some of the real stage ws deployers, instead, are 
stack specific
 and change from stack to stack. The images below present schematically what JAX-RCP / JAX-WS deployments go through during the JBossWS deployment (of course the full deployment involves and requires other non-ws deployers to enter the game):  

[![image](//2.bp.blogspot.com/__h8KLHmj824/S4RPtk9BgzI/AAAAAAAAAXE/6cU3l_lpv-w/s400/jbossws-native-deployers.png)](//2.bp.blogspot.com/__h8KLHmj824/S4RPtk9BgzI/AAAAAAAAAXE/6cU3l_lpv-w/s1600-h/jbossws-native-deployers.png)  

[![image](//4.bp.blogspot.com/__h8KLHmj824/S4RQLGrl_HI/AAAAAAAAAXM/ZqLVb26HcRQ/s400/jbossws-cxf-deployers.png)](//4.bp.blogspot.com/__h8KLHmj824/S4RQLGrl_HI/AAAAAAAAAXM/ZqLVb26HcRQ/s1600-h/jbossws-cxf-deployers.png)  

[![image](//2.bp.blogspot.com/__h8KLHmj824/S4RQbyjofMI/AAAAAAAAAXk/MC3TKdZJI1Y/s400/jbossws-metro-deployers.png)](//2.bp.blogspot.com/__h8KLHmj824/S4RQbyjofMI/AAAAAAAAAXk/MC3TKdZJI1Y/s1600-h/jbossws-metro-deployers.png)  

Adding a couple of flags (using attributes in the 
*-jboss-beans.xml
 descriptors) to stacks&#39; deployers for marking them as being able to deal with jaxrpc/jaxws deployments, we&#39;ve been able to make them run just for the deployments they&#39;re meant for. Thanks to the AS deployers&#39; inputs/outputs and the current JBossWS deployers&#39; modularity, there&#39;s been no need to provide implementation of new deployers, a proper combination (with the right ordering) of the already existing ones is fine. We end up with new 
jboss-beans.xml
 descriptor defining new deployer chains to be installed depending on the selected stack; jaxrpc and jaxws deployments are identified and go through the right deployers that are able to work on them.  

**Making stack libraries coexist**  

As previously mentioned, the blocking issue regarding allowing two JBossWS stack to live together stands in the factories/services specified in 
META-INF/services/...
 (Service API loading) into stacks&#39; jars. We coped with this moving all the &#34;offending&#34; service configurations (i.e. those that have different values in different stacks) to 
descriptor only jars
.  

Those jars (actually only those required for jaxrpc processing) are then isolated in a specific location in application server using a separate classloader declaration (
jboss-classloading.xml
) and explicitly added to the classpath just for jaxrpc deployments using an additional integration deployer during the 
describe stage
.  

Basically we end up with two slightly different classpaths configured at describe stage for different deployment types. This way, when the classloader is created for the current deployment in 
classload stage
, the proper stack is implicitly selected for dealing with it. Below you see the obtained schema:  

[![image](//2.bp.blogspot.com/__h8KLHmj824/S4RQVCxvIKI/AAAAAAAAAXc/7GjpNE5eWTw/s400/jbossws-cxf-jaxrpc-deployers.png)](//2.bp.blogspot.com/__h8KLHmj824/S4RQVCxvIKI/AAAAAAAAAXc/7GjpNE5eWTw/s1600-h/jbossws-cxf-jaxrpc-deployers.png)  

[![image](//2.bp.blogspot.com/__h8KLHmj824/S4RQQ9jA5RI/AAAAAAAAAXU/vUijdnmh89A/s400/jbossws-metro-jaxrpc-deployers.png)](//2.bp.blogspot.com/__h8KLHmj824/S4RQQ9jA5RI/AAAAAAAAAXU/vUijdnmh89A/s1600-h/jbossws-metro-jaxrpc-deployers.png)  

**Now... try it out!**  

The solution introduced above has just been included in [JBossWS-CXF 3.3.0.Beta1](http://repository.jboss.org/maven2/org/jboss/ws/cxf/jbossws-cxf/3.3.0.Beta1/) and J[BossWS-Metro 3.3.0.Beta1](http://repository.jboss.org/maven2/org/jboss/ws/metro/jbossws-metro/3.3.0.Beta1/) and is targeted for JBoss Application Server 6.  
You just need to checkout the current [JBoss AS trunk](http://anonsvn.jboss.org/repos/jbossas/trunk/) (or wait for JBoss AS 6.0.0.M3) and the tag for [JBossWS-CXF](http://anonsvn.jboss.org/repos/jbossws/stack/cxf/tags/jbossws-cxf-3.3.0.Beta1/)/[Metro](http://anonsvn.jboss.org/repos/jbossws/stack/metro/tags/jbossws-metro-3.3.0.Beta1/) 3.3.0.Beta1. Build both AS and [WS stack](http://community.jboss.org/wiki/JBossWS-BuildingFromSource#Checkout_and_build_JBossWSCXF) (it&#39;s just a matter of few commands) and you&#39;re ready to go. You can play deploying jaxrpc and jaxws archives on the AS, with formers being handled by JBossWS-Native and the latters by JBossWS-CXF/Metro.  

Feedback is welcome :-)





