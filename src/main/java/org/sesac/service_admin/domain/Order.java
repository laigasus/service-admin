package org.sesac.service_admin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.sesac.service_admin.model.OrderState;


@Entity
@Table(name = "\"Order\"")
@Getter
@Setter
public class Order {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long productId;

    @Column(columnDefinition = "char(36)")
    private UUID accountId;

    @Column
    private Long price;

    @Column
    private Integer quantity;

    @Column(nullable = false)
    private OffsetDateTime orderDate;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

}
