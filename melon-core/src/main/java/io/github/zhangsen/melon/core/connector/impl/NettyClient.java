package io.github.zhangsen.melon.core.connector.impl;

import io.github.zhangsen.melon.core.codec.ProtocolDecoder;
import io.github.zhangsen.melon.core.codec.ProtocolEncoder;
import io.github.zhangsen.melon.core.connector.Client;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient implements Client {

    private Channel channel;

    private int port=8888;
    private String ip="127.0.0.1";

    @Override
    public void start() {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(io.netty.channel.Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ProtocolDecoder())
                                .addLast(new ProtocolEncoder());
                    }
                });
        try {
            io.netty.channel.Channel nettyChannel = bootstrap.connect(ip, port).sync().channel();
            nettyChannel.writeAndFlush(1);
            while (channel == null) {
                // 等待获取channel
                channel = nettyChannel;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
