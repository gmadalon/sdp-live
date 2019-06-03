package br.com.druid.sdp.model;

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
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SP_ID")
    @SequenceGenerator(sequenceName = "service_provider_sq", allocationSize = 1, name = "SP_ID")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "name", length = 40)
	private String name;

	@Column(name = "externalApplicationId", unique=true, length = 40)
	String externalApplicationId;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceProviderId")
    private ServiceProvider serviceProvider;
	

}
