package br.com.druid.sdp.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.druid.sdp.api.LiveSubscriptionApiDelegate;
import br.com.druid.sdp.model.WebActivation;
import br.com.druid.sdp.service.SubscriptionService;

@Service
public class LiveSubscriptionApiDelegateService implements LiveSubscriptionApiDelegate {

	
	SubscriptionService subscriptionService;
	
	@Autowired
	public LiveSubscriptionApiDelegateService(SubscriptionService subscriptionService ){
		this.subscriptionService = subscriptionService;
	}
	
	@Override
	public ResponseEntity<Void> liveSubscriptionPost(String authorization, WebActivation webActivation,
			String xParamValues) {
		subscriptionService.create(webActivation.getApplicationId(), webActivation.getCpf(), webActivation.getCoId(), webActivation.getCustomerId());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	
	
	
}
