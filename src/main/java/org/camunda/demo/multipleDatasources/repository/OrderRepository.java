package org.camunda.demo.multipleDatasources.repository;

import org.camunda.demo.multipleDatasources.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}