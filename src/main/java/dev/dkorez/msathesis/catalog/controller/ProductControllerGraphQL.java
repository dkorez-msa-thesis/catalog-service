package dev.dkorez.msathesis.catalog.controller;

import dev.dkorez.msathesis.catalog.model.gql.ProductGqlResponse;
import dev.dkorez.msathesis.catalog.service.ProductServiceGql;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class ProductControllerGraphQL {
    private final ProductServiceGql productService;

    @Inject
    public ProductControllerGraphQL(ProductServiceGql productService) {
        this.productService = productService;
    }

    @Query("products")
    public List<ProductGqlResponse> findProducts() {
        return productService.getProducts();
    }

    @Query("product")
    public ProductGqlResponse getProduct(@Name("id") Integer id) {
        return productService.getProduct(id.longValue());
    }

    @Query("productsByCategory")
    public List<ProductGqlResponse> findByCategory(@Name("id") Integer id) {
        return productService.findByCategory(id.longValue());
    }

    @Query("productsByBrand")
    public List<ProductGqlResponse> findByBrand(@Name("brand") String brand) {
        return productService.findByBrand(brand);
    }

    @Query("productsByTag")
    public List<ProductGqlResponse> findByTag(@Name("tag") String tag) {
        return productService.findByTag(tag);
    }

    @Query("searchProducts")
    public List<ProductGqlResponse> searchProducts(@Name("q") String q) {
        return productService.searchProducts(q);
    }
}
