package org.infinispan.container.entries.metadata;

import org.infinispan.metadata.Metadata;
import org.infinispan.container.entries.ExpiryHelper;
import org.infinispan.container.entries.ImmortalCacheValue;
import org.infinispan.container.entries.InternalCacheEntry;
import org.infinispan.commons.io.UnsignedNumeric;
import org.infinispan.commons.marshall.AbstractExternalizer;
import org.infinispan.commons.util.Util;
import org.infinispan.marshall.core.Ids;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

/**
 * A transient cache value, to correspond with
 * {@link org.infinispan.container.entries.TransientCacheEntry} which is
 * {@link org.infinispan.container.entries.metadata.MetadataAware}
 *
 * @author Galder Zamarreño
 * @since 5.3
 */
public class MetadataTransientCacheValue extends ImmortalCacheValue implements MetadataAware {

   Metadata metadata;
   long lastUsed;

   public MetadataTransientCacheValue(Object value, Metadata metadata, long lastUsed) {
      super(value);
      this.metadata = metadata;
      this.lastUsed = lastUsed;
   }

   @Override
   public InternalCacheEntry toInternalCacheEntry(Object key) {
      return new MetadataTransientCacheEntry(key, value, metadata, lastUsed);
   }

   @Override
   public long getMaxIdle() {
      return metadata.maxIdle();
   }

   @Override
   public long getLastUsed() {
      return lastUsed;
   }

   @Override
   public final boolean isExpired(long now) {
      return ExpiryHelper.isExpiredTransient(metadata.maxIdle(), lastUsed, now);
   }

   @Override
   public final boolean isExpired() {
      return isExpired(System.currentTimeMillis());
   }

   @Override
   public boolean canExpire() {
      return true;
   }

   @Override
   public Metadata getMetadata() {
      return metadata;
   }

   @Override
   public void setMetadata(Metadata metadata) {
      this.metadata = metadata;
   }

   @Override
   public long getExpiryTime() {
      long maxIdle = metadata.maxIdle();
      return maxIdle > -1 ? lastUsed + maxIdle : -1;
   }

   public static class Externalizer extends AbstractExternalizer<MetadataTransientCacheValue> {
      @Override
      public void writeObject(ObjectOutput output, MetadataTransientCacheValue tcv) throws IOException {
         output.writeObject(tcv.value);
         output.writeObject(tcv.metadata);
         UnsignedNumeric.writeUnsignedLong(output, tcv.lastUsed);
      }

      @Override
      public MetadataTransientCacheValue readObject(ObjectInput input) throws IOException, ClassNotFoundException {
         Object v = input.readObject();
         Metadata metadata = (Metadata) input.readObject();
         long lastUsed = UnsignedNumeric.readUnsignedLong(input);
         return new MetadataTransientCacheValue(v, metadata, lastUsed);
      }

      @Override
      public Integer getId() {
         return Ids.METADATA_TRANSIENT_VALUE;
      }

      @Override
      public Set<Class<? extends MetadataTransientCacheValue>> getTypeClasses() {
         return Util.<Class<? extends MetadataTransientCacheValue>>asSet(MetadataTransientCacheValue.class);
      }
   }
}
