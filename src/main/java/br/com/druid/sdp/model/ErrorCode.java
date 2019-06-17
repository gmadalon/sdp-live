package br.com.druid.sdp.model;

public enum ErrorCode {

	InvalidCustomerFields("SVC1000","CPF, coId, customerId parameter is missing"),
	ClientIdRequired("SVC1002","Client Id is missing"),
	ApplicationNotFound("SVC1001", "Application Identifier is missing"),
	SubscriptionNotFount("SVC1100" , "Error querying the subscription system"),
	InvalidExternalCoid("SVC2001", "coId is invalid"),
	CustomerNotFound("SVC2002", "Customer not found"),
	CustomerAlreadySubscribed("SVC2003", "Customer already subscriber"),
	InvalidExternalCustomerId("SVC2004", "CustomerId is invalid"),
	GatekeeperError("SVC2005" , "Error calling Gatekeer"),
	Internal("POL1001", "Duplicated subscription request");
	

	String code;
	String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
}
