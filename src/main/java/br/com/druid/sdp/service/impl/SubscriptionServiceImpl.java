package br.com.druid.sdp.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.druid.sdp.exception.CustomerAlreadySubscribedException;
import br.com.druid.sdp.model.Application;
import br.com.druid.sdp.model.Customer;
import br.com.druid.sdp.model.Subscription;
import br.com.druid.sdp.repository.ServiceProviderRepository;
import br.com.druid.sdp.repository.SubscriptionRepository;
import br.com.druid.sdp.service.ApplicationService;
import br.com.druid.sdp.service.CustomerService;
import br.com.druid.sdp.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	ApplicationService applicationService;
	
	ServiceProviderRepository serviceProviderRepository;
	
	CustomerService customerService;
	
	SubscriptionRepository subscriptionRepository ;
	
	@Autowired
	public SubscriptionServiceImpl(ApplicationService applicationService, CustomerService customerService, SubscriptionRepository subscriptionRepository ){
		this.applicationService = applicationService;
		this.customerService = customerService;
		this.subscriptionRepository = subscriptionRepository;
	}

	
	Subscription getSubscription(Application application, Customer customer){
		
		Subscription subscription = null;
		Optional<Subscription> optionalSubscription = subscriptionRepository.findByApplicationAndCustomer(application, customer);
		if( optionalSubscription.isPresent() ) {
			subscription = optionalSubscription.get();
		}
		return subscription;

	}
	Subscription createSubscription(Application application, Customer customer){

		Subscription subscription = null;
		Optional<Subscription> optionalSubscription = subscriptionRepository.findByApplicationAndCustomer(application, customer);
		if( optionalSubscription.isPresent() ) {
			throw new CustomerAlreadySubscribedException();
		} else {
			subscription = Subscription.builder()
											.customer(customer)
											.application(application)
											.subscriptionDate(LocalDateTime.now())
											.build();
			
			subscription = subscriptionRepository.save(subscription);
		}
		
		return subscription;
	}
	
	@Override
	@Transactional
	public Subscription create( String externalApplicationId, String cpf, String externalCoId, String externalCustomerId ) {

		Application application = applicationService.getAplication(externalApplicationId);
		
		Customer customer = customerService.createCustomer( cpf, externalCoId, externalCustomerId);

		Subscription subscription = createSubscription(application, customer);
		
		return subscription;
	}

	@Override
	@Transactional
	public void delete(String externalApplicationId, String cpf, String externalCoId, String externalCustomerId ) {
		
		Customer customer = customerService.getCustomer(cpf, externalCoId, externalCustomerId);
		
		Application application = applicationService.getAplication(externalApplicationId);
		
		Subscription subscription = getSubscription(application, customer);
		
		subscriptionRepository.delete(subscription);

	}

}
