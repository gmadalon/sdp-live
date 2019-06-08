package br.com.druid.sdp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.druid.sdp.facade.ServiceProviderNotification;
import br.com.druid.sdp.model.Subscription;

@Mapper
public interface ServiceProviderNotificationMapper {
	
	ServiceProviderNotificationMapper MAPPER = Mappers.getMapper(ServiceProviderNotificationMapper.class);

	@Mappings({
		@Mapping(target = "applicationId", source = "application.externalApplicationId" ),
		@Mapping(target = "cpf", source = "customer.cpf" ),
		@Mapping(target = "coId", source = "customer.externalCoId" ),
		@Mapping(target = "customerId", source = "customer.externalCustomerId" ),
		@Mapping(target = "subscriptionId", source = "subscriptionId" )		
	})
	ServiceProviderNotification toServiceProviderNotification(Subscription subscription);

}
