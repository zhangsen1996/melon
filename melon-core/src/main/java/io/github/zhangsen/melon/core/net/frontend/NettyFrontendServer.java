package io.github.zhangsen.melon.core.net.frontend;


import io.github.zhangsen.melon.core.codec.ProtocolDecoder;
import io.github.zhangsen.melon.core.codec.ProtocolEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyFrontendServer implements FrontendServer{




    public void start() {

        bossGroup = new NioEventLoopGroup(1);
        workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new ProtocolDecoder())
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