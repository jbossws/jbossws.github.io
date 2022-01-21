/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.test.ws.jaxws.jbws2978;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPFaultException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * JBWS2978TestCase.
 *
 * @author <a href="ema@redhat.com">Jim Ma</a>
 */
@Ignore(value="Tests migrated from JBossWS-Native specific testsuite which are meant to pass with JBossWS-CXF too, but are still to be fixed")
@RunWith(Arquillian.class)
public class JBWS2978TestCase extends JBossWSTest
{
   @Deployment(testable = false)
   public static WebArchive createDeployments() {
      WebArchive archive = ShrinkWrap.create(WebArchive.class, "jaxws-jbws2978.war");
         archive
               .addManifest()
               .addClass(org.jboss.test.ws.jaxws.jbws2978.AddNumbers.class)
               .addClass(org.jboss.test.ws.jaxws.jbws2978.AddNumbersImpl.class)
               .addClass(org.jboss.test.ws.jaxws.jbws2978.AddNumbersRequest.class)
               .addClass(org.jboss.test.ws.jaxws.jbws2978.AddNumbersResponse.class)
               .addAsWebInfResource(new File(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/jbws2978/WEB-INF/jboss-web.xml"), "jboss-web.xml")
               .setWebXML(new File(JBossWSTestHelper.getTestResourcesDir() + "/jaxws/jbws2978/WEB-INF/web.xml"));
      return archive;
   }

   @Test
   @RunAsClient
   public void testCall() throws Exception
   {
      String text = "http://" + getServerHost() + ":" + getServerPort() + "/jaxws-jbws2978";
      String requestMessage = "<S:Envelope xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'><S:Header><To xmlns='http://www.w3.org/2005/08/addressing'>"
         + text
         + "</To><Action xmlns='http://www.w3.org/2005/08/addressing'>inputAction</Action>"
         + "<MessageID xmlns='http://www.w3.org/2005/08/addressing'>uuid:56d586f8-980c-48cf-982d-77a2f56e5c5b</MessageID>"
         + "<ReplyTo xmlns='http://www.w3.org/2005/08/addressing'><Address>http://www.w3.org/2005/08/addressing/anonymous</Address></ReplyTo>"
         + "</S:Header><S:Body><ns1:addNumbers xmlns:ns1='http://ws.jboss.org'><arg0>10</arg0><arg1>10</arg1></ns1:addNumbers></S:Body></S:Envelope>";

      URL wsdlURL = new URL(text + "?wsdl");
      QName serviceName = new QName("http://ws.jboss.org", "AddNumbers");
      Service service = Service.create(wsdlURL, serviceName);
      
      try
      {
         Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName("http://ws.jboss.org", "AddNumbersPort"), SOAPMessage.class ,
               Service.Mode.MESSAGE);
         SOAPMessage reqMsg = MessageFactory.newInstance().createMessage(null,
               new ByteArrayInputStream(requestMessage.getBytes()));
         BindingProvider bp = dispatch;
         java.util.Map<String, Object> requestContext = bp.getRequestContext();
         requestContext.put(BindingProvider.SOAPACTION_URI_PROPERTY, "mismatchAction");
         dispatch.invoke(reqMsg);
         fail("Should throw SOAPFaultExceptoin");
      }
      catch (SOAPFaultException e)
      {
         assertEquals(true, e.getFault().getFaultCode().indexOf("ActionMismatch") > -1);
      }
   }
}