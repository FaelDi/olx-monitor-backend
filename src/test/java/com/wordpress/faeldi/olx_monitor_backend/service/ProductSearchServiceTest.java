package com.wordpress.faeldi.olx_monitor_backend.service;

import com.wordpress.faeldi.olx_monitor_backend.dto.ProductSearchRequest;
import com.wordpress.faeldi.olx_monitor_backend.entity.ProductSearch;
import com.wordpress.faeldi.olx_monitor_backend.entity.User;
import com.wordpress.faeldi.olx_monitor_backend.repository.ProductSearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductSearchServiceTest {

    @Mock
    private ProductSearchRepository repository;
    private ProductSearchService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new ProductSearchService(repository);
    }

    @Test
    void createSavesSearch() {
        User user = new User();
        ProductSearchRequest request = new ProductSearchRequest();
        request.setQuery("test");
        request.setFrequencyMinutes(10);
        ProductSearch saved = new ProductSearch();
        when(repository.save(any(ProductSearch.class))).thenReturn(saved);

        ProductSearch result = service.create(user, request);
        assertSame(saved, result);
        ArgumentCaptor<ProductSearch> captor = ArgumentCaptor.forClass(ProductSearch.class);
        verify(repository).save(captor.capture());
        ProductSearch ps = captor.getValue();
        assertEquals(user, ps.getUser());
        assertEquals("test", ps.getQuery());
        assertEquals(10, ps.getFrequencyMinutes());
    }

    @Test
    void listDelegatesToRepo() {
        User u = new User();
        u.setId(UUID.randomUUID());
        List<ProductSearch> list = List.of(new ProductSearch());
        when(repository.findByUserId(u.getId())).thenReturn(list);
        assertSame(list, service.list(u));
    }

    @Test
    void deleteOnlyIfOwner() {
        UUID id = UUID.randomUUID();
        User owner = new User();
        owner.setId(id);
        ProductSearch ps = new ProductSearch();
        ps.setUser(owner);
        when(repository.findById(id)).thenReturn(Optional.of(ps));
        service.delete(id, owner);
        verify(repository).delete(ps);
    }

    @Test
    void deleteWhenNotOwnerDoesNothing() {
        UUID id = UUID.randomUUID();
        User owner = new User();
        owner.setId(UUID.randomUUID());
        User other = new User();
        other.setId(UUID.randomUUID());
        ProductSearch ps = new ProductSearch();
        ps.setUser(owner);
        when(repository.findById(id)).thenReturn(Optional.of(ps));
        service.delete(id, other);
        verify(repository, never()).delete(any());
    }
}
