package dev.dkorez.msathesis.catalog.mapper;

import dev.dkorez.msathesis.catalog.entity.SpecificationDao;
import dev.dkorez.msathesis.catalog.grpc.SpecsGrpc;
import dev.dkorez.msathesis.catalog.model.gql.SpecificationGql;
import dev.dkorez.msathesis.catalog.model.rest.SpecsRest;

public class SpecsResponseMapper {
    public static SpecsRest toRestResponse(SpecificationDao dao) {
        if (dao == null)
            return null;

        SpecsRest response = new SpecsRest();
        response.setName(dao.getName());
        response.setValue(dao.getValue());

        return response;
    }

    public static SpecificationGql toGqlResponse(SpecificationDao dao) {
        if (dao == null)
            return null;

        SpecificationGql response = new SpecificationGql();
        response.setName(dao.getName());
        response.setValue(dao.getValue());

        return response;
    }

    public static SpecsGrpc toGrpResponse(SpecificationDao dao) {
        if (dao == null)
            return null;

        return SpecsGrpc.newBuilder()
                .setName(dao.getName())
                .setValue(dao.getValue())
                .build();
    }
}
