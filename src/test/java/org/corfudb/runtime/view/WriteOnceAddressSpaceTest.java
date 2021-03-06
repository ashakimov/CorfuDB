package org.corfudb.runtime.view;

import org.corfudb.runtime.CorfuDBRuntime;
import org.corfudb.runtime.OverwriteException;
import org.corfudb.runtime.UnwrittenException;
import org.corfudb.runtime.smr.Pair;
import org.junit.Before;
import org.junit.Test;

import static com.github.marschall.junitlambda.LambdaAssert.assertRaises;
import static org.junit.Assert.assertEquals;

/**
 * Created by mwei on 4/30/15.
 */
public class WriteOnceAddressSpaceTest extends IWriteOnceAddressSpaceTest {

    @Override
    public IWriteOnceAddressSpace getAddressSpace()
    {
        CorfuDBRuntime cdr = CorfuDBRuntime.createRuntime("memory");
        ConfigurationMaster cm = new ConfigurationMaster(cdr);
        cm.resetAll();
        return new WriteOnceAddressSpace(cdr);
    }

}
