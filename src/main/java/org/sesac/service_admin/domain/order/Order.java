package org.sesac.service_admin.domain.order;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.sesac.service_admin.model.OrderState;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "`order`")
@Getter
@Setter
public class Order {

    @Id
    @Tsid
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "account_id")
    private UUID accountId;

    @Column
    private Long price;

    @Column
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PENDING'")
    @Column(name = "order_state")
    private OrderState orderState;

    @CreatedDate
    @Column(name = "order_date", updatable = false, nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime orderDate;

}
