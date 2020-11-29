package com.example.demo.repository;

import com.example.demo.domain.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
    OrderStatus findByStatusCode(String code);
}
