---
layout:     post
title:       "Maven plugin for JAXWS tools"
subtitle:   ""
date:       March 01, 2010
author:     JBossWS Team
---


JBossWS comes with [JAXWS tools](http://community.jboss.org/wiki/JBossWS-JAX-WSTools) for top-down and bottom-up webservice development.  

Starting from today, a [
Maven plugin
](http://repository.jboss.org/maven2/org/jboss/ws/plugins/) is available for easily embedding tools&#39; invocation into your own project&#39;s 
pom.xml
:  
`&lt;build&gt;  
&lt;plugins&gt;  
  &lt;plugin&gt;  
    &lt;groupId&gt;org.jboss.ws.plugins&lt;/groupId&gt;  
    &lt;artifactId&gt;maven-jaxws-tools-plugin&lt;/artifactId&gt;  
    &lt;version&gt;1.0.0.GA&lt;/version&gt;  
    &lt;configuration&gt;  
      &lt;wsdls&gt;  
        &lt;wsdl&gt;${basedir}/test.wsdl&lt;/wsdl&gt;  
        &lt;wsdl&gt;${basedir}/test2.wsdl&lt;/wsdl&gt;  
      &lt;/wsdls&gt;  
      &lt;targetPackage&gt;foo.bar&lt;/targetPackage&gt;  
      &lt;extension&gt;true&lt;/extension&gt;  
    &lt;/configuration&gt;  
    &lt;executions&gt;  
      &lt;execution&gt;  
        &lt;goals&gt;  
          &lt;goal&gt;wsconsume&lt;/goal&gt;  
        &lt;/goals&gt;  
      &lt;/execution&gt;  
    &lt;/executions&gt;  
  &lt;/plugin&gt;  
&lt;/plugins&gt;  
&lt;/build&gt;`  
this makes [
wsconsume
](http://community.jboss.org/wiki/JBossWS-wsconsume#Maven_Plugin) parse the specified wsdl files and generate java sources for the SEI, wrapper, etc. The classes are then compiled together with all the other ones in your project.  

Similarly you can use [
wsprovide
](http://community.jboss.org/wiki/JBossWS-wsprovide#Maven_Plugin), see the example below:  
`&lt;build&gt;  
&lt;plugins&gt;  
  &lt;plugin&gt;  
    &lt;groupId&gt;org.jboss.ws.plugins&lt;/groupId&gt;  
    &lt;artifactId&gt;maven-jaxws-tools-plugin&lt;/artifactId&gt;  
    &lt;version&gt;1.0.0.GA&lt;/version&gt;  
    &lt;configuration&gt;  
      &lt;verbose&gt;true&lt;/verbose&gt;  
      &lt;endpointClass&gt;org.jboss.test.ws.plugins.tools.wsprovide.TestEndpoint&lt;/endpointClass&gt;  
      &lt;generateWsdl&gt;true&lt;/generateWsdl&gt;  
    &lt;/configuration&gt;  
    &lt;executions&gt;  
      &lt;execution&gt;  
        &lt;goals&gt;  
          &lt;goal&gt;wsprovide&lt;/goal&gt;  
        &lt;/goals&gt;  
      &lt;/execution&gt;  
    &lt;/executions&gt;  
  &lt;/plugin&gt;  
&lt;/plugins&gt;  
&lt;/build&gt;`  

The plugin has sensible defaults, in particular for computing the classpath to be used before invoking the tools; you just need to make sure your project declares dependencies on a jbossws stack (which you most probably already do, if you have ws endpoints there).  
So it&#39;s really just a matter of declaring the plugin in the pom.xml and running ;-)  
All stacks (JBossWS-Native, JBossWS-CXF, JBossWS-Metro) are supported by the plugin.  
A couple of additional complete sample pom.xml files are available on the SCM, for instance take a look at [this](http://anonsvn.jboss.org/repos/jbossws/projects/plugins/maven/jaxws-tools/tags/jaxws-tools-1.0.0.GA/src/test/resources/test-embedded/testWsConsume/pom.xmlhttp://anonsvn.jboss.org/repos/jbossws/projects/plugins/maven/jaxws-tools/tags/jaxws-tools-1.0.0.GA/src/test/resources/test-embedded/testWsConsume/pom.xmlhttp://anonsvn.jboss.org/repos/jbossws/projects/plugins/maven/jaxws-tools/tags/jaxws-tools-1.0.0.GA/src/test/resources/test-embedded/testWsConsume/pom.xmlhttp://anonsvn.jboss.org/repos/jbossws/projects/plugins/maven/jaxws-tools/tags/jaxws-tools-1.0.0.GA/src/test/resources/test-embedded/testWsConsume/pom.xmlhttp://anonsvn.jboss.org/repos/jbossws/projects/plugins/maven/jaxws-tools/tags/jaxws-tools-1.0.0.GA/src/test/resources/test-embedded/testWsConsume/pom.xmlhttp://anonsvn.jboss.org/repos/jbossws/projects/plugins/maven/jaxws-tools/tags/jaxws-tools-1.0.0.GA/src/test/resources/test-embedded/testWsConsume/pom.xmlhttp://anonsvn.jboss.org/repos/jbossws/projects/plugins/maven/jaxws-tools/tags/jaxws-tools-1.0.0.GA/src/test/resources/test-embedded/testWsConsume/pom.xmlhttp://anonsvn.jboss.org/repos/jbossws/projects/plugins/maven/jaxws-tools/tags/jaxws-tools-1.0.0.GA/src/test/resources/test-embedded/testWsConsume/pom.xml).  
Enjoy!




