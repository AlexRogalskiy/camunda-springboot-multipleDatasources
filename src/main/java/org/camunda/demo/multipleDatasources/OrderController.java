package org.camunda.demo.multipleDatasources;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.demo.multipleDatasources.entity.Order;
import org.camunda.demo.multipleDatasources.repository.OrderRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProcessEngineService engineService;

	private static Logger LOG = Logger.getLogger(OrderController.class);

	@PostMapping("/order")
	@Transactional(transactionManager = "domainTransactionManager", propagation = Propagation.REQUIRED)
	public Long createOrder(@RequestBody() Order order) {
		LOG.info("Received new Order " + order.getCustomerId() + " " + order.getOrderAmount());
		ObjectValue orderVar = Variables.objectValue(order).create();

		engineService.startOrderProcess(order.getOrderId(), Variables.putValueTyped("order", orderVar));
		orderRepository.save(order);
		if (1 == 1)
			throw new RuntimeException("Ooooops....!");
		return order.getId();
	}
}