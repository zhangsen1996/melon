package io.github.zhangsen.melon.core.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerInstanceManager {

    private final Map<String, ServerInstance> instanceMap = new ConcurrentHashMap<>();


    public void addServerInstance(ServerInstance serverInstance){
        instanceMap.put(serverInstance.getServerId(),serverInstance);
    }

    public ServerInstance getServerInstance(String serverId){
        return instanceMap.get(serverId);
    }

}
