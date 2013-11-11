/* test jni api version */

package org.infinispan.client.hotrod.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.infinispan.commons.marshall.Marshaller;
import org.infinispan.commons.util.Util;
import org.infinispan.client.hotrod.Flag;
import org.infinispan.client.hotrod.MetadataValue;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.VersionedValue;
import org.infinispan.client.hotrod.impl.MetadataValueImpl;
import org.infinispan.client.hotrod.impl.VersionedValueImpl;
import org.infinispan.client.hotrod.jni.Hotrod;
import org.infinispan.client.hotrod.jni.MapReturn;
import org.infinispan.client.hotrod.jni.MetadataPairReturn;
import org.infinispan.client.hotrod.jni.RelayBytes;
import org.infinispan.client.hotrod.jni.RemoteCache_jb_jb;
import org.infinispan.client.hotrod.jni.VectorReturn;
import org.infinispan.client.hotrod.jni.VersionPairReturn;

import static org.infinispan.client.hotrod.jni.JniHelper.*;

public class RemoteCacheImpl<K, V> implements RemoteCache<K, V> {

    private Marshaller marshaller;
    private RemoteCache_jb_jb jniRemoteCache;

    public RemoteCacheImpl(RemoteCacheManager manager, String name) {
        this.marshaller = manager.getMarshaller();
        if (name == null) {
            this.jniRemoteCache = Hotrod.getJniRelayCache(manager.getJniManager());
        } else {
            this.jniRemoteCache = Hotrod.getJniRelayNamedCache(manager.getJniManager(), name);
        }
    }

