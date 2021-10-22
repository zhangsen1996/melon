package io.github.zhangsen.melon.core.common.packet.impl;

import io.github.zhangsen.melon.core.common.packet.Packet;
import io.netty.buffer.ByteBuf;

public class ByteBufPacket implements Packet {

    private short packetId;


    private ByteBuf packetBody;

    @Override
    public short getPacketId() {
        return packetId;
    }

    public void setPacketId(short packetId) {
        this.packetId = packetId;
    }

    public ByteBuf getPacketBody() {
        return packetBody;
    }

    public void setPacketBody(ByteBuf packetBody) {
        this.packetBody = packetBody;
    }
}
