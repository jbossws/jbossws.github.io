---
layout:     post
title:       "Simplifying use of JAXB Introductions"
subtitle:   ""
date:       October 15, 2009
author:     JBossWS Team
---


The [JAXBIntros project](http://www.jboss.org/community/wiki/JAXBIntroductions) provides a mean of using JAXB with non annotated classes, getting the required binding information from a xml config file. The information is converted into a binding customization that is basically a custom annotation reader JAXB allows as a hook for driving his context creation [1].  

One of the targets of JBossWS 3.2.1 is to enable use of JAXB Introductions with the Apache CXF based stack. In order to do that I&#39;ve gone through the currently available integration code between the JAXBIntro project and the JBossWS-Native stack and rationalized it to simplify the use of JAXB Introductions with every WS stack.  

JAXBIntros project has been &#34;mavenized&#34; and the new [1.0.1.GA](http://repository.jboss.com/maven2/jboss/jaxbintros/jboss-jaxb-intros/1.0.1.GA/) version is currently available in the JBoss Maven repository. It now has a convenient class (BindingCustomizationFactory) for getting binding customizations ready for being installed in a JAXB context while hiding the internals of jaxb-impl.  

`  
public class BindingCustomizationFactory {  
   public static Map&lt;String, Object&gt; getBindingCustomization(InputStream introsConfigStream, String namespace) {  
      Map&lt;String, Object&gt; jaxbCustomizations = new HashMap&lt;String, Object&gt;();  
      populateBindingCustomization(introsConfigStream, namespace, jaxbCustomizations);  
      return jaxbCustomizations;  
   }  

   public static void populateBindingCustomization(InputStream introsConfigStream, Map&lt;String, Object&gt; customization) {  
      populateBindingCustomization(introsConfigStream, null, customization);  
   }  

   public static void populateBindingCustomization(InputStream introsConfigStream, String namespace, Map&lt;String, Object&gt; customization) {  
      JaxbIntros jaxbIntros = IntroductionsConfigParser.parseConfig(introsConfigStream);  
      IntroductionsAnnotationReader annotationReader = new IntroductionsAnnotationReader(jaxbIntros);  
      String defaultNamespace = namespace != null ? namespace : jaxbIntros.getDefaultNamespace();  

      customization.put(JAXBRIContext.ANNOTATION_READER, annotationReader);  
      if (defaultNamespace != null) {  
         customization.put(JAXBRIContext.DEFAULT_NAMESPACE_REMAP, defaultNamespace);  
      }  
   }  
}  
`  

With that users can simply populate their own binding customization (which is really nothing more than a Map
) and provide it when constructing the JAXBContext. That&#39;s all, the new context will consider the customizations and acts accordingly when used for marshalling/unmarshalling objects to/from xml.  

Since the first release of JAXBIntros, JBossWS-Native used to have a CustomizableJAXBContextFactory getting the binding customization from the current endpoint deployment and providing it to the JAXBRIContextFactory.  
Now, as previosly mentioned, enabling JAXB Introductions is just a matter of giving the customization map a way to JAXBContext creation, it doesn&#39;t matter which webservice stack is used.  

As a prove of that, I&#39;ve recently provided seamless integration with JBossWS-CXF. Basically CXF allows users to specify a Configurer (org.apache.cxf.configuration.Configurer) that is called whenever a configurable CXF bean is setup. JBossWS-CXF has a JBossWSCXFConfigurer ([org.jboss.wsf.stack.cxf.client.configuration.JBossWSCXFConfigurer](http://anonsvn.jboss.org/repos/jbossws/stack/cxf/trunk/modules/client/src/main/java/org/jboss/wsf/stack/cxf/client/configuration/JBossWSCXFConfigurer.java)) users can leverage to configure the bus for use with JBossWS, in this case to simply plug-in binding customizations:  

`  
package org.jboss.wsf.spi.binding;  
...  
public class JAXBBindingCustomization extends HashMap  
{  

}  

BindingCustomization jaxbCustomizations = new JAXBBindingCustomization();  
BindingCustomizationFactory.populateBindingCustomization(getResourceURL(&#34;jaxws/cxf/jaxbintros/META-INF/jaxb-intros.xml&#34;).openStream(), jaxbCustomizations);  
bus = BusFactory.getThreadDefaultBus();  
originalConfigurer = bus.getExtension(Configurer.class);  
JBossWSCXFConfigurer configurer = new JBossWSCXFConfigurer(originalConfigurer);  
configurer.setBindingCustomization(jaxbCustomizations);  
bus.setExtension(configurer, Configurer.class);  
//use the bus to create the service and then invoke the endpoint  
`  

On server side the same configurer is automatically installed and it uses the binding customizations coming from the optional
 jaxb.intro.xml
 file in the deployment.  

Please not that while this all comes out of the box with JBossWS-CXF 3.2.1, with &#34;plain&#34; Apache CXF 2.2.4 you can manually use a 
Configurer
 like the one mentioned above and/or directly provide the customization properties in the 
JAXBDatabinding
 element of the CXF Spring [bus configuration file](http://cxf.apache.org/docs/jaxb.html) ;-)  

[1] [http://weblogs.java.net/blog/kohsuke/archive/2007/07/binding_3rd_par.html](http://weblogs.java.net/blog/kohsuke/archive/2007/07/binding_3rd_par.html)  






