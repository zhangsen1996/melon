package io.github.zhangsen.melon.core.codec;

import io.github.zhangsen.melon.core.common.packet.impl.ByteBufPacket;
import io.github.zhangsen.melon.core.route.RouteManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ByteBufPacketDecoder extends ChannelInboundHandlerAdapter {

    private RouteManager routeManager;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof ByteBufPacket)){
            return;
        }
        ByteBufPacket byteBufPacket = (ByteBufPacket) msg;
        ByteBuf packetBody = byteBufPacket.getPacketBody();
        int routeId = byteBufPacket.getRouteId();
        routeManager.doRoute(routeId,packetBody);

    }


}
