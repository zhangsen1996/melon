package io.github.zhangsen.melon.core.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultSessionManager implements ISessionManager{
    public static final AttributeKey<ISession> MY_SESSION = AttributeKey.valueOf("defaultSession");

    private Map<String,ISession> id2Session = new ConcurrentHashMap<>();

    private ChannelGroup globalChannelGroup=new DefaultChannelGroup(new DefaultEventExecutor());
    private final AtomicInteger sessionSequenceIdInc= new AtomicInteger(1);


    public void channelActive(Channel channel){
        DefaultSession session = new DefaultSession(getSessionSequenceId(),channel);
        Attribute<ISession> attr = channel.attr(MY_SESSION);
        attr.set(session);
        globalChannelGroup.add(channel);
    }

    public ISession getSession(Channel channel){
        Attribute<ISession> attr = channel.attr(MY_SESSION);
        return attr.get();
    }

    public void channelInactive(Channel channel){
        globalChannelGroup.remove(channel);
    }

    public int getSessionSequenceId(){
        return sessionSequenceIdInc.getAndIncrement();
    }
}
