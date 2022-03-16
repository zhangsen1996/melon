package io.github.zhangsen.melon.core.net.packet;

import io.github.zhangsen.melon.core.session.ISession;

public abstract class AbstractPacket {

    public abstract void process(ISession session);
}
