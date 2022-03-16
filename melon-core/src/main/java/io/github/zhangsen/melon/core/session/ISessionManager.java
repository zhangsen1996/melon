package io.github.zhangsen.melon.core.session;

import io.netty.channel.Channel;

public interface ISessionManager {

    void channelActive(Channel channel);

    ISession getSession(Channel channel);

    void channelInactive(Channel channel);
}
