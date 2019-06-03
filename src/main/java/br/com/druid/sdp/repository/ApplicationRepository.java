package br.com.druid.sdp.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.druid.sdp.model.Application;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {

	@Cacheable(value="ApplicationRepository_findByExternalApplicationId")
	Optional<Application> findByExternalApplicationId(String externalApplicationId);

}
