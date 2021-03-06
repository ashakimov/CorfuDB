package org.corfudb.runtime.view;

import org.corfudb.infrastructure.NettyLogUnitServer;
import org.corfudb.infrastructure.StreamingSequencerServer;
import org.corfudb.runtime.CorfuDBRuntime;
import org.corfudb.util.CorfuInfrastructureBuilder;
import org.corfudb.util.RandomOpenPort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.UUID;

/**
 * Created by mwei on 8/26/15.
 */
public class StreamAddressSpaceIT {

    CorfuInfrastructureBuilder infrastructure;
    CorfuDBRuntime runtime;
    ICorfuDBInstance instance;

    @Before
    public void setup()
    {
        infrastructure =
                CorfuInfrastructureBuilder.getBuilder()
                .addSequencer(RandomOpenPort.getOpenPort(), StreamingSequencerServer.class, "cdbss", null)
                .addLoggingUnit(RandomOpenPort.getOpenPort(), 0, NettyLogUnitServer.class, "nlu", null)
                .start(RandomOpenPort.getOpenPort());

        runtime = CorfuDBRuntime.getRuntime(infrastructure.getConfigString());
        instance = runtime.getLocalInstance();
    }

   @Test
    public void addressSpaceWriteRead()
            throws Exception
    {
        IStreamAddressSpace s = instance.getStreamAddressSpace();
        String test = "hello world";
        UUID id = UUID.randomUUID();
        s.write(0, Collections.singleton(id), test);
        IStreamAddressSpace.StreamAddressSpaceEntry entry = s.read(0);
        assertThat(entry.getGlobalIndex())
                .isEqualTo(0);
        assertThat(entry.getPayload())
                .isEqualTo(test);
        assertThat(entry.getStreams().contains(id))
                .isEqualTo(true);
    }

    @After
    public void tearDown()
    {
        infrastructure
                .shutdownAndWait();
    }
}
