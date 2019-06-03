package br.com.druid.sdp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.druid.sdp.model.WebActivation;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SdpLiveApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() throws JsonProcessingException, Exception {

		WebActivation webActivation = new WebActivation();
		webActivation.setApplicationId("121");
		webActivation.setCoId("CoId1234");
		webActivation.setCpf("01234567890");
		webActivation.setCustomerId("CustomerId123");

		mockMvc.perform(post("/liveSubscription").header("Authorization", "dajdjakljdakljdkla").header("Content-Type", "application/json").content(new ObjectMapper().writeValueAsString(webActivation)))
				.andExpect(status().isCreated()).andReturn();

	}

}
