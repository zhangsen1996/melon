package io.github.zhangsen.melon.core.common.packet.impl;

import io.github.zhangsen.melon.core.common.packet.Packet;
import io.netty.buffer.ByteBuf;

public class ByteBufPacket implements Packet {

    private int routeId;


    private ByteBuf packetBody;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public ByteBuf getPacketBody() {
        return packetBody;
    }

    public void setPacketBody(ByteBuf packetBody) {
        this.packetBody = packetBody;
    }
}
