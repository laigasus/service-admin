package org.sesac.service_admin.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.sesac.service_admin.domain.Product;
import org.sesac.service_admin.model.ProductDTO;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

    ProductDTO updateProductDTO(Product product, @MappingTarget ProductDTO productDTO);

    @Mapping(target = "id", ignore = true)
    Product updateProduct(ProductDTO productDTO, @MappingTarget Product product);

}
