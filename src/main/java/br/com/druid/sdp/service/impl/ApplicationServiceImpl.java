package br.com.druid.sdp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.druid.sdp.model.Application;
import br.com.druid.sdp.model.ErrorCode;
import br.com.druid.sdp.model.exception.BusinessException;
import br.com.druid.sdp.repository.ApplicationRepository;
import br.com.druid.sdp.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	ApplicationRepository applicationRepository;
	
	@Autowired
	public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}
	
	@Override
	public Application getAplication(String externalApplicationId) {
		
		Optional<Application> optionalApplication = applicationRepository.findByExternalApplicationId(externalApplicationId);
		Application application = null;
		if( !optionalApplication.isPresent() ) {
			throw new BusinessException(ErrorCode.ApplicationNotFound);
		} else {
			application = optionalApplication.get();
		}
		
		return application;
	}
	
}
