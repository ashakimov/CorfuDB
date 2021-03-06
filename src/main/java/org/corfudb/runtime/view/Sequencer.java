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

import org.corfudb.runtime.CorfuDBRuntime;
import org.corfudb.runtime.NetworkException;
import org.corfudb.runtime.protocols.sequencers.ISimpleSequencer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This view implements a simple sequencer.
 *
 * @author Michael Wei <mwei@cs.ucsd.edu>
 */

public class Sequencer {

    private CorfuDBRuntime client;
	private final Logger log = LoggerFactory.getLogger(Sequencer.class);

    public Sequencer(CorfuDBRuntime client)
    {
        this.client = client;
    }

    public long getNext()
    {
        while (true)
        {
            try {
                ISimpleSequencer sequencer = (ISimpleSequencer) client.getView().getSequencers().get(0);
                return sequencer.sequenceGetNext();
            }
            catch (NetworkException e)
            {
                log.warn("Unable to get next sequence, requesting new view.", e);
                client.invalidateViewAndWait(e);
            }
        }
    }

    public long getCurrent()
    {
        while (true)
        {
            try {
                ISimpleSequencer sequencer = (ISimpleSequencer) client.getView().getSequencers().get(0);
                return sequencer.sequenceGetCurrent();
            }
            catch (NetworkException e)
            {
                log.warn("Unable to get current sequence, requesting new view.", e);
                client.invalidateViewAndWait(e);
            }
        }
    }

}


