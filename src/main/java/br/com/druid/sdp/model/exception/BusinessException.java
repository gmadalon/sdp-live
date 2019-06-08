package br.com.druid.sdp.model.exception;

import br.com.druid.sdp.model.ErrorCode;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	ErrorCode errorCode;

	BusinessException() {
		super();
	}

	BusinessException(String message) {
		super(message);
	}

	BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	BusinessException(Throwable cause) {
		super(cause);
	}
	
	public BusinessException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return this.errorCode;
	}


}
