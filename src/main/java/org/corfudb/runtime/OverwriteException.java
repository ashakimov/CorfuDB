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

package org.corfudb.runtime;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * This exception is thrown whenever a write is attempted on a page
 * that already has been written to. (Applies to write once address spaces)
 */
@SuppressWarnings("serial")
public class OverwriteException extends IOException
{
    public long address;
    public ByteBuffer payload;
    public OverwriteException(String desc, long address, ByteBuffer payload)
    {
        super(desc + "[address=" + address + "]");
        this.address = address;
        this.payload = payload;
    }
}

