package org.corfudb.infrastructure.wireprotocol;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


/**
 * Created by mwei on 9/15/15.
 */
@Getter
@Setter
@NoArgsConstructor
public class NettyLogUnitReadRequestMsg extends NettyCorfuMsg {

    /** The address to read from */
    long address;
    UUID stream;
    long localAddress;

    public NettyLogUnitReadRequestMsg(long address, UUID stream, long localAddress)
    {
        this.msgType = NettyCorfuMsgType.READ_REQUEST;
        this.address = address;
        this.stream = stream;
        this.localAddress = localAddress;
    }
    /**
     * Serialize the message into the given bytebuffer.
     *
     * @param buffer The buffer to serialize to.
     */
    @Override
    public void serialize(ByteBuf buffer) {
        super.serialize(buffer);
        buffer.writeLong(address);
        if (address == -1L) {
            buffer.writeLong(stream.getMostSignificantBits());
            buffer.writeLong(stream.getLeastSignificantBits());
            buffer.writeLong(localAddress);
        }
    }

    /**
     * Parse the rest of the message from the buffer. Classes that extend NettyCorfuMsg
     * should parse their fields in this method.
     *
     * @param buffer
     */
    @Override
    public void fromBuffer(ByteBuf buffer) {
        super.fromBuffer(buffer);
        address = buffer.readLong();
        if (address == -1L) {
            stream = new UUID(buffer.readLong(), buffer.readLong());
            localAddress = buffer.readLong();
        }
    }
}
