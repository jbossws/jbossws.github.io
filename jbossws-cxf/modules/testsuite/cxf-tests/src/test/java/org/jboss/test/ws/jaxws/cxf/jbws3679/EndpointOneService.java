/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.test.ws.jaxws.cxf.jbws3679;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
@WebServiceClient(name = "ServiceOne", targetNamespace = "http://org.jboss.ws.jaxws.cxf/jbws3679")
public class EndpointOneService extends Service {

    public EndpointOneService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EndpointOneService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
       super(wsdlLocation, serviceName, features);
   }

    @WebEndpoint(name = "EndpointOne")
    public EndpointOne getSheepWSPort() {
        return super.getPort(new QName("http://org.jboss.ws.jaxws.cxf/jbws3679", "EndpointOnePort"), EndpointOne.class);
    }

    @WebEndpoint(name = "EndpointOne")
    public EndpointOne getSheepWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://org.jboss.ws.jaxws.cxf/jbws3679", "EndpointOnePort"), EndpointOne.class, features);
    }
}
