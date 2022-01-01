package com.werfen.inventory.features.product.service;

import com.werfen.inventory.features.product.model.Product;
import com.werfen.inventory.features.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Page<Product> getByFilter(Pageable pageable) {
        return productRepository.findAll(getPageableWithDefaultsSort(pageable));
    }

    private Pageable getPageableWithDefaultsSort(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSortOr(Sort.by("id").descending()));
    }


    public Product getProduct(Long productId) {
        return this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        return this.productRepository.findById(id)
                .map(existing -> {
                    product.setId(id);
                    return this.productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product removeProduct(Long id) {
        return this.productRepository.findById(id)
                .map(existing -> {
                    productRepository.delete(existing);
                    return existing;
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }
}
