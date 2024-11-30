package org.sesac.service_admin.repos;

import org.sesac.service_admin.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllById(Long id, Pageable pageable);

}
