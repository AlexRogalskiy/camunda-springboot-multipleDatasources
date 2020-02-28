package org.camunda.demo.mulitpleDatasources;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.camunda.demo.multipleDatasources.xa.config.CamundaDatasourceConfig;
import org.camunda.demo.multipleDatasources.xa.config.CamundaDatasourceProperties;
import org.camunda.demo.multipleDatasources.xa.config.DomainDatasourceConfig;
import org.camunda.demo.multipleDatasources.xa.config.DomainDatasourceProperties;
import org.camunda.demo.multipleDatasources.xa.config.MainConfig;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ContextConfiguration(classes = { TestApp.class })
public class ProcessScenarioTest {

	private static final String PROCESS_DEFINITION_KEY = "domainData";

	static {
		LogFactory.useSlf4jLogging(); // MyBatis
	}
//
//	@Rule
//	public final ProcessEngineRule processEngine = new StandaloneInMemoryTestConfiguration().rule();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	private ProcessScenario myProcess;

	@Test
	public void testHappyPath() {
		// Define scenarios by using camunda-bpm-assert-scenario:

		// ExecutableRunner starter = Scenario.run(myProcess) //
		// .startByKey(PROCESS_DEFINITION_KEY);

		// when(myProcess.waitsAtReceiveTask(anyString())).thenReturn((messageSubscription)
		// -> {
		// messageSubscription.receive();
		// });
		// when(myProcess.waitsAtUserTask(anyString())).thenReturn((task) -> {
		// task.complete();
		// });

		// OK - everything prepared - let's go and execute the scenario
		// Scenario scenario = starter.execute();

		// now you can do some assertions
		// verify(myProcess).hasFinished("EndEvent");
	}

}
