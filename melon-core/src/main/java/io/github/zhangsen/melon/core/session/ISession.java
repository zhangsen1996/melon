package io.github.zhangsen.melon.core.session;

import io.github.zhangsen.melon.core.net.packet.AbstractPacket;

public interface ISession {

    int getSequenceId();
    int getId();

    void sendPacket(AbstractPacket packet);
}
