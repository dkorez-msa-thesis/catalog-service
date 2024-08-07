package dev.dkorez.msathesis.catalog.controller;

import dev.dkorez.msathesis.catalog.model.rest.ProductRestResponse;
import dev.dkorez.msathesis.catalog.service.ProductServiceRest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@ApplicationScoped
@Path("api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductControllerRest {
    @Inject
    private ProductServiceRest productService;

    @GET
    @Path("products")
    public List<ProductRestResponse> findProducts() {
        return productService.getProducts();
    }

    @GET
    @Path("products/{id}")
    public ProductRestResponse getProduct(@PathParam("id") Long id) {
        return productService.getProduct(id);
    }

    @GET
    @Path("products/category/{id}")
    public List<ProductRestResponse> getProductsByCategory(@PathParam("id") Long id) {
        return productService.findByCategory(id);
    }

    @GET
    @Path("products/brand/{brand}")
    public List<ProductRestResponse> getProductsByBrand(@PathParam("brand") String brand) {
        return productService.findByBrand(brand);
    }

    @GET
    @Path("products/tag/{tag}")
    public List<ProductRestResponse> getProductsByTag(@PathParam("tag") String tag) {
        return productService.findByTag(tag);
    }

    @GET
    @Path("products/search")
    public List<ProductRestResponse> searchProducts(@QueryParam("q") String query) {
        return productService.searchProducts(query);
    }
}
