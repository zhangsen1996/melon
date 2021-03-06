package io.github.zhangsen.melon.core.net.frontend;


import io.github.zhangsen.melon.core.net.codec.BaseProtocolDecoder;
import io.github.zhangsen.melon.core.net.codec.BaseProtocolEncoder;
import io.github.zhangsen.melon.core.net.codec.MsgHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyFrontendServer implements FrontendServer{
    private Logger logger = LoggerFactory.getLogger(NettyFrontendServer.class);

    private int clientPort;

    private MsgHandler msgHandler;

    public NettyFrontendServer(int clientPort,MsgHandler msgHandler) {
        this.clientPort = clientPort;
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
            serverBootstrap.bind(clientPort).sync();
        } catch (InterruptedException e) {
            logger.warn("等待绑定端口被打断");
            throw new RuntimeException(e);
        }
        logger.info("NettyFrontendServer服务启动成功，端口：{}", clientPort);
    }

    public int getPort() {
        return clientPort;
    }

}
