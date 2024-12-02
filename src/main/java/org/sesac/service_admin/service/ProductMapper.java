package org.sesac.service_admin.service;

import org.mapstruct.*;
import org.sesac.service_admin.domain.product.Product;
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
