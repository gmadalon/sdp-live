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
import javax.persistence.UniqueConstraint;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"customerId", "applicationId"})
	})
@Data
@Builder
public class Subscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBSCRIPTION_ID")
	@SequenceGenerator(sequenceName = "subscription_sq", allocationSize = 1, name = "SUBSCRIPTION_ID")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "applicationId")
    private Application application;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer customer;
	
	@Column(name = "subscriptionDate", columnDefinition = "TIMESTAMP")
	private LocalDateTime subscriptionDate;
	
	@Column(name = "externalSubscriptionId")
	private String externalSubscriptionId;

	
}
