package br.com.druid.sdp.tests;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.druid.sdp.model.ExecutionLog;
import br.com.druid.sdp.model.ExecutionLogEvent;
import br.com.druid.sdp.model.WebActivation;
import br.com.druid.sdp.repository.ExecutionLogRepository;
import br.com.druid.sdp.repository.SubscriptionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SdpLiveApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ExecutionLogRepository executionLogRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;


	String applicationId = "121";
	String codId = "CoId1234";
	String cpf = "01234567890";
	String customerId = "CustomerId123";

	private static ClientAndServer mockServer;

	@BeforeClass
	public static void loadMocks() {
		mockServer = ClientAndServer.startClientAndServer(8090);
	}

	@AfterClass
	public static void releaseMocks() {
		mockServer.stop();
	}

	void mockServiceProviderRespondingOK() {

		mockServer
				.when(HttpRequest.request().withMethod("POST").withPath("/"), Times.exactly(1),
						TimeToLive.exactly(TimeUnit.MINUTES, 1l))
				.respond(HttpResponse.response().withStatusCode(200)
						.withHeaders(new Header("Content-Type", "text/plain")).withBody("OK\n"));

	}

	boolean verifySubscriptionOKExecutionLog(List<ExecutionLog> logs) {
		Optional<ExecutionLog> opLog = logs.stream()
				.filter(log -> ExecutionLogEvent.SUBSCRIPTION_CREATION.equals(log.getEvent())).findFirst();
		return opLog
				.filter(log -> applicationId.equals(log.getExternalApplicationId())
						&& codId.equals(log.getExternalCoId()) && cpf.equals(String.valueOf(log.getCpf()))
						&& customerId.equals(log.getExternalCustomerId()) && Boolean.TRUE.equals(log.isOk())
						&& StringUtils.hasText(log.getTransactionId()) && StringUtils.hasText(log.getSubscriptionId()))
				.isPresent();
	}
	
	boolean verifyProtocolForSubscriptionOKExecutionLog(List<ExecutionLog> logs) {
		Optional<ExecutionLog> opLog = logs.stream()
				.filter(log -> ExecutionLogEvent.PROTOCOL_CREATION_SUBSCRIPTION.equals(log.getEvent())).findFirst();
		return opLog
				.filter(log -> applicationId.equals(log.getExternalApplicationId())
						&& codId.equals(log.getExternalCoId()) && cpf.equals(String.valueOf(log.getCpf()))
						&& customerId.equals(log.getExternalCustomerId()) && Boolean.TRUE.equals(log.isOk())
						&& StringUtils.hasText(log.getTransactionId()) && StringUtils.hasText(log.getSubscriptionId()))
				.isPresent();
	}
	
	boolean verifyServiceProviderNotificationOKExecutionLog(List<ExecutionLog> logs) {
		Optional<ExecutionLog> opLog = logs.stream()
				.filter(log -> ExecutionLogEvent.SERVICE_PROVIDER_NOTIFICATION.equals(log.getEvent())).findFirst();
		return opLog
				.filter(log -> applicationId.equals(log.getExternalApplicationId())
						&& codId.equals(log.getExternalCoId()) && cpf.equals(String.valueOf(log.getCpf()))
						&& customerId.equals(log.getExternalCustomerId()) && Boolean.TRUE.equals(log.isOk())
						&& StringUtils.hasText(log.getTransactionId()) && StringUtils.hasText(log.getSubscriptionId()))
				.isPresent();
	}
	
	boolean verifyCustomerCreationOKExecutionLog(List<ExecutionLog> logs) {
		Optional<ExecutionLog> opLog = logs.stream()
				.filter(log -> ExecutionLogEvent.CUSTOMER_CREATION.equals(log.getEvent())).findFirst();
		return opLog
				.filter(log -> codId.equals(log.getExternalCoId()) && cpf.equals(String.valueOf(log.getCpf()))
						&& customerId.equals(log.getExternalCustomerId()) && Boolean.TRUE.equals(log.isOk())
						&& StringUtils.hasText(log.getTransactionId()))
				.isPresent();
	}

	void mockServiceProviderRespondingInternalServerError() {

		mockServer
				.when(HttpRequest.request().withMethod("POST").withPath("/"), Times.exactly(1),
						TimeToLive.exactly(TimeUnit.MINUTES, 1l))
				.respond(HttpResponse.response().withStatusCode(500)
						.withHeaders(new Header("Content-Type", "text/plain")).withBody("INTERNAL SERVER ERROR \n"));

	}

	@Before
	public void cleanDatabaseBeforeTest(){
		executionLogRepository.deleteAll();
		subscriptionRepository.deleteAll();
	}
	@Test
	public void testSubscritpionOK() throws JsonProcessingException, Exception {

		WebActivation webActivation = new WebActivation();
		webActivation.setApplicationId(applicationId);
		webActivation.setCoId(codId);
		webActivation.setCpf(cpf);
		webActivation.setCustomerId(customerId);

		mockServiceProviderRespondingOK();

		mockMvc.perform(post("/liveSubscription").header("Authorization", "dajdjakljdakljdkla")
				.header("Content-Type", "application/json")
				.content(new ObjectMapper().writeValueAsString(webActivation))).andExpect(status().isCreated())
				.andReturn();

		List<ExecutionLog> logs = executionLogRepository.findByExternalCustomerId("CustomerId123");

		if (logs.stream().count() != 4) {
			fail("Quantity of ExecutionLogs lines is wrong, the correct is 3!");
		} else if (!verifySubscriptionOKExecutionLog(logs)) {
			fail("A line of Subscription Event in Execution Log table not found or the fields are incorrect!");
		} else if(!verifyProtocolForSubscriptionOKExecutionLog(logs)) {
			fail("A line of ProtocolForSubscription Event in Execution Log table not found or the fields are incorrect!");
		} else if(!verifyCustomerCreationOKExecutionLog(logs)) {
			fail("A line of CustomerCreation Event in Execution Log table not found or the fields are incorrect!");
		} else if(!verifyServiceProviderNotificationOKExecutionLog(logs)) {
			fail("A line of ServiceProviderNotification Event in Execution Log table not found or the fields are incorrect!");
		}
		
	}

	@Test
	public void testSubscritpionServiceProvider500() throws JsonProcessingException, Exception {
		mockServer.reset();
		mockServiceProviderRespondingInternalServerError();

		WebActivation webActivation = new WebActivation();
		webActivation.setApplicationId(applicationId);
		webActivation.setCoId(codId);
		webActivation.setCpf(cpf);
		webActivation.setCustomerId(customerId);

		mockMvc.perform(post("/liveSubscription").header("Authorization", "dajdjakljdakljdkla")
				.header("Content-Type", "application/json")
				.content(new ObjectMapper().writeValueAsString(webActivation))).andExpect(status().isBadRequest())
				.andReturn();

		List<ExecutionLog> logs = executionLogRepository.findByExternalCustomerId("CustomerId123");

		if (logs.stream().count() != 3) {
			fail("Quantity of ExecutionLogs lines is wrong, the correct is 3!");
		} 


		System.out.println("teste");
	}

}
