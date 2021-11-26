package io.github.zhangsen.melon.core.route;

import io.github.zhangsen.melon.core.common.packet.impl.ByteBufPacket;
import io.netty.buffer.ByteBuf;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultRouteManager implements RouteManager{
    private static final Log logger = LogFactory.getLog(DefaultRouteManager.class);
    private static Map<Integer,MethodCaller> routeMap = new ConcurrentHashMap<>();

    @Override
    public void doRoute(int routeId, ByteBuf msg) {
        MethodCaller methodCaller = routeMap.get(routeId);
        if (methodCaller == null) {
            logger.error("not found route by Id:" + routeId);
            return;
        }
        methodCaller.call(msg);
    }
}
