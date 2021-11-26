package io.github.zhangsen.melon.core.route;

import io.github.zhangsen.melon.core.common.packet.impl.ByteBufPacket;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

public interface RouteManager {

    void doRoute(int routeId, ByteBuf msg);


}
