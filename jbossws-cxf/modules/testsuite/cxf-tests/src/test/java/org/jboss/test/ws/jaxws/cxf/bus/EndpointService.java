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
package org.jboss.test.ws.jaxws.cxf.bus;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "EndpointService", targetNamespace = "http://org.jboss.ws/bus", wsdlLocation = "file://bogus-location/jaxws-cxf-busf?wsdl")
public class EndpointService
    extends Service
{

    private final static URL WSDL_LOCATION;
    private final static QName TESTENDPOINTSERVICE = new QName("http://org.jboss.ws/bus", "EndpointService");
    private final static QName TESTENDPOINTPORT = new QName("http://org.jboss.ws/bus", "EndpointPort");

    static {
        URL url = null;
        try {
            url = new URL("file://bogus-location/jaxws-cxf-bus?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public EndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
       super(wsdlLocation, serviceName, features);
    }

    public EndpointService() {
        super(WSDL_LOCATION, TESTENDPOINTSERVICE);
    }

    /**
     * 
     * @return
     *     returns Endpoint
     */
    @WebEndpoint(name = "EndpointPort")
    public Endpoint getEndpointPort() {
        return (Endpoint)super.getPort(TESTENDPOINTPORT, Endpoint.class);
    }
    
    /**
     * 
     * @return
     *     returns Endpoint
     */
    @WebEndpoint(name = "EndpointPort")
    public Endpoint getEndpointPort(WebServiceFeature... features) {
        return (Endpoint)super.getPort(TESTENDPOINTPORT, Endpoint.class, features);
    }

}
