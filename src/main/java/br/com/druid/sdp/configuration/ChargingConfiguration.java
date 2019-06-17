package br.com.druid.sdp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import br.com.druid.gatekeeper.GkConfig;
import br.com.druid.gatekeeper.oneapi.GkOneApiClient;
import br.com.druid.gatekeeper.oneapi.GkOneApiClientImpl;

@Configuration
public class ChargingConfiguration {

	GkConfig gkConfig;
	
	@Bean
	public GkConfig gkConfig(@Value("${gatekeeper.baseUrl}") String baseUrl,
			@Value("${gatekeeper.username}") String username,
			@Value("${gatekeeper.password}") String password,
			@Value("${gatekeeper.expiration}") String expiration) {
		gkConfig = new GkConfig(baseUrl, username, password, expiration);
		return gkConfig;
	}
	
	@Bean
	@DependsOn("gkConfig")
	public GkOneApiClient gkOneApiClient() {
		return new GkOneApiClientImpl(gkConfig);
	}

}
