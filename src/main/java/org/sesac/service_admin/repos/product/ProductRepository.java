package org.sesac.service_admin.repos.product;

import org.sesac.service_admin.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllById(Long id, Pageable pageable);

}
