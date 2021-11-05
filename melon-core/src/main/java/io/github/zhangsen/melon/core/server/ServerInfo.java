package io.github.zhangsen.melon.core.server;

public class ServerInfo {
    private String serverId;

    private String serverType;

    private String serverAddress;

    private String serverPort;

    private String clientPort;

    public ServerInfo(String serverId, String serverType, String serverAddress, String serverPort) {
        this.serverId = serverId;
        this.serverType = serverType;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public ServerInfo(String serverId, String serverType, String serverAddress, String serverPort, String clientPort) {
        this.serverId = serverId;
        this.serverType = serverType;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.clientPort = clientPort;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }
}
