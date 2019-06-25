package br.com.druid.sdp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionLog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXECUTION_LOG")
	@SequenceGenerator(sequenceName = "execution_log_sq", allocationSize = 1, name = "EXECUTION_LOG")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "externalApplicationId", length = 50)
	private String externalApplicationId;
	
	@Column(name = "cpf")
	private String cpf;

	@Column(name = "externalCoId", length = 50)
	private String externalCoId;

	@Column(name = "externalCustomerId", length = 50)
	private String externalCustomerId;

	@Column(name = "subscriptionId", length = 50)
	private String subscriptionId;

	@Column(name = "transactionId", length = 50)
	private String transactionId;

	@Column(name = "logDate", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime logDate;

	@Column(name = "message", length = 1000)
	private String message;

	@Column(name = "exceptionMessage", length = 4000)
	private String exceptionMessage;

	@Column(name = "isOk", nullable = false)
	private boolean isOk;

	@Column(name = "errorCode")
	private ErrorCode errorCode;

	@Column(name = "event",length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private ExecutionLogEvent event;


}
