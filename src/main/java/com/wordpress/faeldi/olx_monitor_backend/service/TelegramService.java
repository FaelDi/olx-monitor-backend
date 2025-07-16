package com.wordpress.faeldi.olx_monitor_backend.service;

import org.springframework.stereotype.Service;

@Service
public class TelegramService {
    public void sendMessage(Long chatId, String message) {
        // placeholder for real Telegram API integration
        System.out.println("Sending to " + chatId + ": " + message);
    }
}
