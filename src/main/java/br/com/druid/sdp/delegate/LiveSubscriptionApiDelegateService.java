package br.com.druid.sdp.delegate;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.druid.sdp.api.LiveSubscriptionApiDelegate;
import br.com.druid.sdp.model.CancellationResponse;
import br.com.druid.sdp.model.Subscription;
import br.com.druid.sdp.model.SubscriptionResponse;
import br.com.druid.sdp.model.WebActivation;
import br.com.druid.sdp.model.WebCancellation;
import br.com.druid.sdp.service.SubscriptionService;

@Service
public class LiveSubscriptionApiDelegateService implements LiveSubscriptionApiDelegate {

	
	SubscriptionService subscriptionService;
	
	@Autowired
	public LiveSubscriptionApiDelegateService(SubscriptionService subscriptionService ){
		this.subscriptionService = subscriptionService;
	}
	
	@Override
	public ResponseEntity<SubscriptionResponse> liveSubscriptionPost(String authorization, WebActivation webActivation,
			String xParamValues) {
		
		UUID transactionId = UUID.randomUUID();
		Subscription subscription = subscriptionService.create(webActivation.getApplicationId(), webActivation.getCpf(), webActivation.getCoId(), webActivation.getCustomerId(), transactionId.toString());
		
		SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
		subscriptionResponse.setSubscriptionId(subscription.getSubscriptionId());
		subscriptionResponse.setTransactionId(subscription.getTransactionId());
		return new ResponseEntity<SubscriptionResponse>(subscriptionResponse , HttpStatus.CREATED);
		
	}

	@Override
	public ResponseEntity<CancellationResponse> subscriptionActions(String authorization, WebCancellation webCancellation,
			String xParamValues) {
		
		UUID transactionId = UUID.randomUUID();
		subscriptionService.delete(webCancellation.getApplicationId(), webCancellation.getCpf(), webCancellation.getCoId(), webCancellation.getCustomerId(), transactionId.toString());
		CancellationResponse cancellationResponse = new CancellationResponse();
		cancellationResponse.setTransactionId(transactionId.toString());
		return new ResponseEntity<CancellationResponse>(cancellationResponse , HttpStatus.OK);

	}

	
	
	
}
