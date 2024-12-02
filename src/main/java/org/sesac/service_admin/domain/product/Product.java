package org.sesac.service_admin.domain.product;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @Tsid
    private Long id;

    private String name;

    private String image;

    private String description;

    private Long price;

    private Integer stock;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private OffsetDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_date", nullable = false)
    private OffsetDateTime modifiedDate;

}
