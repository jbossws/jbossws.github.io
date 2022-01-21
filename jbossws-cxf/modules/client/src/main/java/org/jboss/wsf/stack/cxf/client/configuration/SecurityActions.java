/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.wsf.stack.cxf.client.configuration;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.jboss.ws.common.utils.DelegateClassLoader;

/**
 * 
 * @author alessio.soldano@jboss.com
 * @since 22-Feb-2011
 *
 */
class SecurityActions
{
   /**
    * Get context classloader.
    * 
    * @return the current context classloader
    */
   static ClassLoader getContextClassLoader()
   {
      SecurityManager sm = System.getSecurityManager();
      if (sm == null)
      {
         return Thread.currentThread().getContextClassLoader();
      }
      else
      {
         return AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
            public ClassLoader run()
            {
               return Thread.currentThread().getContextClassLoader();
            }
         });
      }
   }
   
   /**
    * Set context classloader.
    *
    * @param classLoader the classloader
    */
   static void setContextClassLoader(final ClassLoader classLoader)
   {
      if (System.getSecurityManager() == null)
      {
         Thread.currentThread().setContextClassLoader(classLoader);
      }
      else
      {
         AccessController.doPrivileged(new PrivilegedAction<Object>()
         {
            public Object run()
            {
               Thread.currentThread().setContextClassLoader(classLoader);
               return null;
            }
         });
      }
   }
   
   static Boolean getBoolean(final String propName, final Boolean defaultValue)
   {
      SecurityManager sm = System.getSecurityManager();
      if (sm == null)
      {
         String s = System.getProperty(propName);
         return (s != null) ? Boolean.valueOf(s) : defaultValue;
      }
      else
      {
         return AccessController.doPrivileged(new PrivilegedAction<Boolean>()
         {
            public Boolean run()
            {
               String s = getSystemProperty(propName, null);
               return (s != null) ? Boolean.valueOf(s) : defaultValue;
            }
         });
      }
   }
   
   static boolean getBoolean(final String propName)
   {
      SecurityManager sm = System.getSecurityManager();
      if (sm == null)
      {
         return Boolean.getBoolean(propName);
      }
      else
      {
         return AccessController.doPrivileged(new PrivilegedAction<Boolean>()
         {
            public Boolean run()
            {
               return Boolean.getBoolean(propName);
            }
         });
      }
   }
   
   static Long getLong(final String propName, final Long defaultValue)
   {
      SecurityManager sm = System.getSecurityManager();
      if (sm == null)
      {
         return Long.getLong(propName, defaultValue);
      }
      else
      {
         return AccessController.doPrivileged(new PrivilegedAction<Long>()
         {
            public Long run()
            {
               return Long.getLong(propName, defaultValue);
            }
         });
      }
   }
   
   static Integer getInteger(final String propName, final Integer defaultValue)
   {
      SecurityManager sm = System.getSecurityManager();
      if (sm == null)
      {
         return Integer.getInteger(propName, defaultValue);
      }
      else
      {
         return AccessController.doPrivileged(new PrivilegedAction<Integer>()
         {
            public Integer run()
            {
               return Integer.getInteger(propName, defaultValue);
            }
         });
      }
   }
   
   static Integer getInteger(final String propName)
   {
      SecurityManager sm = System.getSecurityManager();
      if (sm == null)
      {
         return Integer.getInteger(propName);
      }
      else
      {
         return AccessController.doPrivileged(new PrivilegedAction<Integer>()
         {
            public Integer run()
            {
               return Integer.getInteger(propName);
            }
         });
      }
   }
   
   /**
    * Load a class using the provided classloader
    * 
    * @param name
    * @return
    * @throws PrivilegedActionException
    */
   static Class<?> loadClass(final ClassLoader cl, final String name) throws PrivilegedActionException, ClassNotFoundException
   {
      SecurityManager sm = System.getSecurityManager();
      if (sm == null)
      {
         return cl.loadClass(name);
      }
      else
      {
         return AccessController.doPrivileged(new PrivilegedExceptionAction<Class<?>>() {
            public Class<?> run() throws PrivilegedActionException
            {
               try
               {
                  return cl.loadClass(name);
               }
               catch (Exception e)
               {
                  throw new PrivilegedActionException(e);
               }
            }
         });
      }
   }
   
   /**
    * Return the current value of the specified system property
    * 
    * @param name
    * @param defaultValue
    * @return
    */
   static String getSystemProperty(final String name, final String defaultValue)
   {
      PrivilegedAction<String> action = new PrivilegedAction<String>()
      {
         public String run()
         {
            return System.getProperty(name, defaultValue);
         }
      };
      return AccessController.doPrivileged(action);
   }
   
   static DelegateClassLoader createDelegateClassLoader(final ClassLoader clientClassLoader, final ClassLoader origClassLoader)
   {
      SecurityManager sm = System.getSecurityManager();
      if (sm == null)
      {
         return new DelegateClassLoader(clientClassLoader, origClassLoader);
      }
      else
      {
         return AccessController.doPrivileged(new PrivilegedAction<DelegateClassLoader>()
         {
            public DelegateClassLoader run()
            {
               return new DelegateClassLoader(clientClassLoader, origClassLoader);
            }
         });
      }
   }

}
