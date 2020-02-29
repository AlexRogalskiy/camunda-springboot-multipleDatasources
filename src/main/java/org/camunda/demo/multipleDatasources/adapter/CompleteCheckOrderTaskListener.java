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
	 * If the subsequent activity fails and camunda database rolls back this
	 * taskInfoObject is deleted anyway!!!! Also known as I N C O N S I S T E N C Y!
	 * This can only be avoided by defining proper transaction boundaries in the
	 * model! IN that case an async before will do the trick
	 */
	@Override
	@Transactional(transactionManager = "domainTransactionManager", propagation = Propagation.REQUIRED)
	public void notify(DelegateTask delegateTask) {
		Long cotId = (Long) delegateTask.getVariable("cotId");
		checkOrderTaskRepository.deleteById(cotId);
	}
}
