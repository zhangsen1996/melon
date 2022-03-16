package io.github.zhangsen.melon.core.net.packet.reserved;

import io.github.zhangsen.melon.core.annotation.Packet;
import io.github.zhangsen.melon.core.net.packet.AbstractPacket;
import io.github.zhangsen.melon.core.session.ISession;

@Packet(packetId = 2)
public class HandshakeAckPacket extends AbstractPacket {
    @Override
    public void process(ISession session) {

    }
}
