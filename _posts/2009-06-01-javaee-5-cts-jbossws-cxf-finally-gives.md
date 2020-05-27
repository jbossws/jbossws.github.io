---
layout:     post
title:       "JavaEE 5 CTS: JBossWS-CXF finally gives great results!"
subtitle:   ""
date:       June 01, 2009
author:     JBossWS Team
---


As you already know, the JBossWS stack integration layer work started back in 2007. We&#39;ve worked on enabling features and functionalities both using the JBossWS native stack and the integrated stacks (Apache CXF and Sun Metro).  
We&#39;ve been using and continuously enriching a 
common testsuite
 for proving/showing the working functionalities with all stacks.  

JBoss AS 5 is JavaEE5 certified with JBossWS-Native stack; on the contrary we&#39;ve been quite far from passing the official certification testsuite with the third-party integrated stacks (we actually did not care about that for quite some time). At least till the beginning of 2009...  

When the collaboration with Apache CXF became more intense, we&#39;ve been able to successfully identify existing issues and push their fixes to releases. Apache CXF is of course already JAX-WS certified, but our (JBoss) aim is to pass the JavaEE certification testsuite with JBossWS-CXF on top of the application server and that&#39;s a different (and bigger) testsuite than the JAX-WS standalone one.  

Anyway, finally, last week we&#39;ve passed 
100%
 of the tests in the 
jaxws
, 
jws
, 
saaj
 and 
jaxr
 module of the JavaEE 5 CTS TCK.  
This might sound a bit too technical, so let&#39;s read it as follows: 
JBoss AS 5.1.0.GA now supports all [*] the WS functionalities included in the Java EE 5 also using JBossWS-CXF integrated stack, based on Apache CXF
.  

This is a great achievement for us; I&#39;d like to thank the current JBossWS team (especially Richard) and the former one (Thomas and Heiko) for their contribution, as well as Daniel and the other Apache CXF guys for the collaboration.  

Willing to try out Apache CXF stack integration then? 
The JBossWS-CXF 3.1.2.GA has just been released!  

[Get it](http://www.jboss.org/jbossws/) and enjoy :-)  


[*] except for JAX-RPC functionalities, which are not part of Apache CXF.





