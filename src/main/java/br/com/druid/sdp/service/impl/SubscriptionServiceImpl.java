package br.com.druid.sdp.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.druid.sdp.model.Application;
import br.com.druid.sdp.model.Customer;
import br.com.druid.sdp.model.ErrorCode;
import br.com.druid.sdp.model.Subscription;
import br.com.druid.sdp.model.exception.BusinessException;
import br.com.druid.sdp.repository.ServiceProviderRepository;
import br.com.druid.sdp.repository.SubscriptionRepository;
import br.com.druid.sdp.service.ApplicationService;
import br.com.druid.sdp.service.CustomerService;
import br.com.druid.sdp.service.ProtocolService;
import br.com.druid.sdp.service.ServiceProviderService;
import br.com.druid.sdp.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	ApplicationService applicationService;
	
	ServiceProviderRepository serviceProviderRepository;
	
	CustomerService customerService;
	
	SubscriptionRepository subscriptionRepository ;
	
	ServiceProviderService serviceProviderService;
	
	ProtocolService protocolService;
	
	@Autowired
	public SubscriptionServiceImpl(	ApplicationService applicationService, 
									CustomerService customerService, 
									SubscriptionRepository subscriptionRepository, 
									ServiceProviderService serviceProviderService,
									ProtocolService protocolService ){

		this.applicationService = applicationService;
		this.customerService = customerService;
		this.subscriptionRepository = subscriptionRepository;
		this.serviceProviderService = serviceProviderService;
		this.protocolService = protocolService;
	
	}

	
	Subscription getSubscription(Application application, Customer customer) {
		
		Subscription subscription = null;
		Optional<Subscription> optionalSubscription = subscriptionRepository.findByApplicationAndCustomer(application, customer);
		if( optionalSubscription.isPresent() ) {
			subscription = optionalSubscription.get();
		}
		return subscription;

	}
	
	Subscription createSubscription(Application application, Customer customer, String transactionId){

		Subscription subscription = null;
		Optional<Subscription> optionalSubscription = subscriptionRepository.findByApplicationAndCustomer(application, customer);
		if( optionalSubscription.isPresent() ) {
			throw new BusinessException(ErrorCode.CustomerAlreadySubscribed);
		} else {
			subscription = Subscription.builder()
											.customer(customer)
											.application(application)
											.subscriptionDate(LocalDateTime.now())
											.transactionId(transactionId)
											.build();
			subscription.createSubscriptionId();
			
			subscription = subscriptionRepository.save(subscription);
		}
		
		return subscription;
	}
	
	@Override
	@Transactional
	public Subscription create( String externalApplicationId, String cpf, String externalCoId, String externalCustomerId, String transactionId ) {
				
		Application application = applicationService.getAplication(externalApplicationId);
		
		Customer customer = customerService.createCustomer( cpf, externalCoId, externalCustomerId, transactionId );

		Subscription subscription = createSubscription(application, customer, transactionId);
		
		serviceProviderService.notify(subscription, transactionId );
		
		protocolService.createForSubscription(subscription, transactionId);
		
		return subscription;
	}

	@Override
	@Transactional
	public void delete(String externalApplicationId, String cpf, String externalCoId, String externalCustomerId, String transactionId  ) {
		
		Customer customer = customerService.getCustomer(cpf, externalCoId, externalCustomerId);
		
		Application application = applicationService.getAplication(externalApplicationId);
		
		Subscription subscription = getSubscription(application, customer);
		
		subscriptionRepository.delete(subscription);

		protocolService.createForCancellation(subscription, transactionId);
	
	}

}
