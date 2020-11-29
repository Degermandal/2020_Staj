package com.example.demo.repository;

import com.example.demo.domain.OrderPeriot;
import org.springframework.data.repository.CrudRepository;

public interface OrderPeriotRepository extends CrudRepository<OrderPeriot, Long> {
    OrderPeriot findByOrderType(String orderCode);
}
