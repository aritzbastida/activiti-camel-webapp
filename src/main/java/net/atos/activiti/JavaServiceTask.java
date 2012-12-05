package net.atos.activiti;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;


public class JavaServiceTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {
		
		System.out.println("execution id " + execution.getId());
		
		int i = 0;
		for (String str : execution.getVariableNames()) {
			System.out.println("VAR #" + i + ": " + str);
			i++;
		}
		
		//Long isbn = (Long) execution.getVariable("isbn");
		//System.out.println("received isbn " + isbn);
		execution.setVariable("validatetime", new Date());
	}
	
}
