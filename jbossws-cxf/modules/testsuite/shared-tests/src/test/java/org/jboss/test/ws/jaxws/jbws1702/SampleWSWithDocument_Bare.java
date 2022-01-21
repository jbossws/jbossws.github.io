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
package org.jboss.test.ws.jaxws.jbws1702;

import javax.jws.WebService;

import org.jboss.test.ws.jaxws.jbws1702.types.ClassC;
import org.jboss.test.ws.jaxws.jbws1702.types.ResponseWrapperB;
import org.jboss.test.ws.jaxws.jbws1702.types.ResponseWrapperC;

/**
 *
 *
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Organisation: Marabu EDV</p>
 * @author strauch
 * @version     1.0
 */
@WebService(endpointInterface = "org.jboss.test.ws.jaxws.jbws1702.SampleWSBareSEI")
public class SampleWSWithDocument_Bare implements SampleWSBareSEI
{

  /**
   * Creates a new instance of SampleWSWithDocument_Bare
   */
  public SampleWSWithDocument_Bare() {
  }

  /**
   * In .NET Client (C#) only the content information of ClassB is being submitted.  (--> propC is unknown)
   */
  @Override
public ResponseWrapperB getClassCAsClassB() {
    ClassC classC= new ClassC();
    classC.setPropA("propA");
    classC.setPropB("propB");
    classC.setPropC("propC");

    ResponseWrapperB resp = new ResponseWrapperB();
    resp.setData(classC);
    return resp;
  }

  /**
   * Method that make ClassC available for all clients using this web service.
   */
  @Override
public ResponseWrapperC getClassC() {
    ClassC data = (ClassC) getClassCAsClassB().getData();
    ResponseWrapperC resp = new ResponseWrapperC();
    resp.setData(data);
    return resp;
  }

}
