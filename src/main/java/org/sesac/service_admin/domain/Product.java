package org.sesac.service_admin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Product {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String image;

    @Column(name = "\"description\"")
    private String description;

    @Column
    private Long price;

    @Column
    private Integer stock;

    @Column(nullable = false)
    private OffsetDateTime createdDate;

    @Column(nullable = false)
    private OffsetDateTime modifiedDate;

}
