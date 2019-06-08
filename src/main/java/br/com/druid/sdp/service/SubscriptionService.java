package br.com.druid.sdp.service;

import br.com.druid.sdp.model.Subscription;

public interface SubscriptionService {

	Subscription create( String externalApplicationId, String cpf, String externalCoId, String externalCustomerId, String transactionId );

	void delete(String externalApplicationId, String cpf, String externalCoId, String externalCustomerId,
			String transactionId);
}
