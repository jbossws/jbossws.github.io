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
package org.jboss.test.ws.jaxws.samples.handlerchain;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

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

/**
 * Test the JSR-181 annotation: javax.jws.HandlerChain
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 * @author Thomas.Diesler@jboss.org
 * @since 15-Oct-2005
 */
@RunWith(Arquillian.class)
public class HandlerChainTestCase extends JBossWSTest
{
   private static final String targetNS = "http://handlerchain.samples.jaxws.ws.test.jboss.org/";

   @ArquillianResource
   private URL baseURL;

   @Deployment(testable = false)
   public static WebArchive createDeployments() {
      WebArchive archive = ShrinkWrap.create(WebArchive.class, "jaxws-samples-handlerchain.war");
         archive
               .addManifest()
               .addClass(org.jboss.test.ws.jaxws.samples.handlerchain.AuthorizationHandler.class)
               .addClass(org.jboss.test.ws.jaxws.samples.handlerchain.ClientMimeHandler.class)
               .addClass(org.jboss.test.ws.jaxws.samples.handlerchain.Endpoint.class)
               .addClass(org.jboss.test.ws.jaxws.samples.handlerchain.EndpointImpl.class)
               .addClass(org.jboss.test.ws.jaxws.samples.handlerchain.LogHandler.class)
               .addClass(org.jboss.test.ws.jaxws.samples.handlerchain.RoutingHandler.class)
               .addClass(org.jboss.test.ws.jaxws.samples.handlerchain.ServerMimeHandler.class)
               .addAsManifestResource(new File(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/samples/handlerchain/WEB-INF/permissions.xml"), "permissions.xml")
               .addAsResource("org/jboss/test/ws/jaxws/samples/handlerchain/jaxws-handlers-server.xml")
               .setWebXML(new File(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/samples/handlerchain/WEB-INF/web.xml"));
      return archive;
   }

   @Test
   @RunAsClient
   public void testDynamicHandlerChain() throws Exception
   {
      QName serviceName = new QName(targetNS, "EndpointImplService");
      URL wsdlURL = new URL(baseURL + "/TestService?wsdl");

      Service service = Service.create(wsdlURL, serviceName);
      Endpoint port = service.getPort(Endpoint.class);

      BindingProvider bindingProvider = (BindingProvider)port;
      @SuppressWarnings("rawtypes")
      List<Handler> handlerChain = new ArrayList<Handler>();
      handlerChain.add(new LogHandler());
      handlerChain.add(new AuthorizationHandler());
      handlerChain.add(new RoutingHandler());
      handlerChain.add(new ClientMimeHandler());
      bindingProvider.getBinding().setHandlerChain(handlerChain);

      String resStr = port.echo("Kermit");
      assertEquals("Kermit|LogOut|AuthOut|RoutOut|RoutIn|AuthIn|LogIn|endpoint|LogOut|AuthOut|RoutOut|RoutIn|AuthIn|LogIn", resStr);
      assertCookies();
   }

   @Test
   @RunAsClient
   public void testHandlerChainOnService() throws Exception
   {
      QName serviceName = new QName(targetNS, "EndpointImplService");
      URL wsdlURL = new URL(baseURL + "/TestService?wsdl");

      Service service = new EndpointWithHandlerChainService(wsdlURL, serviceName);
      EndpointWithHandlerChain port = service.getPort(EndpointWithHandlerChain.class);

      String resStr = port.echo("Kermit");
      assertEquals("Kermit|LogOut|AuthOut|RoutOut|RoutIn|AuthIn|LogIn|endpoint|LogOut|AuthOut|RoutOut|RoutIn|AuthIn|LogIn", resStr);
      assertCookies();
   }

   private void assertCookies() throws Exception
   {
      assertEquals("server-cookie=true", ClientMimeHandler.inboundCookie);
   }
}
