package io.github.zhangsen.melon.core.connector.impl;

import io.github.zhangsen.melon.core.codec.DispatcherHandler;
import io.github.zhangsen.melon.core.codec.ProtocolDecoder;
import io.github.zhangsen.melon.core.codec.ProtocolEncoder;
import io.github.zhangsen.melon.core.connector.Connector;
import io.github.zhangsen.melon.core.route.RouteManager;
import io.github.zhangsen.melon.core.session.SessionManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyConnector implements Connector {
    private final Logger logger = LoggerFactory.getLogger(NettyConnector.class);
    private RouteManager routeManager;
    private SessionManager sessionManager;
    private int port = 9999;

    public NettyConnector(RouteManager routeManager, SessionManager sessionManager) {
        this.routeManager = routeManager;
        this.sessionManager = sessionManager;
    }

    @Override
    public void start() {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        DispatcherHandler dispatcherHandler = new DispatcherHandler(routeManager,sessionManager);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new ProtocolDecoder()).addLast(dispatcherHandler)
                                .addLast(new ProtocolEncoder());
                    }
                });
        try {
            serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            logger.warn("等待绑定端口被打断");
            throw new RuntimeException(e);
        }
        logger.info("服务启动成功，端口：{}", port);
    }
}
