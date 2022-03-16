package io.github.zhangsen.melon.core.session;

import io.github.zhangsen.melon.core.net.packet.AbstractPacket;
import io.netty.channel.Channel;

public class DefaultSession implements ISession{

    private Channel channel;

    public DefaultSession(int sequenceId, Channel channel) {
        this.sequenceId = sequenceId;
        this.channel = channel;
    }


    private int sequenceId;

    @Override
    public int getSequenceId() {
        return sequenceId;
    }

    @Override
    public int getId() {
        return sequenceId;
    }

    @Override
    public void sendPacket(AbstractPacket packet) {
        channel.writeAndFlush(packet);
    }
}
