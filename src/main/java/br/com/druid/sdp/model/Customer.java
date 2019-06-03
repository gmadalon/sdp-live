package br.com.druid.sdp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_ID")
	@SequenceGenerator(sequenceName = "customer_sq", allocationSize = 1, name = "CUSTOMER_ID")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "cpf", unique = true, nullable = false)
	/* a number of a document from brazil is equivalent of tax id */
	private Long cpf;
	
	@Column(name = "externalCustomerId",unique = true, nullable = false)
	private String externalCustomerId;
	
	@Column(name = "externalCoId",unique = true, nullable = false)
	private String externalCoId;
	
	

}
