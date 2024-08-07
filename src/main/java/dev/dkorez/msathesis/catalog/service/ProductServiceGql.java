package dev.dkorez.msathesis.catalog.service;

import dev.dkorez.msathesis.catalog.mapper.ProductResponseMapper;
import dev.dkorez.msathesis.catalog.model.gql.ProductGqlResponse;
import dev.dkorez.msathesis.catalog.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProductServiceGql {
    @Inject
    private ProductRepository productRepository;

    public List<ProductGqlResponse> getProducts() {
        return productRepository.listAll().stream()
                .map(ProductResponseMapper::toGraphqlResponse)
                .toList();
    }

    public ProductGqlResponse getProduct(Long id) {
        return ProductResponseMapper.toGraphqlResponse(productRepository.findById(id));
    }

    public List<ProductGqlResponse> findByCategory(Long categoryId) {
        return productRepository.findByCategory(categoryId).stream()
                .map(ProductResponseMapper::toGraphqlResponse)
                .toList();
    }

    public List<ProductGqlResponse> findByBrand(String brand) {
        return productRepository.findByBrand(brand).stream()
                .map(ProductResponseMapper::toGraphqlResponse)
                .toList();
    }

    public List<ProductGqlResponse> findByTag(String tag) {
        return productRepository.findByTag(tag).stream()
                .map(ProductResponseMapper::toGraphqlResponse)
                .toList();
    }

    public List<ProductGqlResponse> searchProducts(String q) {
        return productRepository.findProducts(q).stream()
                .map(ProductResponseMapper::toGraphqlResponse)
                .toList();
    }
}
