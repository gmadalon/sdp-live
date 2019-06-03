package br.com.druid.sdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.druid.sdp.facade.ServiceProviderNotification;
import br.com.druid.sdp.mapper.ServiceProviderNotificationMapper;
import br.com.druid.sdp.model.Subscription;
import br.com.druid.sdp.service.ServiceProviderService;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

	RestTemplate restTemplate;
	
	@Autowired
	public ServiceProviderServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public void notify(Subscription subscription){
		ServiceProviderNotification notification = ServiceProviderNotificationMapper.MAPPER.toServiceProviderNotification(subscription);
		restTemplate.postForLocation(subscription.getApplication().getServiceProvider().getSubscriptionNotificationUrl(),notification);
	}
}
