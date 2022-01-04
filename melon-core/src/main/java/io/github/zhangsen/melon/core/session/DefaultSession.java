package io.github.zhangsen.melon.core.session;

import io.netty.channel.Channel;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultSession implements ISession{



    public DefaultSession(int sequenceId, Channel channel) {
        this.sequenceId = sequenceId;
    }

    private String id;

    private int sequenceId;

    @Override
    public int getSequenceId() {
        return sequenceId;
    }

    @Override
    public String getId() {
        return id;
    }
}
