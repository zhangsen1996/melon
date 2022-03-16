package io.github.zhangsen.melon.spring.support;

import io.github.zhangsen.melon.core.net.frontend.FrontendServer;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

public class FrontendServerBootstrap {

    private FrontendServer frontendServer;

    public FrontendServerBootstrap(FrontendServer frontendServer) {
        this.frontendServer = frontendServer;
    }

    @EventListener
    public void start(ApplicationStartedEvent event) {
        frontendServer.start();
    }
}