    @Override
    public V put(K k, V v) {
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.put(rbs[0], rbs[1]);
            }
        }, k, v);
    }

    @Override
    public V put(K k, V v, long lifespan, TimeUnit unit) {
        final long l = lifespan;
        final TimeUnit lu = unit;
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.put(rbs[0], rbs[1], BigInteger.valueOf(l), timeunitToSwig(lu));
            }
        }, k, v);
    }

    @Override
    public V put(K k, V v, long lifespan, TimeUnit lifespanUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        final long l = lifespan;
        final TimeUnit lu = lifespanUnit;
        final long m = maxIdleTime;
        final TimeUnit mu = maxIdleTimeUnit;
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.put(rbs[0], rbs[1], BigInteger.valueOf(l), timeunitToSwig(lu),
                        BigInteger.valueOf(m), timeunitToSwig(mu));
            }
        }, k, v);
    }

    @Override
    public V get(K k) {
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.get(rbs[0]);
            }
        }, k);
    }

    @Override
    public boolean containsKey(K k) {
        Boolean b = relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.containsKey(rbs[0]);
            }
        }, k);
        return b;
    }

    @Override
    public boolean containsValue(V v) {
        Boolean b = relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.containsValue(rbs[0]);
            }
        }, v);
        return b;
    }

    @Override
    public V remove(K k) {
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.remove(rbs[0]);
            }
        }, k);
    }

    @Override
    public V replace(K k, V v) {
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.replace(rbs[0], rbs[1]);
            }
        }, k, v);
    }

    @Override
    public V replace(K k, V v, long lifespan, TimeUnit unit) {
        final long l = lifespan;
        final TimeUnit lu = unit;
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.replace(rbs[0], rbs[1], BigInteger.valueOf(l), timeunitToSwig(lu));
            }
        }, k, v);
    }

    @Override
    public V replace(K k, V v, long lifespan, TimeUnit lifespanUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        final long l = lifespan;
        final TimeUnit lu = lifespanUnit;
        final long m = maxIdleTime;
        final TimeUnit mu = maxIdleTimeUnit;
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.replace(rbs[0], rbs[1], BigInteger.valueOf(l), timeunitToSwig(lu),
                        BigInteger.valueOf(m), timeunitToSwig(mu));
            }
        }, k, v);
    }

    @Override
    public V putIfAbsent(K k, V v) {
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.putIfAbsent(rbs[0], rbs[1]);
            }
        }, k, v);
    }

    @Override
    public V putIfAbsent(K k, V v, long lifespan, TimeUnit unit) {
        final long l = lifespan;
        final TimeUnit lu = unit;
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.putIfAbsent(rbs[0], rbs[1], BigInteger.valueOf(l), timeunitToSwig(lu));
            }
        }, k, v);
    }

    @Override
    public V putIfAbsent(K k, V v, long lifespan, TimeUnit lifespanUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        final long l = lifespan;
        final TimeUnit lu = lifespanUnit;
        final long m = maxIdleTime;
        final TimeUnit mu = maxIdleTimeUnit;
        return relayedInvoker(new RelayedMethod() {
            @Override
            public Object invoke(RelayBytes... rbs) {
                return jniRemoteCache.putIfAbsent(rbs[0], rbs[1], BigInteger.valueOf(l), timeunitToSwig(lu),
                        BigInteger.valueOf(m), timeunitToSwig(mu));
            }
        }, k, v);
    }

    @Override
    public void clear() {
        if (jniRemoteCache != null) {
            jniRemoteCache.clear();
        }
    }

    @Override
    public boolean isEmpty() {
        return jniRemoteCache.isEmpty();
    }

    @Override
    public VersionedValue<V> getVersioned(K k) {
        if (jniRemoteCache == null) {
            return null;
        }

        final VersionPairReturn versionPair = relayedInvoker(new RelayedMethod() {
                @Override
                public Object invoke(RelayBytes... rbs) {
                    return jniRemoteCache.getWithVersion(rbs[0]);
                }
            }, k);

        if (versionPair.getFirst() == null || Hotrod.isNull(versionPair.getFirst())) {
            return null;
        }

        V value = relayedInvoker(new RelayedMethod() {
                @Override
                public Object invoke(RelayBytes... rbs) {
                    return Hotrod.dereference(versionPair.getFirst());
                }
            });

        if (value == null) {
            return null;
        }

        return new VersionedValueImpl(versionPair.getSecond().getVersion(), value);
    }

    @Override
    public MetadataValue<V> getWithMetadata(K k) {
        if (jniRemoteCache == null) {
            return null;
        }

        final MetadataPairReturn metadataPair = relayedInvoker(new RelayedMethod() {
               @Override
               public Object invoke(RelayBytes... rbs) {
                   return jniRemoteCache.getWithMetadata(rbs[0]);
               }
            }, k);

        if (metadataPair == null || metadataPair.getFirst() == null || Hotrod.isNull(metadataPair.getFirst())) {
            return null;
        }

       V value = relayedInvoker(new RelayedMethod() {
               @Override
               public Object invoke(RelayBytes... rbs) {
                   return Hotrod.dereference(metadataPair.getFirst());
               }
           });

        org.infinispan.client.hotrod.jni.MetadataValue metadata = metadataPair.getSecond();
        return new MetadataValueImpl(metadata.getCreated(), metadata.getLifespan(), metadata.getLastUsed(),
                metadata.getMaxIdle(), metadata.getVersion(), value);
    }

    @Override
    public Map<K, V> getBulk(int size) {
        if (jniRemoteCache == null) {
            return null;
        }

        final MapReturn mapReturn = jniRemoteCache.getBulk(size);
        final VectorReturn vectorReturn = Hotrod.keySet(mapReturn);

        Map<K, V> result = new HashMap<K, V>();
        for (int i = 0; i < vectorReturn.size(); i++) {
            final int index = i;
            K key = relayedInvoker(new RelayedMethod() {
                    @Override
                    public Object invoke(RelayBytes... rbs) {
                        return Hotrod.dereference(vectorReturn.get(index));
                    }
                });
            V value = relayedInvoker(new RelayedMethod() {
                    @Override
                    public Object invoke(RelayBytes... rbs) {
                        return Hotrod.dereference(mapReturn.get(vectorReturn.get(index)));
                    }
                });

            result.put(key, value);
        }

        return result;
    }

    @Override
    public RemoteCache<K, V> withFlags(Flag... flags) {
        int result = 0;
        if (flags != null) {
            for (Flag flag : flags) {
                result |= flag.getFlagInt();
            }
        }
        jniRemoteCache.withFlags(org.infinispan.client.hotrod.jni.Flag.swigToEnum(result));
        return this;
    }

    private <R> R relayedInvoker(RelayedMethod m, Object... o) {
        RelayBytes rb[] = new RelayBytes[o.length];
        byte bytes[][] = new byte[o.length][];
        try {
            for (int i = 0; i < o.length; i++) {
                rb[i] = new RelayBytes();
                bytes[i] = marshaller.objectToByteBuffer(o[i]);
                setJvmBytes(rb[i], bytes[i]);
            }
            Object ret = m.invoke(rb);
            if (ret == null) {
                return null;
            } else if (ret instanceof RelayBytes) {
                RelayBytes retrb = (RelayBytes) ret;
                byte[] jcopy = new byte[(int) retrb.getLength()];
                readNative(retrb, jcopy);
                try {
                    return (R) marshaller.objectFromByteBuffer(jcopy);
                } finally {
                    dispose(retrb);
                }
            } else {
                return (R) ret;
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        } finally {
            for (int i = 0; i < o.length; i++) {
                releaseJvmBytes(rb[i], bytes[i]);
            }
        }
    }

    public interface RelayedMethod {
        Object invoke(RelayBytes... rbs);
    }
}
