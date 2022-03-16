package io.github.zhangsen.melon.core.net.codec.protobuf;

import io.github.zhangsen.melon.core.net.codec.Codec;
import io.github.zhangsen.melon.core.net.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;

public class Protostuff implements Codec {


    @Override
    public AbstractPacket decode(int packetId, ByteBuf byteBuf, Class<? extends AbstractPacket> packetClass) throws Exception {
        return null;
    }

    @Override
    public void encode(Object msg, ByteBuf out) {

    }
}
