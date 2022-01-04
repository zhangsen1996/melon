package io.github.zhangsen.melon.core.codec;

import io.github.zhangsen.melon.core.common.packet.impl.ByteBufPacket;
import io.github.zhangsen.melon.core.route.RouteManager;
import io.github.zhangsen.melon.core.session.ISession;
import io.github.zhangsen.melon.core.session.SessionManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class DispatcherHandler extends ChannelInboundHandlerAdapter {

    private RouteManager routeManager;
    private SessionManager sessionManager;

    public DispatcherHandler(RouteManager routeManager, SessionManager sessionManager) {
        this.routeManager = routeManager;
        this.sessionManager = sessionManager;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof ByteBufPacket)){
            return;
        }
        ISession session = sessionManager.getSession(ctx.channel());
        ByteBufPacket byteBufPacket = (ByteBufPacket) msg;
        ByteBuf packetBody = byteBufPacket.getPacketBody();
        int routeId = byteBufPacket.getRouteId();
        routeManager.doRoute(session,routeId,packetBody);

    }



    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        sessionManager.channelActive(channel);

    }


}
