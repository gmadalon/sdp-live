package br.com.druid.sdp.service;

import br.com.druid.gatekeeper.oneapi.model.AmountTransaction;

public interface ChargingService {

	AmountTransaction chargesACustomer(AmountTransaction amountTransaction);

}
