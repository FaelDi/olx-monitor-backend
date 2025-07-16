package com.example.saasmonitor.service;

import com.example.saasmonitor.dto.ProductSearchRequest;
import com.example.saasmonitor.entity.ProductSearch;
import com.example.saasmonitor.entity.User;
import com.example.saasmonitor.repository.ProductSearchRepository;
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
