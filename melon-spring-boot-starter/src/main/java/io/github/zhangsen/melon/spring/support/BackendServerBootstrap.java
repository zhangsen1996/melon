package io.github.zhangsen.melon.spring.support;

import io.github.zhangsen.melon.core.net.server.BackendServer;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

public class BackendServerBootstrap {

    private BackendServer backendServer;

    public BackendServerBootstrap(BackendServer backendServer) {
        this.backendServer = backendServer;
    }

    @EventListener
    public void start(ApplicationStartedEvent event) {
        backendServer.start();
    }
}
