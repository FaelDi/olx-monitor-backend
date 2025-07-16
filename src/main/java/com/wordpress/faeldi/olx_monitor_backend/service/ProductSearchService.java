package com.wordpress.faeldi.olx_monitor_backend.service;

import com.wordpress.faeldi.olx_monitor_backend.dto.ProductSearchRequest;
import com.wordpress.faeldi.olx_monitor_backend.entity.ProductSearch;
import com.wordpress.faeldi.olx_monitor_backend.entity.User;
import com.wordpress.faeldi.olx_monitor_backend.repository.ProductSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductSearchService {
    private final ProductSearchRepository repository;

    public ProductSearchService(ProductSearchRepository repository) {
        this.repository = repository;
    }

    public ProductSearch create(User user, ProductSearchRequest request) {
        ProductSearch ps = new ProductSearch();
        ps.setUser(user);
        ps.setQuery(request.getQuery());
        ps.setFrequencyMinutes(request.getFrequencyMinutes());
        return repository.save(ps);
    }

    public List<ProductSearch> list(User user) {
        return repository.findByUserId(user.getId());
    }

    public void delete(UUID id, User user) {
        repository.findById(id).filter(ps -> ps.getUser().getId().equals(user.getId()))
                .ifPresent(repository::delete);
    }
}
