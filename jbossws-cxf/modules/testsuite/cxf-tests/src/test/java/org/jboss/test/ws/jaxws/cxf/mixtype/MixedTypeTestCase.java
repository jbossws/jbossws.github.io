/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.test.ws.jaxws.cxf.mixtype;

import java.io.File;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MixedTypeTestCase extends JBossWSTest
{
   private final String targetNS = "http://org.jboss.ws.jaxws.cxf/mixtype";
   
   @ArquillianResource
   private URL baseURL;
   
   @Deployment(testable = false)
   public static WebArchive createDeployment() {
      WebArchive archive = ShrinkWrap.create(WebArchive.class, "jaxws-cxf-mixtype.war");
      archive.addManifest()
            .addClass(org.jboss.test.ws.jaxws.cxf.mixtype.EndpointOne.class)
            .addClass(org.jboss.test.ws.jaxws.cxf.mixtype.EndpointOneEJB3Impl.class)
            .addClass(org.jboss.test.ws.jaxws.cxf.mixtype.EndpointOneImpl.class)
            .addClass(org.jboss.test.ws.jaxws.cxf.mixtype.EndpointTwoImpl.class)
            .addAsWebInfResource(new File(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/cxf/mixtype/WEB-INF/jboss-web.xml"), "jboss-web.xml")
            .setWebXML(new File(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/cxf/mixtype/WEB-INF/web.xml"));
      return archive;
   }

   @Test
   @RunAsClient
   public void testEndpoint() throws Exception
   {
      URL wsdlOneURL = new URL(baseURL + "/ServiceOne/EndpointOne?wsdl");
      QName serviceOneName = new QName(targetNS, "ServiceOne");
      Service service = Service.create(wsdlOneURL, serviceOneName);
      EndpointOne endpoint = (EndpointOne)service.getPort(new QName(targetNS, "EndpointOnePort"), EndpointOne.class);
      int initialCount = endpoint.getCount();
      assertEquals("mixedType", endpoint.echo("mixedType"));
      assertEquals(1, endpoint.getCount() - initialCount);
   }
   
   @Test
   @RunAsClient
   public void testEJBEndpoint() throws Exception
   {
      URL wsdlOneURL = new URL(baseURL + "/EJBServiceOne/EndpointOneEJB3Impl?wsdl");
      QName serviceOneName = new QName(targetNS, "EJBServiceOne");
      Service service = Service.create(wsdlOneURL, serviceOneName);
      EndpointOne endpoint = (EndpointOne)service.getPort(new QName(targetNS, "EJBEndpointOnePort"), EndpointOne.class);
      int initialCount = endpoint.getCount();
      assertEquals("mixedType", endpoint.echo("mixedType"));
      assertEquals(5, endpoint.getCount() - initialCount);
   }

   @Test
   @RunAsClient
   public void testEndpoint2() throws Exception
   {
      //verify everything works with an endpoint extending another one impl
      URL wsdlOneURL = new URL(baseURL + "/ServiceOne/EndpointTwo?wsdl");
      QName serviceOneName = new QName(targetNS, "ServiceOne");
      Service service = Service.create(wsdlOneURL, serviceOneName);
      EndpointOne endpoint = (EndpointOne)service.getPort(new QName(targetNS, "EndpointTwoPort"), EndpointOne.class);
      int initialCount = endpoint.getCount();
      assertEquals("mixedType", endpoint.echo("mixedType"));
      assertEquals(1, endpoint.getCount() - initialCount);
   }
   
   @Test
   @RunAsClient
   public void testEndpoint2WithAnotherURLPattern() throws Exception
   {
      //verify everything works with an endpoint extending another one impl
      URL wsdlOneURL = new URL(baseURL + "/ServiceOne/AnotherEndpointTwo?wsdl");
      QName serviceOneName = new QName(targetNS, "ServiceOne");
      Service service = Service.create(wsdlOneURL, serviceOneName);
      EndpointOne endpoint = (EndpointOne)service.getPort(new QName(targetNS, "EndpointTwoPort"), EndpointOne.class);
      int initialCount = endpoint.getCount();
      assertEquals("mixedType", endpoint.echo("mixedType"));
      assertEquals(1, endpoint.getCount() - initialCount);
   }
 
}