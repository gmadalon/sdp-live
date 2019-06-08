package br.com.druid.sdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.druid.sdp.model.ExecutionLog;
import br.com.druid.sdp.repository.ExecutionLogRepository;
import br.com.druid.sdp.service.ExecutionLogService;

@Service
public class ExecutionLogServiceImpl implements ExecutionLogService {

	ExecutionLogRepository executionLogRepository;

	@Autowired
	public ExecutionLogServiceImpl(ExecutionLogRepository executionLogRepository) {
		this.executionLogRepository = executionLogRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(ExecutionLog executionLog) {
		executionLogRepository.save(executionLog);
	}

}
