package br.com.druid.sdp.delegate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.druid.sdp.api.TransactionApiDelegate;
import br.com.druid.sdp.model.Transaction;

@Service
public class TransactionApiDelegateService implements TransactionApiDelegate {

	@Override
	public ResponseEntity<Void> chargesACustomer(String authorization, Transaction amountTransaction,
			String xParamValues) {
		// TODO Auto-generated method stub
		return TransactionApiDelegate.super.chargesACustomer(authorization, amountTransaction, xParamValues);
	}

}
