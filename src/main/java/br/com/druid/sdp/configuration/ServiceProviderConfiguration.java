package br.com.druid.sdp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceProviderConfiguration {

	@Bean
	public RestTemplate restTemplateServiceProvider() {
		return new RestTemplate();
	}
	
}
