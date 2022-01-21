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
package org.jboss.test.ws.jaxws.jbws1904;

import java.net.URL;
import java.util.Iterator;

import javax.xml.namespace.QName;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.ws.common.DOMUtils;
import org.jboss.wsf.test.JBossWSTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * [JBWS-1904] Explicitly set the namespace of a WebFault
 *
 * http://jira.jboss.org/jira/browse/JBWS-1904
 *
 * @author alessio.soldano@jboss.com
 * @since 13-Dec-2007
 */
@RunWith(Arquillian.class)
public class JBWS1904TestCase extends JBossWSTest
{
   @ArquillianResource
   private URL baseURL;

   @Deployment(testable = false)
   public static JavaArchive createDeployments() {
      JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "jaxws-jbws1904.jar");
         archive
            .addManifest()
            .addClass(org.jboss.test.ws.jaxws.jbws1904.Endpoint.class)
            .addClass(org.jboss.test.ws.jaxws.jbws1904.EndpointImpl.class)
            .addClass(org.jboss.test.ws.jaxws.jbws1904.UserException.class);
      return archive;
   }

   @Test
   @RunAsClient
   public void testWSDLSchema() throws Exception
   {
      URL wsdlURL = new URL(baseURL + "/jaxws-jbws1904?wsdl");

      Document doc = DOMUtils.getDocumentBuilder().parse(wsdlURL.toString());
      NodeList schemas = ((Element)doc.getDocumentElement()
         .getElementsByTagNameNS("http://schemas.xmlsoap.org/wsdl/", "types").item(0))
            .getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "schema");

      boolean firstTypeFound = false;
      boolean secondTypeFound = false;
      for (int i = 0; i < schemas.getLength(); i++)
      {
         Element schema = (Element)schemas.item(i);
         if (schema.getAttribute("targetNamespace").equals("http://org.jboss.ws/jbws1904/faults"))
         {
            Iterator<?> elements = DOMUtils.getChildElements(schema, new QName("http://www.w3.org/2001/XMLSchema", "element"));
            while (elements.hasNext())
            {
               Element e = (Element)elements.next();
               boolean nameEquals = e.getAttribute("name").equals("UserExceptionFault");
               boolean typeEquals = e.getAttribute("type").endsWith(":UserException");
               if (nameEquals && typeEquals)
               {
                  firstTypeFound = true;
               }
            }
         }
         if (schema.getAttribute("targetNamespace").equals("http://org.jboss.ws/jbws1904/exceptions"))
         {
            Iterator<?> elements = DOMUtils.getChildElements(schema, new QName("http://www.w3.org/2001/XMLSchema", "complexType"));
            while (elements.hasNext())
            {
               Element e = (Element)elements.next();
               boolean nameEquals = e.getAttribute("name").equals("UserException");
               if (nameEquals)
               {
                  secondTypeFound = true;
               }
            }
         }
      }
      assertTrue(firstTypeFound && secondTypeFound);
   }
}
