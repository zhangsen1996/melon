package io.github.zhangsen.melon.core.net.codec;

import io.github.zhangsen.melon.core.net.packet.PacketManager;
import io.github.zhangsen.melon.core.net.packet.AbstractPacket;
import io.github.zhangsen.melon.core.net.packet.ByteBufPacket;
import io.github.zhangsen.melon.core.session.ISession;
import io.github.zhangsen.melon.core.session.ISessionManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@ChannelHandler.Sharable
public class BaseMsgHandler extends MsgHandler {
    private final Logger logger = LoggerFactory.getLogger(BaseMsgHandler.class);
    private PacketManager packetManager;
    private ISessionManager sessionManager;
    public BaseMsgHandler(PacketManager packetManager,ISessionManager sessionManager) {
        this.packetManager = packetManager;
        this.sessionManager = sessionManager;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBufPacket) {
            ByteBufPacket byteBufPacket = ((ByteBufPacket) msg);
            int packetId = byteBufPacket.getPacketId();
            Class<? extends AbstractPacket> packetClass = packetManager.getPacketClass(packetId);
            if (Objects.isNull(packetClass)) {
                logger.error("packetClass is null !!!");
            }

            Codec packetCodec = packetManager.getPacketCodec(packetId);
            if (Objects.isNull(packetCodec)) {
                logger.error("packetCodec is null !!!");
            }
            ISession session = sessionManager.getSession(ctx.channel());
            AbstractPacket packet = packetCodec.decode(packetId, byteBufPacket.getPacketBody(), packetClass);
            packet.process(session);
        } else {
            logger.error("DefaultDispatcher packet not is ByteBufPacket !!!");
        }
    }
}
