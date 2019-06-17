package br.com.druid.sdp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.druid.sdp.model.AmountTransaction;

@Mapper
public interface AmountTransactionMapper {

	AmountTransactionMapper MAPPER = Mappers.getMapper(AmountTransactionMapper.class);

	
	br.com.druid.gatekeeper.oneapi.model.AmountTransaction toAmountTransaction(AmountTransaction amountTransaction);

    AmountTransaction fromAmountTransaction(br.com.druid.gatekeeper.oneapi.model.AmountTransaction amountTransaction);
    
}
