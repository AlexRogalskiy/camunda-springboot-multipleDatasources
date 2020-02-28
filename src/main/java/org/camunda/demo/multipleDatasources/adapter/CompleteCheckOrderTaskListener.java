package org.camunda.demo.multipleDatasources.adapter;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.demo.multipleDatasources.repository.CheckOrderTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component("completeCheckOrderTaskListener")
public class CompleteCheckOrderTaskListener implements TaskListener {

	@Autowired
	CheckOrderTaskRepository checkOrderTaskRepository;

	/**
	 * This Listener deletes a TaskInfoObject after completion
	 * 
	 */
	@Override
	@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
	public void notify(DelegateTask delegateTask) {
		Long cotId = (Long) delegateTask.getVariable("cotId");
		checkOrderTaskRepository.deleteById(cotId);
	}

}
