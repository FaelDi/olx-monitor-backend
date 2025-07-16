package com.wordpress.faeldi.olx_monitor_backend.service;

import org.junit.jupiter.api.Test;

class TelegramServiceTest {

    @Test
    void sendMessageDoesNotThrow() {
        new TelegramService().sendMessage(1L, "msg");
    }
}
