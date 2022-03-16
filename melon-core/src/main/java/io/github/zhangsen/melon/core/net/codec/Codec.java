package io.github.zhangsen.melon.core.net.codec;

import io.github.zhangsen.melon.core.net.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;

public interface Codec {

    public AbstractPacket decode(int packetId, ByteBuf byteBuf, Class<? extends AbstractPacket> packetClass) throws Exception;


    public void encode(Object msg, ByteBuf out);

}
