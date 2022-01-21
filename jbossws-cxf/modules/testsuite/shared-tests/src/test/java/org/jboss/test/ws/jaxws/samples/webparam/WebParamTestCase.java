/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.test.ws.jaxws.samples.webparam;

import java.io.File;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestHelper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test the JSR-181 annotation: javax.jws.WebParam
 *
 * @author Thomas.Diesler@jboss.org
 * @since 07-Oct-2005
 */
@RunWith(Arquillian.class)
public class WebParamTestCase extends JBossWSTest
{
   private String targetNS = "http://www.openuri.org/jsr181/WebParamExample";  
   private static PingService port;

   @Deployment(testable = false)
   public static WebArchive createDeployment1() {
      WebArchive archive = ShrinkWrap.create(WebArchive.class, "jaxws-samples-webparam.war");
      archive.addManifest()
             .addClass(org.jboss.test.ws.jaxws.samples.webparam.PingDocument.class)
             .addClass(org.jboss.test.ws.jaxws.samples.webparam.PingServiceImpl.class)
             .addClass(org.jboss.test.ws.jaxws.samples.webparam.SecurityHeader.class)
             .setWebXML(new File(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/samples/webparam/WEB-INF/web.xml"));
      return archive;
   }

   @Before
   public void createPort() throws Exception
   {
      if (port == null)
      {
         QName serviceName = new QName(targetNS, "PingServiceService");
         URL wsdlURL = getResourceURL("jaxws/samples/webparam/META-INF/wsdl/PingService.wsdl");

         Service service = Service.create(wsdlURL, serviceName);
         port = service.getPort(PingService.class);
      }
   }
   @Test
   @RunAsClient
   public void testEcho() throws Exception
   {
      PingDocument doc = new PingDocument();
      doc.setContent("Hello Kermit");
      PingDocument retObj = port.echo(doc);
      assertEquals(doc.getContent(), retObj.getContent());
   }
   @Test
   @RunAsClient
   public void testPingOneWay() throws Exception
   {
      PingDocument doc = new PingDocument();
      doc.setContent("Hello Kermit");
      port.pingOneWay(doc);
   }
   @Test
   @RunAsClient
   public void testPingTwoWay() throws Exception
   {
      PingDocument doc = new PingDocument();
      doc.setContent("Hello Kermit");
      Holder<PingDocument> holder = new Holder<PingDocument>(doc);

      port.pingTwoWay(holder);
      assertEquals("Hello Kermit Response", holder.value.getContent());
   }
   @Test
   @RunAsClient
   public void testSecurePing() throws Exception
   {
      PingDocument doc = new PingDocument();
      doc.setContent("Hello Kermit");
      SecurityHeader secHeader = new SecurityHeader();
      secHeader.setValue("some secret");

      port.securePing(doc, secHeader);
   }
   
   @AfterClass
   public static void cleanupPort() {
	   port = null;
   }
}
