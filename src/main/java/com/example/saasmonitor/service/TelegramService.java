package com.example.saasmonitor.service;

import org.springframework.stereotype.Service;

@Service
public class TelegramService {
    public void sendMessage(Long chatId, String message) {
        // placeholder for real Telegram API integration
        System.out.println("Sending to " + chatId + ": " + message);
    }
}
