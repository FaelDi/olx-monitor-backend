package com.wordpress.faeldi.olx_monitor_backend.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EntitiesTest {

    @Test
    void userGettersAndSetters() {
        User u = new User();
        UUID id = UUID.randomUUID();
        u.setId(id);
        u.setEmail("e");
        u.setPassword("p");
        u.setTelegramChatId(1L);
        u.setPlanType(PlanType.MONTHLY);
        u.setCredits(5);
        LocalDateTime exp = LocalDateTime.now();
        u.setSubscriptionExpiresAt(exp);

        assertEquals(id, u.getId());
        assertEquals("e", u.getEmail());
        assertEquals("p", u.getPassword());
        assertEquals(1L, u.getTelegramChatId());
        assertEquals(PlanType.MONTHLY, u.getPlanType());
        assertEquals(5, u.getCredits());
        assertEquals(exp, u.getSubscriptionExpiresAt());
    }

    @Test
    void productSearchGetters() {
        ProductSearch ps = new ProductSearch();
        UUID id = UUID.randomUUID();
        User u = new User();
        ps.setId(id);
        ps.setUser(u);
        ps.setQuery("q");
        ps.setActive(false);
        ps.setFrequencyMinutes(10);
        assertEquals(id, ps.getId());
        assertEquals(u, ps.getUser());
        assertEquals("q", ps.getQuery());
        assertFalse(ps.isActive());
        assertEquals(10, ps.getFrequencyMinutes());
    }

    @Test
    void foundProductGetters() {
        FoundProduct fp = new FoundProduct();
        UUID id = UUID.randomUUID();
        ProductSearch ps = new ProductSearch();
        LocalDateTime time = LocalDateTime.now();
        fp.setId(id);
        fp.setSearch(ps);
        fp.setTitle("t");
        fp.setLink("l");
        fp.setPrice(1.0);
        fp.setPostedAt(time);
        fp.setSentToUser(true);
        assertEquals(id, fp.getId());
        assertEquals(ps, fp.getSearch());
        assertEquals("t", fp.getTitle());
        assertEquals("l", fp.getLink());
        assertEquals(1.0, fp.getPrice());
        assertEquals(time, fp.getPostedAt());
        assertTrue(fp.isSentToUser());
    }

    @Test
    void paymentGetters() {
        Payment p = new Payment();
        UUID id = UUID.randomUUID();
        User u = new User();
        LocalDateTime time = LocalDateTime.now();
        p.setId(id);
        p.setUser(u);
        p.setAmount(1.0);
        p.setCreditsPurchased(2);
        p.setSubscriptionUntil(time);
        p.setPaymentMethod("m");
        assertEquals(id, p.getId());
        assertEquals(u, p.getUser());
        assertEquals(1.0, p.getAmount());
        assertEquals(2, p.getCreditsPurchased());
        assertEquals(time, p.getSubscriptionUntil());
        assertEquals("m", p.getPaymentMethod());
    }

    @Test
    void notificationGetters() {
        Notification n = new Notification();
        UUID id = UUID.randomUUID();
        User u = new User();
        FoundProduct p = new FoundProduct();
        LocalDateTime time = LocalDateTime.now();
        n.setId(id);
        n.setUser(u);
        n.setProduct(p);
        n.setSentAt(time);
        assertEquals(id, n.getId());
        assertEquals(u, n.getUser());
        assertEquals(p, n.getProduct());
        assertEquals(time, n.getSentAt());
    }
}
