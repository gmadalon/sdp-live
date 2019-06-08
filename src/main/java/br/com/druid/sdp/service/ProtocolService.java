package br.com.druid.sdp.service;

import br.com.druid.sdp.model.Subscription;

public interface ProtocolService {

	void createForSubscription(Subscription subscription, String transactionId);

	void createForCancellation(Subscription subscription, String transactionId);

}
