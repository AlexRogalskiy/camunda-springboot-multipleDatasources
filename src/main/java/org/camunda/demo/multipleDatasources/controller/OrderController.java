package org.camunda.demo.multipleDatasources.controller;

import org.camunda.demo.multipleDatasources.entity.CheckOrderTask;
import org.camunda.demo.multipleDatasources.entity.Order;
import org.camunda.demo.multipleDatasources.repository.AddressRepository;
import org.camunda.demo.multipleDatasources.repository.CheckOrderTaskRepository;
import org.camunda.demo.multipleDatasources.repository.CustomerRepository;
import org.camunda.demo.multipleDatasources.repository.OrderRepository;
import org.camunda.demo.multipleDatasources.services.ProcessEngineService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CheckOrderTaskRepository checkOrderTaskRepository;

	@Autowired
	ProcessEngineService engineService;

	private static Logger LOG = Logger.getLogger(OrderController.class);

	@PostMapping("/order")
	@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
	public Long createOrder(@RequestBody() Order order) {
		LOG.info("Received new Order " + order.getOrderNumber() + " / " + order.getCustomer().getFirstName() + " "
				+ order.getCustomer().getLastName());

		addressRepository.save(order.getCustomer().getAddress());
		customerRepository.save(order.getCustomer());
		orderRepository.save(order);

		engineService.startOrderProcess(order.getOrderNumber(), order);

		if (order.getOrderFail()) {
			throw new RuntimeException("Order failed");
		}
		return order.getId();
	}

	@RequestMapping(value = "/checkOrderTask/{cotId}", method = RequestMethod.GET)
	@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
	public CheckOrderTask checkOrderTask(@PathVariable long cotId) {
		return checkOrderTaskRepository.findById(cotId).orElseThrow();
	}
}