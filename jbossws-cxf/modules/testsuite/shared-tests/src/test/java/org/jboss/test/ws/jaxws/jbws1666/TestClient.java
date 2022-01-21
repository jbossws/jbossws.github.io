/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.test.ws.jaxws.jbws1666;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.spi.Provider;

public class TestClient
{
   public static final String REQ_STR = "Hello World!";

   public static String testPortAccess(String serverHost, int serverPort) throws Exception
   {
      URL wsdlURL = new URL("http://" + serverHost + ":" + serverPort + "/jaxws-jbws1666?wsdl");

      QName serviceName = new QName("http://org.jboss.ws/jbws1666", "TestEndpointImplService");
      Service service = Service.create(wsdlURL, serviceName);
      TestEndpoint port = (TestEndpoint)service.getPort(TestEndpoint.class);

      String resStr = port.echo(REQ_STR);
      return resStr;
   }

   public static void main(String[] args) throws Exception
   {
      String serverHost = args[0];
      String serverPort = args[1];
      String resStr = testPortAccess(serverHost, Integer.valueOf(serverPort));
      System.out.println(Provider.provider().getClass().getName() + ", " + resStr);
      
      //wait a bit before returning as the log processing can be aysnch, the test client
      //relies on the log contents and the log streams are closed by the system when the
      //process terminates
      Thread.sleep(1000);
   }
}
