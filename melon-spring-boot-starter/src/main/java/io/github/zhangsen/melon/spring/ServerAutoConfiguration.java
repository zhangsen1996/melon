package io.github.zhangsen.melon.spring;

import io.github.zhangsen.melon.core.net.codec.BaseMsgHandler;
import io.github.zhangsen.melon.core.net.codec.MsgHandler;
import io.github.zhangsen.melon.core.net.frontend.NettyFrontendServer;
import io.github.zhangsen.melon.core.net.packet.PacketManager;
import io.github.zhangsen.melon.core.net.server.NettyServer;
import io.github.zhangsen.melon.core.session.DefaultSessionManager;
import io.github.zhangsen.melon.core.session.ISessionManager;
import io.github.zhangsen.melon.spring.config.SpringServerProperties;
import io.github.zhangsen.melon.spring.support.BackendServerBootstrap;
import io.github.zhangsen.melon.spring.support.FrontendServerBootstrap;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(SpringServerProperties.class)
public class ServerAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(BackendServerBootstrap.class)
    public BackendServerBootstrap backendServerBootstrap(SpringServerProperties springServerProperties,MsgHandler msgHandler) {
        BackendServerBootstrap backendServerBootstrap = new BackendServerBootstrap(new NettyServer(springServerProperties.getPort(),msgHandler));
        return backendServerBootstrap;
    }

    @Bean
    @ConditionalOnMissingBean(MsgHandler.class)
    public MsgHandler msgHandler(SpringServerProperties springServerProperties,ISessionManager sessionManager) {
        BaseMsgHandler baseMsgHandler = new BaseMsgHandler(new PacketManager(springServerProperties.getScanPackage()),sessionManager);
        return baseMsgHandler;
    }

    @Bean
    @ConditionalOnMissingBean(ISessionManager.class)
    public ISessionManager sessionManager(SpringServerProperties springServerProperties) {
        DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
        return defaultSessionManager;
    }



    @Bean
    @ConditionalOnMissingBean(FrontendServerBootstrap.class)
    public FrontendServerBootstrap frontendServerBootstrap(SpringServerProperties springServerProperties,MsgHandler msgHandler) {
        FrontendServerBootstrap frontendServerBootstrap = new FrontendServerBootstrap(new NettyFrontendServer(springServerProperties.getClientPort(),msgHandler));
        return frontendServerBootstrap;
    }
}
