package br.com.druid.sdp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.druid.sdp.exception.CustomerNotFoundException;
import br.com.druid.sdp.exception.InvalidExternalCoidException;
import br.com.druid.sdp.exception.InvalidExternalCustomerId;
import br.com.druid.sdp.model.Customer;
import br.com.druid.sdp.repository.CustomerRepository;
import br.com.druid.sdp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer getCustomer(String cpf, String externalCoId, String externalCustomerId) {
		Customer customer = null;
		Optional<Customer> optionalCustomer = customerRepository.findByCpf(Long.valueOf(cpf));
		if (!optionalCustomer.isPresent()) {
			throw new CustomerNotFoundException();
		} else {
			customer = optionalCustomer.get();
			if (!ObjectUtils.nullSafeEquals(customer.getExternalCoId(), externalCoId)) {
				throw new InvalidExternalCoidException();
			} else if (!ObjectUtils.nullSafeEquals(customer.getExternalCustomerId(), externalCustomerId)) {
				throw new InvalidExternalCustomerId();
			}
		}
		return customer;
	}

	@Override
	public Customer createCustomer(String cpf, String externalCoId, String externalCustomerId) {
		Customer customer = null;
		Optional<Customer> optionalCustomer = customerRepository.findByCpf(Long.valueOf(cpf));
		if (!optionalCustomer.isPresent()) {
			customer = Customer.builder().cpf(Long.valueOf(cpf)).externalCoId(externalCoId)
					.externalCustomerId(externalCustomerId).build();
			customer = customerRepository.save(customer);
		} else {
			customer = optionalCustomer.get();
			if (!ObjectUtils.nullSafeEquals(customer.getExternalCoId(), externalCoId)) {
				throw new InvalidExternalCoidException();
			} else if (!ObjectUtils.nullSafeEquals(customer.getExternalCustomerId(), externalCustomerId)) {
				throw new InvalidExternalCustomerId();
			}
		}
		return customer;
	}

}
