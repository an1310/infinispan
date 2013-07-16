/*
 * Copyright 2011 Red Hat, Inc. and/or its affiliates.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA
 */
package org.infinispan.configuration.cache;

/**
 * If configured all communications are synchronous, in that whenever a thread sends a message sent
 * over the wire, it blocks until it receives an acknowledgment from the recipient. SyncConfig is
 * mutually exclusive with the AsyncConfig.
 */
public class SyncConfiguration {

   private long replTimeout;
   private boolean useQuorum;


   SyncConfiguration(long replTimeout, boolean useQuorum) {
      this.replTimeout = replTimeout;
      this.useQuorum = useQuorum;
   }

   /**
    * This is the timeout used to wait for an acknowledgment when making a remote call, after which
    * the call is aborted and an exception is thrown.
    */
   public long replTimeout() {
      return replTimeout;
   }
   
   /**
    * This is the timeout used to wait for an acknowledgment when making a remote call, after which
    * the call is aborted and an exception is thrown.
    */
   public SyncConfiguration replTimeout(long l) {
      this.replTimeout = l;
      return this;
   }
   
   /**
    * This is a configuration option to only wait for the first response before returning from a sync call.
    * Note that if the call originates on the primary owner, the backup call is asynchronous.
    */
   public boolean useQuorum() {
      return this.useQuorum;
   }
   
   /**
    * This is a configuration option to only wait for the first response before returning from a sync call.
    * Note that if the call originates on the primary owner, the backup call is asynchronous.
    */
   public SyncConfiguration useQuorum( boolean useQuorum ) {
      this.useQuorum = useQuorum;
      return this;
   }
   
   @Override
   public String toString() {
      return "SyncConfiguration{" +
            "replTimeout=" + replTimeout +
            "useQuorum=" + useQuorum +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      SyncConfiguration that = (SyncConfiguration) o;

      if (replTimeout != that.replTimeout) return false;
      if (useQuorum != that.useQuorum) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = (int) (replTimeout ^ (replTimeout >>> 32));
      result = 31 * result + (useQuorum ? 1 : 0);
      return result;
   }
}
