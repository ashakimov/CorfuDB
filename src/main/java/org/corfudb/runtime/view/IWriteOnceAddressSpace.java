/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.corfudb.runtime.view;

import org.corfudb.infrastructure.thrift.Hints;
import org.corfudb.runtime.OutOfSpaceException;
import org.corfudb.runtime.OverwriteException;
import org.corfudb.runtime.TrimmedException;
import org.corfudb.runtime.UnwrittenException;
import org.corfudb.runtime.smr.MultiCommand;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

/**
 * This interface represents a view on write-once address spaces.
 */

public interface IWriteOnceAddressSpace {
    void write(long address, Serializable s)
    throws IOException, OverwriteException, TrimmedException, OutOfSpaceException;

    void write(long address, byte[] data)
    throws OverwriteException, TrimmedException, OutOfSpaceException;

    byte[] read(long address)
    throws UnwrittenException, TrimmedException;

    Object readObject(long address)
    throws UnwrittenException, TrimmedException, ClassNotFoundException, IOException;

    default Hints readHints(long address) throws UnwrittenException, TrimmedException {
        throw new UnsupportedOperationException("This address space doesn't support hints");
    }

    default void setHintsNext(long address, UUID stream, long nextOffset) throws UnwrittenException, TrimmedException {
        throw new UnsupportedOperationException("This address space doesn't support hints");
    }

    default void setHintsTxDec(long address, boolean dec) throws UnwrittenException, TrimmedException {
        throw new UnsupportedOperationException("This address space doesn't support hints");
    }

    default void setHintsFlatTxn(long address, MultiCommand flattedTxn) throws UnwrittenException, TrimmedException, IOException {
        throw new UnsupportedOperationException("This address space doesn't support hints");
    }
}

