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
package org.jboss.test.ws.jaxws.samples.wsse.policy.basic;

import javax.jws.WebService;

import org.apache.cxf.annotations.EndpointProperties;
import org.apache.cxf.annotations.EndpointProperty;
import org.jboss.ws.api.annotation.PolicySets;
import org.jboss.wsf.stack.cxf.extensions.policy.Constants;

@WebService(
   portName = "AnnotatedSecurityServicePort",
   serviceName = "AnnotatedSecurityService",
   name = "AnnotatedServiceIface",
   endpointInterface = "org.jboss.test.ws.jaxws.samples.wsse.policy.basic.AnnotatedServiceIface",
   targetNamespace = "http://www.jboss.org/jbossws/ws-extensions/wssecuritypolicy"
)
@EndpointProperties(value = {
      @EndpointProperty(key = "ws-security.signature.properties", value = "bob.properties"),
      @EndpointProperty(key = "ws-security.encryption.properties", value = "bob.properties"),
      @EndpointProperty(key = "ws-security.signature.username", value = "bob"),
      @EndpointProperty(key = "ws-security.encryption.username", value = "alice"),
      @EndpointProperty(key = "ws-security.callback-handler", value = "org.jboss.test.ws.jaxws.samples.wsse.policy.basic.KeystorePasswordCallback")
      }
)
@PolicySets(Constants.AsymmetricBinding_X509v1_GCM256OAEP_ProtectTokens_POLICY_SET)
public class AnnotatedServiceImpl implements AnnotatedServiceIface
{
   public String sayHello()
   {
      return "Secure Hello World!";
   }
}
