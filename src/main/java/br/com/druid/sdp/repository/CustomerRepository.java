package br.com.druid.sdp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.druid.sdp.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Optional<Customer> findByCpf(String cpf);
	
	
}
