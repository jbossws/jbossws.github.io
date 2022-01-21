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
package org.jboss.test.ws.jaxws.samples.schemavalidation;

import org.jboss.test.ws.jaxws.samples.schemavalidation.types.HelloResponse;
/**
 * @author ema@redhat.com
 */
@javax.jws.WebService(serviceName = "HelloService",
		portName = "HelloPort", targetNamespace = "http://jboss.org/schemavalidation", 
		wsdlLocation = "WEB-INF/wsdl/hello.wsdl", 
		endpointInterface = "org.jboss.test.ws.jaxws.samples.schemavalidation.Hello")
public class HelloImpl implements Hello {

   public HelloResponse helloRequest(String input)
   {
      System.out.println("Hello: " + input);
      HelloResponse response = new HelloResponse();
      response.setReturn(2);
      return response;

   }

}
