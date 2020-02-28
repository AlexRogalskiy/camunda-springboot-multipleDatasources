package org.camunda.demo.multipleDatasources.repository;

import org.camunda.demo.multipleDatasources.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}