package br.com.druid.sdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.druid.gatekeeper.exception.GkException;
import br.com.druid.gatekeeper.oneapi.GkOneApiClient;
import br.com.druid.gatekeeper.oneapi.model.AmountTransaction;
import br.com.druid.sdp.model.ErrorCode;
import br.com.druid.sdp.model.exception.BusinessException;
import br.com.druid.sdp.service.ChargingService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChargingServiceImpl implements ChargingService {

	
	GkOneApiClient gkOneApiClient;
	
	@Autowired
	public ChargingServiceImpl(GkOneApiClient gkOneApiClient) {
		this.gkOneApiClient = gkOneApiClient;
	}
	
	@Override
	public AmountTransaction chargesACustomer(AmountTransaction amountTransaction){
		AmountTransaction amountTransactionReturned = null;
		
		try {
			
			amountTransactionReturned = gkOneApiClient.chargeAmountTransaction("tel:" + amountTransaction.getEndUserId(), amountTransaction);
			
		} catch (GkException e) {
			log.error("Error calling gatekeeper!" , e);
			throw new BusinessException(ErrorCode.GatekeeperError);
		}
		
		return amountTransactionReturned;
		
	}

}
