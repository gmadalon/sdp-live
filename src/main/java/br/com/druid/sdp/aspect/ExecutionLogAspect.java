package br.com.druid.sdp.aspect;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.druid.sdp.model.Customer;
import br.com.druid.sdp.model.ErrorCode;
import br.com.druid.sdp.model.ExecutionLog;
import br.com.druid.sdp.model.ExecutionLogEvent;
import br.com.druid.sdp.model.Subscription;
import br.com.druid.sdp.model.exception.BusinessException;
import br.com.druid.sdp.repository.ExecutionLogRepository;
import br.com.druid.sdp.service.ExecutionLogService;

@Aspect
@Component
public class ExecutionLogAspect {

	ExecutionLogRepository executionLogRepository;
	
	ExecutionLogService executionLogService;

	@Autowired
	public ExecutionLogAspect(ExecutionLogRepository executionLogRepository, ExecutionLogService executionLogService) {
		this.executionLogRepository = executionLogRepository;
		this.executionLogService = executionLogService;
	}

	@AfterReturning(pointcut = "execution(* br.com.druid.sdp.service.impl.CustomerServiceImpl.createCustomer(..))", returning = "returnObject")
	public void customerCreationOK(JoinPoint joinPoint, Object returnObject) {

		if (returnObject != null && returnObject instanceof Customer) {
			Customer customer = (Customer) returnObject;
			ExecutionLog executionLog = ExecutionLog.builder().externalCoId(customer.getExternalCoId()).externalCustomerId(customer.getExternalCustomerId())
					.cpf(customer.getCpf()).transactionId((String)joinPoint.getArgs()[3])
					.event(ExecutionLogEvent.CUSTOMER_CREATION).isOk(true).logDate(LocalDateTime.now()).build();
			executionLogService.save(executionLog);
		}

	}

	
	@AfterReturning(pointcut = "execution(* br.com.druid.sdp.service.impl.SubscriptionServiceImpl.create(..))", returning = "returnObject")
	public void subscriptionCreationOK(JoinPoint joinPoint, Object returnObject) {

		if (returnObject != null && returnObject instanceof Subscription) {
			Subscription subscription = (Subscription) returnObject;
			ExecutionLog executionLog = ExecutionLog.builder().subscriptionId(subscription.getSubscriptionId()).externalApplicationId(subscription.getApplication().getExternalApplicationId())
					.externalCoId(subscription.getCustomer().getExternalCoId()).externalCustomerId(subscription.getCustomer().getExternalCustomerId()).subscriptionId(subscription.getSubscriptionId())
					.cpf(subscription.getCustomer().getCpf()).transactionId(subscription.getTransactionId())
					.event(ExecutionLogEvent.SUBSCRIPTION_CREATION).isOk(true).logDate(LocalDateTime.now()).build();
			executionLogService.save(executionLog);
		}

	}
	
	@AfterReturning(pointcut = "execution(* br.com.druid.sdp.service.impl.SubscriptionServiceImpl.delete(..))", returning = "returnObject")
	public void subscriptionDeletionOK(JoinPoint joinPoint, Object returnObject) {

		if (returnObject != null && returnObject instanceof Subscription) {
			Subscription subscription = (Subscription) returnObject;
			ExecutionLog executionLog = ExecutionLog.builder().subscriptionId(subscription.getSubscriptionId()).externalApplicationId(subscription.getApplication().getExternalApplicationId())
					.externalCoId(subscription.getCustomer().getExternalCoId()).externalCustomerId(subscription.getCustomer().getExternalCustomerId()).subscriptionId(subscription.getSubscriptionId())
					.cpf(subscription.getCustomer().getCpf()).transactionId(subscription.getTransactionId())
					.event(ExecutionLogEvent.SUBSCRIPTION_DELETION).isOk(true).logDate(LocalDateTime.now()).build();
			executionLogService.save(executionLog);
		}

	}

	@AfterReturning(pointcut = "execution(* br.com.druid.sdp.service.impl.ProtocolServiceImpl.createForSubscription(..))")
	public void createForSubscriptionOK(JoinPoint joinPoint) {

		if (joinPoint.getArgs() != null && joinPoint.getArgs().length >= 2 && joinPoint.getArgs()[0] instanceof Subscription) {
			Subscription subscription = (Subscription) joinPoint.getArgs()[0];
			ExecutionLog executionLog = ExecutionLog.builder().subscriptionId(subscription.getSubscriptionId()).externalApplicationId(subscription.getApplication().getExternalApplicationId())
					.externalCoId(subscription.getCustomer().getExternalCoId()).externalCustomerId(subscription.getCustomer().getExternalCustomerId()).subscriptionId(subscription.getSubscriptionId())
					.cpf(subscription.getCustomer().getCpf()).transactionId((String)joinPoint.getArgs()[1])
					.event(ExecutionLogEvent.PROTOCOL_CREATION_SUBSCRIPTION).isOk(true).logDate(LocalDateTime.now()).build();
			executionLogService.save(executionLog);
		}

	}

