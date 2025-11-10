package com.dev.hrpayroll.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaConfig {

    @Autowired
    private EurekaInstanceConfigBean eurekaInstanceConfig;

    @EventListener
    public void onWebServerReady(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        eurekaInstanceConfig.setNonSecurePort(port);
        System.out.println("✅ Porta real registrada no Eureka: " + port);
    }
}
