package io.github.zhangsen.melon.core.net.packet;

import io.netty.buffer.ByteBuf;

public class ByteBufPacket {

    private int packetId;


    private ByteBuf packetBody;

    public int getPacketId() {
        return packetId;
    }

    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }

    public ByteBuf getPacketBody() {
        return packetBody;
    }

    public void setPacketBody(ByteBuf packetBody) {
        this.packetBody = packetBody;
    }
}
