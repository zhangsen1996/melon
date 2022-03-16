package io.github.zhangsen.melon.core.net.server;

import io.github.zhangsen.melon.core.net.codec.BaseProtocolDecoder;
import io.github.zhangsen.melon.core.net.codec.BaseProtocolEncoder;
import io.github.zhangsen.melon.core.net.codec.MsgHandler;
import io.github.zhangsen.melon.core.net.frontend.NettyFrontendServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer implements BackendServer{
    private Logger logger = LoggerFactory.getLogger(NettyFrontendServer.class);

    private int port;

    private MsgHandler msgHandler;

    public NettyServer(int port, MsgHandler msgHandler) {
        this.port = port;
        this.msgHandler = msgHandler;
    }

    public void start() {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new BaseProtocolDecoder()).addLast(msgHandler)
                                .addLast(new BaseProtocolEncoder());
                    }
                });
        try {
            serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            logger.warn("等待绑定端口被打断");
            throw new RuntimeException(e);
        }
        logger.info("NettyServer服务启动成功，端口：{}", port);
    }

    @Override
    public int getPort() {
        return port;
    }
}
