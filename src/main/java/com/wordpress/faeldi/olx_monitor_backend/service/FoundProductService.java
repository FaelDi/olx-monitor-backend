package com.wordpress.faeldi.olx_monitor_backend.service;

import com.wordpress.faeldi.olx_monitor_backend.dto.FoundProductRequest;
import com.wordpress.faeldi.olx_monitor_backend.entity.*;
import com.wordpress.faeldi.olx_monitor_backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FoundProductService {

    private final FoundProductRepository foundRepository;
    private final ProductSearchRepository searchRepository;
    private final NotificationRepository notificationRepository;
    private final TelegramService telegramService;

    public FoundProductService(FoundProductRepository foundRepository,
                               ProductSearchRepository searchRepository,
                               NotificationRepository notificationRepository,
                               TelegramService telegramService) {
        this.foundRepository = foundRepository;
        this.searchRepository = searchRepository;
        this.notificationRepository = notificationRepository;
        this.telegramService = telegramService;
    }

    @Transactional
    public void registerFoundProduct(FoundProductRequest request) {
        if (foundRepository.findByLink(request.getLink()).isPresent()) {
            return;
        }
        ProductSearch search = searchRepository.findById(request.getSearchId()).orElseThrow();
        FoundProduct product = new FoundProduct();
        product.setSearch(search);
        product.setTitle(request.getTitle());
        product.setLink(request.getLink());
        product.setPrice(request.getPrice());
        product.setPostedAt(request.getPostedAt());
        foundRepository.save(product);

        User user = search.getUser();
        if (user.getPlanType() == PlanType.CREDITS) {
            if (user.getCredits() <= 0) {
                return;
            }
            user.setCredits(user.getCredits() - 1);
        }
        telegramService.sendMessage(user.getTelegramChatId(), product.getTitle() + " - " + product.getLink());
        product.setSentToUser(true);
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setProduct(product);
        notification.setSentAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }
}
