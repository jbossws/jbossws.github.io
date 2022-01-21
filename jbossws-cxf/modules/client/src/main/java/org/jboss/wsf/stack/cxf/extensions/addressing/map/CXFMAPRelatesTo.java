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
package org.jboss.wsf.stack.cxf.extensions.addressing.map;

import javax.xml.namespace.QName;

import org.jboss.ws.api.addressing.MAPRelatesTo;

/**
 * MAPRelationship is a wrapper class which works with class MAP. This is the JBossWS CXF implementation.
 * 
 * @author Andrew Dinn - adinn@redhat.com
 * @author alessio.soldano@jboss.com
 * @since 26-May-2009
 *
 */
public class CXFMAPRelatesTo implements MAPRelatesTo
{
   private final String relatesTo;
   private final QName type;

   CXFMAPRelatesTo(String relatesTo, QName type)
   {
      this.relatesTo = relatesTo;
      this.type = type;
   }

   public String getRelatesTo()
   {
      return relatesTo;
   }

   public QName getType()
   {
      return type;
   }

   public void setType(QName type)
   {
      throw new UnsupportedOperationException();
   }

}