	@AfterReturning(pointcut = "execution(* br.com.druid.sdp.service.impl.ProtocolServiceImpl.createForCancellation(..))")
	public void createForCancellationOK(JoinPoint joinPoint) {

		if (joinPoint.getArgs() != null && joinPoint.getArgs().length >= 2 && joinPoint.getArgs()[0] instanceof Subscription) {
			Subscription subscription = (Subscription) joinPoint.getArgs()[0];
			ExecutionLog executionLog = ExecutionLog.builder().subscriptionId(subscription.getSubscriptionId()).externalApplicationId(subscription.getApplication().getExternalApplicationId())
					.externalCoId(subscription.getCustomer().getExternalCoId()).externalCustomerId(subscription.getCustomer().getExternalCustomerId()).subscriptionId(subscription.getSubscriptionId())
					.cpf(subscription.getCustomer().getCpf()).transactionId((String)joinPoint.getArgs()[1])
					.event(ExecutionLogEvent.PROTOCOL_CANCELLATION_SUBSCRIPTION).isOk(true).logDate(LocalDateTime.now()).build();
			executionLogService.save(executionLog);
		}

	}
	
	@AfterThrowing(pointcut = "execution(* br.com.druid.sdp.service.impl.ProtocolServiceImpl.createForSubscription(..))" , throwing = "throwable")
	public void createForSubscriptionException(JoinPoint joinPoint, Throwable throwable) {

		if (joinPoint.getArgs() != null && joinPoint.getArgs().length >= 2 && joinPoint.getArgs()[0] instanceof Subscription) {
			Subscription subscription = (Subscription) joinPoint.getArgs()[0];
			ExecutionLog executionLog = ExecutionLog.builder().subscriptionId(subscription.getSubscriptionId()).externalApplicationId(subscription.getApplication().getExternalApplicationId())
					.externalCoId(subscription.getCustomer().getExternalCoId()).externalCustomerId(subscription.getCustomer().getExternalCustomerId()).subscriptionId(subscription.getSubscriptionId())
					.cpf(subscription.getCustomer().getCpf()).transactionId((String)joinPoint.getArgs()[1])
					.event(ExecutionLogEvent.PROTOCOL_CREATION_SUBSCRIPTION).isOk(false).logDate(LocalDateTime.now()).errorCode(getErrorCodeFromThrowable(throwable)).exceptionMessage(throwable.getMessage()).build();
			executionLogService.save(executionLog);
		}

	}

	@AfterThrowing(pointcut = "execution(* br.com.druid.sdp.service.impl.ProtocolServiceImpl.createForCancellation(..))", throwing = "throwable")
	public void createForCancellationException(JoinPoint joinPoint, Throwable throwable) {

		if (joinPoint.getArgs() != null && joinPoint.getArgs().length >= 2 && joinPoint.getArgs()[0] instanceof Subscription) {
			Subscription subscription = (Subscription) joinPoint.getArgs()[0];
			ExecutionLog executionLog = ExecutionLog.builder().subscriptionId(subscription.getSubscriptionId()).externalApplicationId(subscription.getApplication().getExternalApplicationId())
					.externalCoId(subscription.getCustomer().getExternalCoId()).externalCustomerId(subscription.getCustomer().getExternalCustomerId()).subscriptionId(subscription.getSubscriptionId())
					.cpf(subscription.getCustomer().getCpf()).transactionId((String)joinPoint.getArgs()[1])
					.event(ExecutionLogEvent.PROTOCOL_CANCELLATION_SUBSCRIPTION).isOk(false).logDate(LocalDateTime.now()).errorCode(getErrorCodeFromThrowable(throwable)).exceptionMessage(throwable.getMessage()).build();
			executionLogService.save(executionLog);
		}

	}

	
	@AfterReturning(pointcut = "execution(* br.com.druid.sdp.service.impl.ServiceProviderServiceImpl.notify(..))")
	public void serviceProviderNotificationOK(JoinPoint joinPoint) {

		if (joinPoint.getArgs() != null && joinPoint.getArgs().length >= 1 && joinPoint.getArgs()[0] instanceof Subscription) {
			Subscription subscription = (Subscription) joinPoint.getArgs()[0];
			ExecutionLog executionLog = ExecutionLog.builder().subscriptionId(subscription.getSubscriptionId()).externalApplicationId(subscription.getApplication().getExternalApplicationId())
					.externalCoId(subscription.getCustomer().getExternalCoId()).externalCustomerId(subscription.getCustomer().getExternalCustomerId()).subscriptionId(subscription.getSubscriptionId())
					.cpf(subscription.getCustomer().getCpf()).transactionId(subscription.getTransactionId())
					.event(ExecutionLogEvent.SERVICE_PROVIDER_NOTIFICATION).isOk(true).logDate(LocalDateTime.now()).build();
			executionLogService.save(executionLog);
		}

	}

	
	@AfterThrowing(pointcut = "execution(* br.com.druid.sdp.service.impl.ServiceProviderServiceImpl.notify(..))",throwing = "throwable")
	public void serviceProviderNotificationException(JoinPoint joinPoint, Throwable throwable) {

		if (joinPoint.getArgs() != null && joinPoint.getArgs().length >= 1 && joinPoint.getArgs()[0] instanceof Subscription) {
			Subscription subscription = (Subscription) joinPoint.getArgs()[0];
			ExecutionLog executionLog = ExecutionLog.builder().subscriptionId(subscription.getSubscriptionId()).externalApplicationId(subscription.getApplication().getExternalApplicationId())
					.externalCoId(subscription.getCustomer().getExternalCoId()).externalCustomerId(subscription.getCustomer().getExternalCustomerId()).subscriptionId(subscription.getSubscriptionId())
					.cpf(subscription.getCustomer().getCpf()).transactionId(subscription.getTransactionId())
					.event(ExecutionLogEvent.SERVICE_PROVIDER_NOTIFICATION).isOk(false).logDate(LocalDateTime.now()).errorCode(getErrorCodeFromThrowable(throwable)).exceptionMessage(throwable.getMessage()).build();
			executionLogService.save(executionLog);
		}

	}

