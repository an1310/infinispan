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

import java.util.concurrent.TimeUnit;

import org.infinispan.configuration.Builder;

/**
 * If configured all communications are synchronous, in that whenever a thread sends a message sent
 * over the wire, it blocks until it receives an acknowledgment from the recipient. SyncConfig is
 * mutually exclusive with the AsyncConfig.
 */
public class SyncConfigurationBuilder extends AbstractClusteringConfigurationChildBuilder implements Builder<SyncConfiguration> {

   private long replTimeout = TimeUnit.SECONDS.toMillis(15);
   private boolean useQuorum = false;

   protected SyncConfigurationBuilder(ClusteringConfigurationBuilder builder) {
      super(builder);
   }

   /**
    * This is the timeout used to wait for an acknowledgment when making a remote call, after which
    * the call is aborted and an exception is thrown.
    */
   public SyncConfigurationBuilder replTimeout(long l) {
      this.replTimeout = l;
      return this;
   }

   /**
    * This is the timeout used to wait for an acknowledgment when making a remote call, after which
    * the call is aborted and an exception is thrown.
    */
   public SyncConfigurationBuilder replTimeout(long l, TimeUnit unit) {
      return replTimeout(unit.toMillis(l));
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
   public SyncConfigurationBuilder useQuorum( boolean useQuorum ) {
      this.useQuorum = useQuorum;
      return this;
   }


   @Override
   public void validate() {

   }

   @Override
   public SyncConfiguration create() {
      return new SyncConfiguration(replTimeout, useQuorum);
   }

   @Override
   public SyncConfigurationBuilder read(SyncConfiguration template) {
      this.replTimeout = template.replTimeout();
      this.useQuorum = template.useQuorum();
      return this;
   }

   @Override
   public String toString() {
      return "SyncConfigurationBuilder{" +
            "replTimeout=" + replTimeout +
            "useQuorum=" + useQuorum +
            '}';
   }
}
