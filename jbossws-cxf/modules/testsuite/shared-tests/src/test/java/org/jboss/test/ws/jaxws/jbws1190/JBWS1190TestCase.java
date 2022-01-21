/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.test.ws.jaxws.jbws1190;

import java.io.File;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * WSDL generated for JSR-181 POJO does not take 'transport-guarantee' in web.xml into account
 * 
 * http://jira.jboss.org/jira/browse/JBWS-1190
 * 
 * @author darran.lofthouse@jboss.com
 * @author alessio.soldano@jboss.com
 * @since 19-October-2006
 */
@RunWith(Arquillian.class)
public class JBWS1190TestCase extends JBossWSTest
{
   @Deployment(testable = false)
   public static WebArchive createDeployments() {
      WebArchive archive = ShrinkWrap.create(WebArchive.class, "jaxws-jbws1190.war");
         archive
               .addManifest()
               .addClass(org.jboss.test.ws.jaxws.jbws1190.ConfidentialEndpoint.class)
               .addClass(org.jboss.test.ws.jaxws.jbws1190.Endpoint.class)
               .addClass(org.jboss.test.ws.jaxws.jbws1190.EndpointImpl.class)
               .addClass(org.jboss.test.ws.jaxws.jbws1190.JBWS1190Exception.class)
               .setWebXML(new File(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/jbws1190/WEB-INF/web.xml"));
      return archive;
   }

   @Test
   @RunAsClient
   public void testEndpointAddress() throws Exception
   {
      MBeanServerConnection server = getServer();
      ObjectName oname = new ObjectName("jboss.ws:context=jaxws-jbws1190,endpoint=Endpoint");
      String address = (String)server.getAttribute(oname, "Address");
      assertTrue("Expected http address, but got: " + address, address.startsWith("http://"));
   }

   @Test
   @RunAsClient
   public void testConfidentialEndpointAddress() throws Exception
   {
      MBeanServerConnection server = getServer();
      ObjectName oname = new ObjectName("jboss.ws:context=jaxws-jbws1190,endpoint=ConfidentialEndpoint");
      String address = (String)server.getAttribute(oname, "Address");
      assertTrue("Expected https address, but got: " + address, address.startsWith("https://"));
   }
}
