package org.sesac.service_admin.service;

import lombok.RequiredArgsConstructor;
import org.sesac.service_admin.domain.order.Order;
import org.sesac.service_admin.model.OrderDTO;
import org.sesac.service_admin.repos.order.OrderRepository;
import org.sesac.service_admin.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Page<OrderDTO> findAll(final String filter, final Pageable pageable) {
        Page<Order> page;
        if (filter != null) {
            Long longFilter = null;
            try {
                longFilter = Long.parseLong(filter);
            } catch (final NumberFormatException numberFormatException) {
                // keep null - no parseable input
            }
            page = orderRepository.findAllById(longFilter, pageable);
        } else {
            page = orderRepository.findAll(pageable);
        }
        return new PageImpl<>(page.getContent()
                .stream()
                .map(order -> orderMapper.updateOrderDTO(order, new OrderDTO()))
                .toList(),
                pageable, page.getTotalElements());
    }

    public OrderDTO get(final Long id) {
        return orderRepository.findById(id)
                .map(order -> orderMapper.updateOrderDTO(order, new OrderDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OrderDTO orderDTO) {
        final Order order = new Order();
        orderMapper.updateOrder(orderDTO, order);
        return orderRepository.save(order).getId();
    }

    public void update(final Long id, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        orderMapper.updateOrder(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final Long id) {
        orderRepository.deleteById(id);
    }

}
