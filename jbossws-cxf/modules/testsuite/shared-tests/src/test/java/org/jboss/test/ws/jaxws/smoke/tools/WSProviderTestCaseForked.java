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
package org.jboss.test.ws.jaxws.smoke.tools;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Heiko.Braun@jboss.com
 */
@RunWith(Arquillian.class)
public class WSProviderTestCaseForked extends PluginBase
{
   @Deployment(name="jaxws-classloading-types", order=1, testable = false)
   public static JavaArchive createDeployment1() {
      JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "jaxws-classloading-types.jar");
         archive
               .addManifest()
               .addClass(org.jboss.test.ws.jaxws.smoke.tools.service.Echo.class)
               .addClass(org.jboss.test.ws.jaxws.smoke.tools.service.EchoResponse.class)
               .addClass(org.jboss.test.ws.jaxws.smoke.tools.service.Message.class);
      return archive;
   }

   @Deployment(name="jaxws-classloading-service", order=2, testable = false)
   public static JavaArchive createDeployment2() {
      JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "jaxws-classloading-service.jar");
         archive
            .addManifest()
            .addClass(org.jboss.test.ws.jaxws.smoke.tools.service.HelloWorld.class);
      return archive;
   }

   /**
    * Recreates a tools delegate for every test
    * @throws Exception
    */
   @Before
   public void setup() throws Exception
   {
      setupClasspath();

      Class<?> wspClass = Thread.currentThread().getContextClassLoader().loadClass(WSProviderPlugin.class.getName());
      setDelegate(wspClass);
    }


   @After
   public void teardown() throws Exception
   {
      restoreClasspath();
   }

   @Test
   @RunAsClient
   public void testGenerateWsdl() throws Exception
   {
      dispatch("testGenerateWsdl");
   }

   @Test
   @RunAsClient
   public void testGenerateWsdlWithExtension() throws Exception
   {
      dispatch("testGenerateWsdlWithExtension");
   }

   @Test
   @RunAsClient
   public void testGenerateSource() throws Exception
   {
      dispatch("testGenerateSource");
   }

   @Test
   @RunAsClient
   public void testOutputDirectory() throws Exception
   {
      dispatch("testOutputDirectory");
   }

   @Test
   @RunAsClient
   public void testResourceDirectory() throws Exception
   {
      dispatch("testResourceDirectory");
   }

   @Test
   @RunAsClient
   public void testSourceDirectory() throws Exception
   {
      dispatch("testSourceDirectory");
   }

   @Test
   @RunAsClient
   public void testClassLoader() throws Exception
   {
      dispatch("testClassLoader");
   }

   @Test
   @RunAsClient
   public void testMessageStream() throws Exception
   {
      dispatch("testMessageStream");
   }
}
