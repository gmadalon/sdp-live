package br.com.druid.sdp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class ServiceProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SP_ID")
	@SequenceGenerator(sequenceName = "service_provider_sq", allocationSize = 1, name = "SP_ID")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "name", length = 40)
	private String name;
	
	@Column(name = "subscriptionNotificationUrl", length = 1000)
	private String subscriptionNotificationUrl;

	@OneToMany(mappedBy = "serviceProvider", fetch = FetchType.EAGER)
	private List<Application> applications = new ArrayList<Application>();
}
