package dev.dkorez.msathesis.catalog.grpc;

import com.google.protobuf.Empty;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

import java.util.List;

@GrpcService
public class ProductServiceGrpcImpl implements ProductServiceGrpc {
    @Inject
    private dev.dkorez.msathesis.catalog.service.ProductServiceGrpc productService;

    @Override
    @Blocking
    public Uni<ProductListGrpcResponse> findProducts(SearchProductGrpcRequest request) {
        return Uni.createFrom().item(() ->
                createProductListResponse(productService.searchProducts(request.getQ())));
    }

    @Override
    @Blocking
    public Uni<ProductListGrpcResponse> listAllProducts(Empty request) {
        return Uni.createFrom().item(() ->
                createProductListResponse(productService.getProducts()));
    }

    @Override
    @Blocking
    public Uni<ProductGrpcResponse> getProductById(ProductGrpcRequest request) {
        return Uni.createFrom().item(() ->
                productService.getProduct(request.getId()));
    }

    @Override
    @Blocking
    public Uni<ProductListGrpcResponse> listProductsByCategory(ProductsByCategoryGrpcRequest request) {
        return Uni.createFrom().item(() ->
                createProductListResponse(productService.findByCategory(request.getCategoryId())));
    }

    @Override
    public Uni<ProductListGrpcResponse> listProductsByBrand(ProductsByBrandGrpcRequest request) {
        return Uni.createFrom().item(() ->
                createProductListResponse(productService.findByBrand(request.getBrand())));
    }

    @Override
    public Uni<ProductListGrpcResponse> listProductsByTags(ProductsByTagsGrpcRequest request) {
        return Uni.createFrom().item(() ->
                createProductListResponse(productService.findByTag(request.getTag())));
    }

    private ProductListGrpcResponse createProductListResponse(List<ProductGrpcResponse> products) {
        return ProductListGrpcResponse.newBuilder().addAllProducts(products).build();
    }
}
