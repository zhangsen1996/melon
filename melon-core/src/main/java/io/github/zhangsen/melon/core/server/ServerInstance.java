package io.github.zhangsen.melon.core.server;

public class ServerInstance {

    private ServerInfo serverInfo;

    private byte serverStatus;

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public byte getServerStatus() {
        return serverStatus;
    }

    public String getServerId() {
        return getServerInfo().getServerId();
    }
}

enum ServerStatus {
    online((byte) 1),
    offline((byte) 2);

    private final byte status;

    ServerStatus(byte status) {
        this.status =status;
    }

    public byte getStatus() {
        return status;
    }
}