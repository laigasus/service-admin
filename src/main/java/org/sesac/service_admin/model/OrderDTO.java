package org.sesac.service_admin.model;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
public class OrderDTO {

    private Long id;

    private Long productId;

    private UUID accountId;

    private Long price;

    private Integer quantity;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime orderDate;

    private OrderState orderState;

}
