package org.sesac.service_admin.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String image;

    @Size(max = 255)
    private String description;

    private Long price;

    private Integer stock;

}
