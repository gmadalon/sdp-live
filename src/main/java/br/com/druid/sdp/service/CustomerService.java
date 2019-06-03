package br.com.druid.sdp.service;

import br.com.druid.sdp.model.Customer;

public interface CustomerService {

	Customer getCustomer(String cpf, String externalCoId, String externalCustomerId);

	Customer createCustomer(String cpf, String externalCoId, String externalCustomerId);

}
