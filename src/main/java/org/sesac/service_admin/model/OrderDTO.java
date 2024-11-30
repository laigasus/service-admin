package org.sesac.service_admin.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDTO {

    private Long id;
    private Long productId;
    private UUID accountId;
    private Long price;
    private Integer quantity;
    private OrderState orderState;

}
