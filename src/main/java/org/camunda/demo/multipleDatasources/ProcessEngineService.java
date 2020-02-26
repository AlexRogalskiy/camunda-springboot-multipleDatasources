package org.camunda.demo.multipleDatasources;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.demo.multipleDatasources.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProcessEngineService {

	@Autowired
	private RuntimeService runtimeService;

	@Transactional(transactionManager = "camundaTransactionManager", propagation = Propagation.REQUIRED)
	public void startOrderProcess(String businessKey, Map<String, Object> vars) {
		runtimeService.startProcessInstanceByKey("orderProcess", vars);
	}
}
