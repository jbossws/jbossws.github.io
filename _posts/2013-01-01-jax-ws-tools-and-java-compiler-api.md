---
layout:     post
title:       "JAX-WS tools and the Java Compiler API"
subtitle:   ""
date:       January 30, 2013
author:     JBossWS Team
---





As of today, **Apache CXF** is well integrated into 
**JBoss AS 7** and working properly through the **JBossWS** layer. The 
application server satisfies the JavaEE 6 requirements (of course 
including the WS related ones) and most of the Apache CXF specific 
funtionalities are [continuously tested](http://jbossws.jboss.org:8180/hudson/) by the JBossWS own testsuite 
and covered in the [application server webservices documentation](https://docs.jboss.org/author/display/AS72/Webservices+reference+guide) .








While we do have plans for future new features and 
contributions, even directly on Apache CXF, we still have very few 
action items to work on in the pure JBoss AS integration area. Last week
 I dealt with one of them, which turned out to be quite interesting from
 a technical point of view, so I thought it&#39;s worth to share it here.








It is well known that JBoss AS 7 is built on top of 
the [modules](http://in.relation.to/16904.lace)&#39; concept and comes with a fully [modular classloader](https://docs.jboss.org/author/display/AS71/Class+Loading+in+AS7) defined by module dependencies and really allowing controlling which
 classes to load, etc. As part of the move to the new classloading 
mechanism, we created modules for most of the Java EE apis that we 
include in the application server, JAX-WS api being one of them. Our own JAX-WS api 
module comes with a slightly modified version of the vanilla jaxws 2.2 
apis, which resolves the _javax.xml.ws.spi.Provider_ into the 
corresponding JBossWS implementation (defaults to it, unless different 
configuration is specified using the _Service API_ mechanism). The jaxws 
api module is then used to define the classloader for any application in
 the application server, so that whenever JAXWS is required its classes are used and the
 **JBossWS (Apache CXF based) implementation is automatically used** under 
the hood. Besides for the fact that we&#39;re easily enforcing usage of our 
JAX-WS impl this way, the real benefit of such an approach is that we 
always **control the JAX-WS api level** (currently 2.2) in use.








As a matter of fact, a mismatch in JAXWS api version
 is usually a common source of major final user headaches; the reason 
for that is basically in JDK 6 (well, actually starting from update 4) 
coming with JAXWS 2.1 api included. Unless the bootclasspath is properly
 modified by setting the _java.endorsed.dirs_ env to a directory containing the  jaxws 2.2 api jar when starting the virtual machine, 2.1 api classes 
from the JDK will always be used by default whenever making use of 
JAX-WS apis. Users will eventually forget about that, make mistakes in 
setting that, get confused with tools / IDE configuration of that, 
etc... and not understand what&#39;s really happening and why they&#39;re 
getting bunch of errors.








JBoss AS 7 relies on JBoss Modules for modular 
classloading and does not require any _java.endorsed.dirs_ setup when 
booting the AS. So far so good :-)



JBossWS (as well as Apache 
CXF) however provides JAX-WS tools for wsdl-to-java and java-to-wsdl 
generation; each of them comes with _wsconsume.sh/bat_ and _wsprovide.sh/bat_
 scripts to execute on command line. The tools internally rely on Apache
 CXF corresponding tools, which generate code and compile it. In order 
for having CXF compile jaxws 2.2 compliant code, we used to need setting
 the _java.endorsed.dirs_ env in the wsconsume and wsprovide scripts, 
failing that the code couldn&#39;t simply be compiled, because CXF runs 
_javac_ which would of course get the JAXWS api directly from the JDK.








So, a [jira](https://issues.jboss.org/browse/AS7-6355) has been created for fixing this, as 
we do not want to set the _java.endorsed.dirs_ anymore, even for ws tools.
 How to solve this then?



Fortunately, Apache CXF tools 
recently moved to direct usage of **Java Compiler API** to compile sources, 
instead of forking a process for running javac (forking is still 
possible, yet not the default behaviour). Starting from JDK 6, the Java 
Compiler API (JSR-199) offers a powerfull (yet quite complicated) way of
 creating a compilation task and supplying source files to it for being 
compiled. Below is an excerpt of the CXF code making use of JSR-199:















The [javax.tools.JavaFileManager](http://docs.oracle.com/javase/6/docs/api/javax/tools/JavaFileManager.html) interface allows
 for controlling loading of any class involved in the compilation task; 
it&#39;s possible to define and install a custom [ForwardingJavaFileManager](http://docs.oracle.com/javase/6/docs/api/javax/tools/ForwardingJavaFileManager.html) to 
override the methods that actually fetch and provide the bytecode for 
any class.








So, what we ended up in [doing here](https://source.jboss.org/viewrep/JBossWS/stack/cxf/trunk/modules/client/src/main/java/org/jboss/wsf/stack/cxf/tools/CXFConsumerImpl.java?r1=16758&amp;r2=17246) was to define an extension of  _ForwardingJavaFileManager_ that would **intercept requests for 
the JAXWS** (and JAXB...) **api classes, load the corresponding ones from our
 JBoss Module classloader, read their bytecode and create proper 
[JavaFileObject](http://docs.oracle.com/javase/6/docs/api/javax/tools/JavaFileObject.html) instances to return to the JDK compiler**. It took a while 
to write a decent JavaFileManager implementation (also considering I didn&#39;t want 
any JBoss Module specific code in it in JBossWS), but it finally worked and **allowed
 compiling jaxws 2.2 code with JDK6 and no endorsing configuration**.








Then, the usual JBossWS open source **integration 
process** started, as I had to figure out how to extend Apache CXF in a 
general way so that I could made my JavaFileManager available [deep](http://svn.apache.org/repos/asf/cxf/tags/cxf-2.7.2/api/src/main/java/org/apache/cxf/common/util/Compiler.java) into the CXF
 tools code. Once again, the _Apache CXF community proved to be really 
open_ to extensions and contributions even for really specific stuff like
 this and the Apache CXF project lead even figured out possible 
[different scenarios that would benefit](http://mail-archives.apache.org/mod_mbox/cxf-dev/201301.mbox/%3C2F7A988A-F0AF-434D-B287-0F3AD5A3249D%40apache.org%3E) from my extension. So a CXF [jira](https://issues.apache.org/jira/browse/CXF-4777) was created, we discussed design a bit on IRC and I committed my changes on
 Apache CXF and JBossWS.  





A new Apache CXF release is coming out very soon and hopefully by 
the end of next week I&#39;ll be able to include it in JBoss AS upstream and
 fix the _wsconsume/wsprovide_ command line scripts, **cool!**








This is what **integration** really means to me :-)









