package org.camunda.demo.multipleDatasources.repository;

import org.camunda.demo.multipleDatasources.entity.CheckOrderTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckOrderTaskRepository extends JpaRepository<CheckOrderTask, Long> {
}