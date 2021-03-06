package org.corfudb.runtime.collections;

import org.corfudb.runtime.smr.*;
import org.corfudb.runtime.stream.IStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by crossbach on 5/29/15.
 */
public class LPBTEntry<K extends Comparable<K>, V> implements ICorfuDBObject<TreeEntry<K,V>> {

    private static final Logger log = LoggerFactory.getLogger(LPBTEntry.class);

    /**
     * read the deleted flag on this entry
     *
     * @return
     */
    public Boolean readDeleted() {
        return accessorHelper((entry, opts) -> entry.deleted);
    }

    /**
     * apply a delete command
     *
     * @param b
     * @return
     */
    public Boolean writeDeleted(boolean b) {
        return mutatorAccessorHelper((entry, opts) -> {
            entry.deleted = b;
            return b;
        });
    }

    /**
     * read the key
     *
     * @return
     */
    public K readKey() {
        return accessorHelper((entry, opts) -> entry.key);
    }

    /**
     * write the key
     *
     * @param k
     * @return
     */
    public K writeKey(K k) {
        return mutatorAccessorHelper((entry, opts) -> {
            entry.key = k;
            return k;
        });
    }

    /**
     * read the value
     *
     * @return
     */
    public V readValue() {
        return accessorHelper((entry, opts) -> entry.value);
    }

    /**
     * write the value
     *
     * @param v
     * @return
     */
    public V writeValue(V v) {
        return mutatorAccessorHelper((entry, opts) -> {
            entry.value = v;
            return v;
        });
    }

    /**
     * read the next pointer
     *
     * @return
     */
    public UUID readNext() {
        return accessorHelper((entry, opts) -> entry.oidnext);
    }

    /**
     * write the next pointer
     *
     * @param _next
     * @return
     */
    public UUID writeNext(UUID _next) {
        return (UUID) mutatorAccessorHelper((entry, opts) -> {
            entry.oidnext = _next;
            return _next;
        });
    }

    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString() {
        return accessorHelper((entry, opts) -> {
            StringBuilder sb = new StringBuilder();
            if (entry.deleted)
                sb.append("DEL: ");
            sb.append("E");
            sb.append(getStreamID());
            sb.append(":[k=");
            sb.append(entry.key);
            sb.append(", v=");
            sb.append(entry.value);
            sb.append(", n=");
            sb.append(entry.oidnext);
            sb.append("]");
            return sb.toString();
        });
    }
}
