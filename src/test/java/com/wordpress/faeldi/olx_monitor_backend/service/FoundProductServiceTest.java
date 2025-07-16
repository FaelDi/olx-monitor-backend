package com.wordpress.faeldi.olx_monitor_backend.service;

import com.wordpress.faeldi.olx_monitor_backend.dto.FoundProductRequest;
import com.wordpress.faeldi.olx_monitor_backend.entity.*;
import com.wordpress.faeldi.olx_monitor_backend.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class FoundProductServiceTest {

    @Mock
    private FoundProductRepository foundRepo;
    @Mock
    private ProductSearchRepository searchRepo;
    @Mock
    private NotificationRepository notificationRepo;
    @Mock
    private TelegramService telegramService;

    private FoundProductService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new FoundProductService(foundRepo, searchRepo, notificationRepo, telegramService);
    }

    private FoundProductRequest createRequest(UUID searchId) {
        FoundProductRequest req = new FoundProductRequest();
        req.setSearchId(searchId);
        req.setTitle("title");
        req.setLink("link");
        req.setPrice(5.0);
        req.setPostedAt(LocalDateTime.now());
        return req;
    }

    @Test
    void doNothingWhenAlreadyExists() {
        FoundProductRequest req = createRequest(UUID.randomUUID());
        when(foundRepo.findByLink("link")).thenReturn(Optional.of(new FoundProduct()));
        service.registerFoundProduct(req);
        verify(searchRepo, never()).findById(any());
    }

    @Test
    void creditPlanWithoutCreditsSkipsNotification() {
        UUID searchId = UUID.randomUUID();
        FoundProductRequest req = createRequest(searchId);
        when(foundRepo.findByLink("link")).thenReturn(Optional.empty());
        ProductSearch search = new ProductSearch();
        search.setId(searchId);
        User user = new User();
        user.setPlanType(PlanType.CREDITS);
        user.setCredits(0);
        search.setUser(user);
        when(searchRepo.findById(searchId)).thenReturn(Optional.of(search));

        service.registerFoundProduct(req);

        verify(foundRepo).save(any(FoundProduct.class));
        verify(telegramService, never()).sendMessage(any(), any());
        verify(notificationRepo, never()).save(any());
    }

    @Test
    void registerAndNotify() {
        UUID searchId = UUID.randomUUID();
        FoundProductRequest req = createRequest(searchId);
        when(foundRepo.findByLink("link")).thenReturn(Optional.empty());
        ProductSearch search = new ProductSearch();
        search.setId(searchId);
        User user = new User();
        user.setPlanType(PlanType.CREDITS);
        user.setCredits(2);
        user.setTelegramChatId(1L);
        search.setUser(user);
        when(searchRepo.findById(searchId)).thenReturn(Optional.of(search));
        FoundProduct savedProduct = new FoundProduct();
        when(foundRepo.save(any(FoundProduct.class))).thenReturn(savedProduct);
        Notification savedNotif = new Notification();
        when(notificationRepo.save(any(Notification.class))).thenReturn(savedNotif);

        service.registerFoundProduct(req);

        verify(foundRepo).save(any(FoundProduct.class));
        verify(telegramService).sendMessage(eq(1L), contains("link"));
        verify(notificationRepo).save(any(Notification.class));
        assertEquals(1, user.getCredits());
        assertTrue(savedProduct.isSentToUser());
    }
}
