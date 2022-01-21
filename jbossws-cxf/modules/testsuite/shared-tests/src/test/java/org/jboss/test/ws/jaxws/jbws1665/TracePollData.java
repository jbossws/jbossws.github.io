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
package org.jboss.test.ws.jaxws.jbws1665;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TracePollData", propOrder = { "traces", "mark", "more" })
public class TracePollData implements Serializable {
   
   private static final long serialVersionUID = -1210126441804051453L;
   
   @XmlElement(required=true,nillable=true)
   protected TraceData[] traces;
   @XmlElement(required = true, nillable = true)
   protected String mark;
   protected boolean more;

   public String getMark() {
      return mark;
   }

   public void setMark(String mark) {
      this.mark = mark;
   }

   public boolean isMore() {
      return more;
   }

   public void setMore(boolean more) {
      this.more = more;
   }
   
   public void setTraces(TraceData[] traces) {
      this.traces = traces;
   }

   public TraceData[] getTraces() {
      return traces;
   }
}
