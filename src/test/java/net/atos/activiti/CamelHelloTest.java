package net.atos.activiti;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import net.atos.activiti.common.AbstractTest;

import org.activiti.camel.ExchangeUtils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti-application-context.xml")
public class CamelHelloTest extends AbstractTest {

	@Autowired
	private CamelContext camelContext;

	@Autowired
  private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;

	@Test
	public void simpleProcessTest() {
		/*
    ProducerTemplate tpl = camelContext.createProducerTemplate();
    String instanceId = (String) tpl.requestBody("direct:start", Collections.singletonMap("var1", "hello"));
    assertEquals("world", runtimeService.getVariable(instanceId, "var2"));
		Task task = taskService.createTaskQuery().singleResult();
		assertEquals("HelloTask", task.getName());
		taskService.complete(task.getId());
		
	    String processInstanceId = findProcessInstanceId(exchange);
	    Execution execution = runtimeService.createExecutionQuery()
	        .processDefinitionKey(processKey)
	        .processInstanceId(processInstanceId)
	        .activityId(activity).singleResult();

	    if (execution == null) {
	      throw new RuntimeException("Couldn't find activity "+activity+" for processId " + processInstanceId);
	    }
	    runtimeService.setVariables(execution.getId(), ExchangeUtils.prepareVariables(exchange, getActivitiEndpoint()));
	    runtimeService.signal(execution.getId());
	    */
	}
}
