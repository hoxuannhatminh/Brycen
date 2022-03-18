package com.brycen.vn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@EnableTransactionManagement
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "injection_history")

public class InjectionHistory extends Base {


	
	@Column(name = "injection_date", columnDefinition = "DATETIME NOT NULL")
	private String injectionDate;

	@Column(name = "address", columnDefinition = "VARCHAR(255) NOT NULL")
	private String address;

	@Column(name = "number_injection", columnDefinition = "VARCHAR(255) NOT NULL")
	private String numberInjection;

	@Column(name = "vaccineName", columnDefinition = "VARCHAR(255) NOT NULL")
	private String vaccineName;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	

}
