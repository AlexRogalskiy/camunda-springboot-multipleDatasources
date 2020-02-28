package org.camunda.demo.multipleDatasources.repository;

import org.camunda.demo.multipleDatasources.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}