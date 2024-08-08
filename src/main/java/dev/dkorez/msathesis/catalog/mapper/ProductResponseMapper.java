package dev.dkorez.msathesis.catalog.mapper;

import com.google.protobuf.DoubleValue;
import dev.dkorez.msathesis.catalog.entity.ProductDao;
import dev.dkorez.msathesis.catalog.entity.TagDao;
import dev.dkorez.msathesis.catalog.grpc.ProductGrpcResponse;
import dev.dkorez.msathesis.catalog.grpc.SpecsGrpc;
import dev.dkorez.msathesis.catalog.model.gql.ProductGqlResponse;
import dev.dkorez.msathesis.catalog.model.rest.ProductRestResponse;

import java.util.Collections;
import java.util.List;

public class ProductResponseMapper {
    public static ProductRestResponse toRestResponse(ProductDao dao) {
        if (dao == null)
            return null;

        ProductRestResponse response = new ProductRestResponse();
        response.setId(dao.getId());
        response.setName(dao.getName());
        response.setDescription(dao.getDescription());
        response.setPrice(dao.getPrice());
        response.setQuantity(dao.getQuantity());
        response.setActive(dao.isActive());
        response.setCategoryName(dao.getCategory() != null ?
                dao.getCategory().getName() : null);
        response.setBrandName(dao.getBrand() != null ?
                dao.getBrand().getName() : null);
        response.setSpecs(dao.getSpecification() != null ?
                dao.getSpecification().stream().map(SpecsResponseMapper::toRestResponse).toList() :
                Collections.emptyList());
        response.setTags(dao.getTags() != null ?
                dao.getTags().stream().map(ProductResponseMapper::getTagName).toList() :
                Collections.emptyList());

        return response;
    }

    public static ProductGqlResponse toGraphqlResponse(ProductDao dao) {
        if (dao == null)
            return null;

        ProductGqlResponse response = new ProductGqlResponse();
        response.setId(dao.getId());
        response.setName(dao.getName());
        response.setDescription(dao.getDescription());
        response.setPrice(dao.getPrice());
        response.setQuantity(dao.getQuantity());
        response.setActive(dao.isActive());
        response.setCategoryName(dao.getCategory() != null ?
                dao.getCategory().getName() : null);
        response.setBrandName(dao.getBrand() != null ?
                dao.getBrand().getName() : null);
        response.setSpecs(dao.getSpecification() != null ?
                dao.getSpecification().stream().map(SpecsResponseMapper::toGqlResponse).toList() :
                Collections.emptyList());
        response.setTags(dao.getTags() != null ?
                dao.getTags().stream().map(ProductResponseMapper::getTagName).toList() :
                Collections.emptyList());

        return response;
    }

    public static ProductGrpcResponse toGrpcResponse(ProductDao dao) {
        List<SpecsGrpc> specs = dao.getSpecification() != null ?
                dao.getSpecification().stream().map(SpecsResponseMapper::toGrpcResponse).toList() :
                Collections.emptyList();
        List<String> tags = dao.getTags() != null ?
                dao.getTags().stream().map(ProductResponseMapper::getTagName).toList() :
                Collections.emptyList();
        return ProductGrpcResponse.newBuilder()
                .setId(dao.getId())
                .setName(dao.getName())
                .setDescription(dao.getDescription())
                .setPrice(DoubleValue.of(dao.getPrice().doubleValue()))
                .setQuantity(dao.getQuantity())
                .setActive(dao.isActive())
                .setCategoryName(dao.getCategory() != null ? dao.getCategory().getName() : null)
                .setBrandName(dao.getBrand() != null ? dao.getBrand().getName() : null)
                .addAllSpecs(specs)
                .addAllTags(tags)
                .build();
    }

    private static String getTagName(TagDao dao) {
        return dao.getName();
    }
}
