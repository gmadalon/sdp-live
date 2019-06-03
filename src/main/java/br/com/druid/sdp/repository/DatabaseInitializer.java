package br.com.druid.sdp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.druid.sdp.model.Application;
import br.com.druid.sdp.model.ServiceProvider;

@Component
public class DatabaseInitializer implements CommandLineRunner{
	@Autowired
	private ServiceProviderRepository serviceProviderRepository;

	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Override
	public void run(String... args) throws Exception {

		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setName("Test Service Provider");

		serviceProviderRepository.save(serviceProvider);
		
		Application application = new Application();
		application.setServiceProvider(serviceProvider);
		application.setName("Test App 1");
		application.setExternalApplicationId("121");
		
		applicationRepository.save(application);

		application = new Application();
		application.setServiceProvider(serviceProvider);
		application.setName("Test App 2");
		application.setExternalApplicationId("122");

		
		applicationRepository.save(application);

	}

}
