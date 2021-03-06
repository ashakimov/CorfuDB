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
package org.corfudb.runtime.smr.legacy;

import org.corfudb.runtime.stream.ITimestamp;

import java.io.Serializable;
import java.util.UUID;

public class CorfuDBObjectCommand implements Serializable
{
    Exception E=null;
    Object retval=null;
    Serializable readsummary=null;
    ITimestamp appliedtimestamp=ITimestamp.getInvalidTimestamp();
    UUID txid=null;
    public Object getReturnValue()
    {
        return retval;
    }
    public void setReturnValue(Object obj)
    {
        retval = obj;
    }
    public UUID getTxid() { return txid; }
    public void setTxid(UUID l) { txid = l; }
    public void setException(Exception tE)
    {
        E = tE;
    }
    public Exception getException()
    {
        return E;
    }
    public void summarizeRead(Serializable R)
    {
        readsummary = R;
    }
    public Serializable getReadSummary()
    {
        return readsummary;
    }
    public void setTimestamp(ITimestamp ts)
    {
        appliedtimestamp = ts;
    }
    public ITimestamp getTimestamp()
    {
        return appliedtimestamp;
    }

    public String toString()
    {
        return appliedtimestamp + ":" + readsummary;
    }
}
