package br.com.druid.sdp.model.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.druid.sdp.model.ErrorCode;
import br.com.druid.sdp.model.RequestError;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ BusinessException.class })
	public ResponseEntity<Object> handleExecutionBusinessException(BusinessException businessException, WebRequest request) {
		
		RequestError requestError = new RequestError();
		requestError.setMessageId(businessException.errorCode.getCode());
		requestError.setText(businessException.errorCode.getMessage());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return handleExceptionInternal(businessException, requestError, headers, HttpStatus.BAD_REQUEST, request);
	
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleExecutionException(Exception e, WebRequest request) {
		
		log.error("Internal error! ex: " , e);
		RequestError requestError = new RequestError();
		requestError.setMessageId(ErrorCode.Internal.getCode());
		requestError.setText(ErrorCode.Internal.getMessage());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return handleExceptionInternal(e, requestError, headers, HttpStatus.BAD_REQUEST, request);
	
	}

}
