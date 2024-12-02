package org.sesac.service_admin.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.sesac.service_admin.domain.order.Order;
import org.sesac.service_admin.model.OrderDTO;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper {

    OrderDTO updateOrderDTO(Order order, @MappingTarget OrderDTO orderDTO);

    @Mapping(target = "id", ignore = true)
    Order updateOrder(OrderDTO orderDTO, @MappingTarget Order order);

}
