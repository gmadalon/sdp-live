package br.com.druid.sdp.service;

import br.com.druid.sdp.model.Subscription;

public interface SubscriptionService {
	
	Subscription create(String applicationId, String cpf, String coId, String customerId);

	void delete(String applicationId, String cpf, String externalCoId, String externalCustomerId);
	
}
