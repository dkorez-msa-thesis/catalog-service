package dev.dkorez.msathesis.catalog.service;

import dev.dkorez.msathesis.catalog.grpc.ProductGrpcResponse;
import dev.dkorez.msathesis.catalog.mapper.ProductResponseMapper;
import dev.dkorez.msathesis.catalog.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProductServiceGrpc {
    private final ProductRepository productRepository;

    @Inject
    public ProductServiceGrpc(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductGrpcResponse> getProducts() {
        return productRepository.listAll().stream()
                .map(ProductResponseMapper::toGrpcResponse)
                .toList();
    }

    public ProductGrpcResponse getProduct(Long id) {
        return ProductResponseMapper.toGrpcResponse(productRepository.findById(id));
    }

    public List<ProductGrpcResponse> findByCategory(Long categoryId) {
        return productRepository.findByCategory(categoryId).stream()
                .map(ProductResponseMapper::toGrpcResponse)
                .toList();
    }

    public List<ProductGrpcResponse> findByBrand(String brand) {
        return productRepository.findByBrand(brand).stream()
                .map(ProductResponseMapper::toGrpcResponse)
                .toList();
    }

    public List<ProductGrpcResponse> findByTag(String tag) {
        return productRepository.findByTag(tag).stream()
                .map(ProductResponseMapper::toGrpcResponse)
                .toList();
    }

    public List<ProductGrpcResponse> searchProducts(String q) {
        return productRepository.findProducts(q).stream()
                .map(ProductResponseMapper::toGrpcResponse)
                .toList();
    }
}
