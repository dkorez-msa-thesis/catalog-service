package dev.dkorez.msathesis.catalog.service;

import dev.dkorez.msathesis.catalog.mapper.ProductResponseMapper;
import dev.dkorez.msathesis.catalog.model.rest.ProductRestResponse;
import dev.dkorez.msathesis.catalog.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProductServiceRest {
    @Inject
    private ProductRepository productRepository;

    public List<ProductRestResponse> getProducts() {
        return productRepository.listAll().stream()
                .map(ProductResponseMapper::toRestResponse)
                .toList();
    }

    public ProductRestResponse getProduct(Long id) {
        return ProductResponseMapper.toRestResponse(productRepository.findById(id));
    }

    public List<ProductRestResponse> findByCategory(Long categoryId) {
        return productRepository.findByCategory(categoryId).stream()
                .map(ProductResponseMapper::toRestResponse)
                .toList();
    }

    public List<ProductRestResponse> findByBrand(String brand) {
        return productRepository.findByBrand(brand).stream()
                .map(ProductResponseMapper::toRestResponse)
                .toList();
    }

    public List<ProductRestResponse> findByTag(String tag) {
        return productRepository.findByTag(tag).stream()
                .map(ProductResponseMapper::toRestResponse)
                .toList();
    }

    public List<ProductRestResponse> searchProducts(String q) {
        return productRepository.findProducts(q).stream()
                .map(ProductResponseMapper::toRestResponse)
                .toList();
    }
}
