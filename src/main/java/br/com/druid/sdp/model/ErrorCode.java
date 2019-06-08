package br.com.druid.sdp.model;

public enum ErrorCode {
	
	ClientIdRequired("SVC1002","Client Id is missing"),
	ApplicationNotFound("SVC1001", "Application Identifier is missing"),
	CustomerAlreadySubscribed("SVC2003", "Customer already subscriber"),
	CustomerNotFound("SVC2002", "Customer not found"),
	InvalidExternalCoid("SVC2001", "coId is invalid"),
	InvalidExternalCustomerId("SVC2000", "customerId is invalid"),
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
