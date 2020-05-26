---
layout:     post
title:       "JBossWS 4.2 feature preview"
subtitle:   ""
date:       April 18, 2013
author:     JBossWS Team
---


This blog post is about few additions that are coming with **JBossWS 4.2** release which I actually started working on due to some specific [**Narayana**](http://www.jboss.org/jbosstm) requirements, but ended up using for additional tasks too.  

Starting from version 2.1, the _JAX-WS API_ contains the [_javax.xml.ws.WebServiceFeature_](http://docs.oracle.com/javase/6/docs/api/javax/xml/ws/WebServiceFeature.html) abstract class which is used to [configure clients](http://docs.oracle.com/javase/6/docs/api/javax/xml/ws/class-use/WebServiceFeature.html), e.g. when getting the _port_ stub. The _JAX-WS_ reference impl, which is also included in the _JDK_, bundles three implementations of _WebServiceFeature_ for controlling [addressing](http://docs.oracle.com/javase/6/docs/api/javax/xml/ws/soap/AddressingFeature.html), [MTOM](http://docs.oracle.com/javase/6/docs/api/javax/xml/ws/soap/MTOMFeature.html) and [wsdl:extension usage](http://docs.oracle.com/javase/6/docs/api/javax/xml/ws/RespectBindingFeature.html).  

As of [JBossWS 4.2.0.Alpha1](https://repository.jboss.org/nexus/content/groups/public-jboss/org/jboss/ws/cxf/jbossws-cxf/4.2.0.Alpha1/), a custom extension of _WebServiceFeature_ is available, [_org.jboss.ws.api.configuration.AbstractClientFeature_](http://anonsvn.jboss.org/repos/jbossws/api/tags/jbossws-api-1.0.2.Alpha1/src/main/java/org/jboss/ws/api/configuration/AbstractClientFeature.java). That offers users and project integrators a way of initializing the client [_BindingProvider_](http://docs.oracle.com/javase/6/docs/api/javax/xml/ws/BindingProvider.html), setting handlers, etc. The JBoss project for distributed webservices transactions, Narayana, actually leveraged this addition to [really simplify the way users write clients](http://jbossts.blogspot.it/2013/04/simplified-xts-context-propagation.html) for _WS-AT_ and _WS-BA_ enabled webservices. Two custom features built on top of _AbstractClientFeature_ let Narayama hide all its context propagation configuration from the user eyes ;-)  

Now, as most of you probably know, JBossWS also comes with a notion of [predefined client-configuration](https://docs.jboss.org/author/display/JBWS/Predefined+client+and+endpoint+configurations) which can be used to prepare _JAX-WS_ client configuration templates to associate client ports to. Configurations can include properties as well as handlers declaration and are provided either as part of the **JBoss AS** webservices subsystem configuration (_standalone.xml_ / _domain.xml_) or as a separate file.  

The _AbstractClientFeature_ extension above offered a way for also simplifying a client-configuration setup for a given _JAX-WS_ client; the [_org.jboss.ws.api.configuration.ClientConfigFeature_](http://anonsvn.jboss.org/repos/jbossws/api/tags/jbossws-api-1.0.2.Alpha1/src/main/java/org/jboss/ws/api/configuration/ClientConfigFeature.java) allows specifying the name of the desired client-configuration as well as the file to read that from (null for reading from the AS model):  



Nice, isn&#39;t it?

Feel free to try this as well as any other additions on JBossWS trunk, the 4.2.0 final release is coming in the next months. **Feedback is welcome!**