	@AfterThrowing(pointcut = "execution(* br.com.druid.sdp.service.impl.SubscriptionServiceImpl.create(..))",throwing = "throwable")
	public void subscriptionCreationException(JoinPoint joinPoint, Throwable throwable) {
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length >= 5 ) {
			
			ExecutionLog executionLog = ExecutionLog.builder().externalApplicationId((String)joinPoint.getArgs()[0])
					.externalCoId((String)joinPoint.getArgs()[2]).externalCustomerId((String)joinPoint.getArgs()[3])
					.cpf((String)joinPoint.getArgs()[1]).transactionId((String)joinPoint.getArgs()[4])
					.event(ExecutionLogEvent.SUBSCRIPTION_CREATION).isOk(false).logDate(LocalDateTime.now()).errorCode(getErrorCodeFromThrowable(throwable)).exceptionMessage(throwable.getMessage()).build();
			executionLogService.save(executionLog);
		}

	}
	
	@AfterThrowing(pointcut = "execution(* br.com.druid.sdp.service.impl.SubscriptionServiceImpl.delete(..))",throwing = "throwable")
	public void subscriptionDeletionException(JoinPoint joinPoint, Throwable throwable) {
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length >= 5 ) {
			
			ExecutionLog executionLog = ExecutionLog.builder().externalApplicationId((String)joinPoint.getArgs()[0])
					.externalCoId((String)joinPoint.getArgs()[2]).externalCustomerId((String)joinPoint.getArgs()[3])
					.cpf((String)joinPoint.getArgs()[1]).transactionId((String)joinPoint.getArgs()[4])
					.event(ExecutionLogEvent.SUBSCRIPTION_DELETION).isOk(false).logDate(LocalDateTime.now()).errorCode(getErrorCodeFromThrowable(throwable)).exceptionMessage(throwable.getMessage()).build();
			executionLogService.save(executionLog);
		}

	}

	
	@AfterThrowing(pointcut = "execution(* br.com.druid.sdp.service.impl.CustomerServiceImpl.createCustomer(..))",throwing = "throwable")
	public void customerCreationException(JoinPoint joinPoint, Throwable throwable) {
		
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length >= 4 ) {
			ExecutionLog executionLog = ExecutionLog.builder()
					.externalCoId((String)joinPoint.getArgs()[1]).externalCustomerId((String)joinPoint.getArgs()[2]).cpf((String)joinPoint.getArgs()[1]).transactionId((String)joinPoint.getArgs()[3])
					.event(ExecutionLogEvent.SERVICE_PROVIDER_NOTIFICATION).isOk(false).logDate(LocalDateTime.now()).errorCode(getErrorCodeFromThrowable(throwable)).exceptionMessage(throwable.getMessage()).build();
			executionLogService.save(executionLog);
		}

	}


	private ErrorCode getErrorCodeFromThrowable(Throwable throwable){
		ErrorCode errorCode = ErrorCode.Internal;
		if(throwable instanceof BusinessException) {
			errorCode = ((BusinessException) throwable).getErrorCode();
		}
		return errorCode;
	}
	

}
