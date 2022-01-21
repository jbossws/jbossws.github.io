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
package org.jboss.test.ws.jaxws.jbws1702.types;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 *
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Organisation: Marabu EDV</p>
 * @author strauch
 * @version     1.0
 */
@XmlSeeAlso({ClassB.class, ClassC.class})
public class ClassA
{

  private String propA;

  /**
   * Creates a new instance of ClassA
   */
  public ClassA() {
  }

  public String getPropA() {
    return propA;
  }

  public void setPropA(String propA) {
    this.propA = propA;
  }

}
