package org.camunda.demo.multipleDatasources;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CreateOrderDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.getVariableTyped("order");
	}

}
