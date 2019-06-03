package br.com.druid.sdp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.druid.sdp.model.Application;
import br.com.druid.sdp.model.Customer;
import br.com.druid.sdp.model.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

	Optional<Subscription> findByApplicationAndCustomer(Application application, Customer customer);
}
