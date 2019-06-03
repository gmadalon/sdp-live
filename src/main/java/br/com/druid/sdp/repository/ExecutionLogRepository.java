package br.com.druid.sdp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.druid.sdp.model.ExecutionLog;

@Repository
public interface ExecutionLogRepository extends CrudRepository<ExecutionLog, Long> {
	
	

}
