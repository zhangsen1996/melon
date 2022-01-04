package io.github.zhangsen.melon.core.route;

import io.github.zhangsen.melon.core.session.ISession;
import io.netty.buffer.ByteBuf;

public interface RouteManager {

    void doRoute(ISession session,int routeId, ByteBuf msg);


}
