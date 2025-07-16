package com.wordpress.faeldi.olx_monitor_backend.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {
    private static final Logger logger = LoggerFactory.getLogger(StartupLogger.class);

    @PostConstruct
    public void logStartup() {
        logger.info("SaaS Monitor backend started");
    }
}
