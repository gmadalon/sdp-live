package br.com.druid.sdp.delegate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.druid.sdp.api.TransactionApiDelegate;
import br.com.druid.sdp.mapper.AmountTransactionMapper;
import br.com.druid.sdp.model.Transaction;
import br.com.druid.sdp.service.ChargingService;

@Service
public class TransactionApiDelegateService implements TransactionApiDelegate {
	
	ChargingService chargingService;
	
	public TransactionApiDelegateService (ChargingService chargingService) {
		this.chargingService = chargingService;
	}

	@Override
	public ResponseEntity<Transaction> chargesACustomer(String authorization, Transaction amountTransaction,
			String xParamValues) {
		
		br.com.druid.gatekeeper.oneapi.model.AmountTransaction gkAmountTransaction = AmountTransactionMapper.MAPPER.toAmountTransaction(amountTransaction.getAmountTransaction());
		
		br.com.druid.gatekeeper.oneapi.model.AmountTransaction gkAmountTransactionReturned =  chargingService.chargesACustomer(gkAmountTransaction);
		
		Transaction transactionReturned  = new Transaction();
		transactionReturned.setAmountTransaction( AmountTransactionMapper.MAPPER.fromAmountTransaction(gkAmountTransactionReturned) );

		
		return new ResponseEntity<Transaction>(transactionReturned , HttpStatus.OK);
	}

}
