package br.com.druid.sdp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class ExecutionLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXECUTION_LOG")
	@SequenceGenerator(sequenceName = "execution_log_sq", allocationSize = 1, name = "EXECUTION_LOG")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicationId")
    private Application application;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
	
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
	
	@Column(name = "event", nullable = false)
	private ExecutionLogEvent event;

}
