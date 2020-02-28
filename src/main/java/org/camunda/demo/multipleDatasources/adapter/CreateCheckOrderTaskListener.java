package org.camunda.demo.multipleDatasources.adapter;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.demo.multipleDatasources.entity.CheckOrderTask;
import org.camunda.demo.multipleDatasources.entity.Order;
import org.camunda.demo.multipleDatasources.repository.CheckOrderTaskRepository;
import org.camunda.demo.multipleDatasources.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component("createCheckOrderTaskListener")
public class CreateCheckOrderTaskListener implements TaskListener {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	CheckOrderTaskRepository cotr;

	/**
	 * This Listener creates a TaskInfoObject for the Tasklist
	 * 
	 */
	@Override
	@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
	public void notify(DelegateTask delegateTask) {
		Order order = orderRepo.findById((Long) delegateTask.getVariable("orderId")).orElseThrow();
		CheckOrderTask task = new CheckOrderTask();
		task.setOrderId(order.getId());
		task.setOrderNumber(order.getOrderNumber());
		task.setCustomerName(order.getCustomer().getFirstName().concat(" ").concat(order.getCustomer().getLastName()));
		task.setOrderAmount(order.getOrderAmount());
		task.setStreet(order.getCustomer().getAddress().getStreet());
		task.setCity(order.getCustomer().getAddress().getCity());
		task.setZipCode(order.getCustomer().getAddress().getZipCode());

		task = cotr.save(task);

		delegateTask.setVariable("cotId", task.getId());
	}

}
