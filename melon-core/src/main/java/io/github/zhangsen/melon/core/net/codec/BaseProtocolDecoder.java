package io.github.zhangsen.melon.core.net.codec;

import io.github.zhangsen.melon.core.common.Constant;
import io.github.zhangsen.melon.core.net.packet.ByteBufPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class BaseProtocolDecoder extends ByteToMessageDecoder {


    private short packetSize = 0;
    private byte readPhase = 0;//读取阶段
    private ByteBufPacket tempPacket = null;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        do {
            if (readPhase == 0) {
                if (in.readableBytes() < Constant.PACKET_HEAD_LENGTH) {
                    return;
                }
                readPhase = 1;
                tempPacket = new ByteBufPacket();
                tempPacket.setPacketId(in.readInt());
                packetSize = in.readShort();
            }
            if (readPhase == 1) {
                if (in.readableBytes() < packetSize) {
                    return;
                }
                ByteBuf packetBody = in.readBytes(packetSize);
                tempPacket.setPacketBody(packetBody);
                out.add(tempPacket);
                tempPacket = null;
                readPhase = 0;
            }

        } while (in.isReadable());

    }


}
