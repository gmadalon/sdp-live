package br.com.druid.sdp.service;

import br.com.druid.sdp.model.Subscription;

public interface ServiceProviderService {

	void notify(Subscription subscription, String transactionId);

}
