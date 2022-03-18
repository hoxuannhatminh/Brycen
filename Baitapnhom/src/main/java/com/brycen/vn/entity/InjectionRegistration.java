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
@Table(name = "injection_registration")

public class InjectionRegistration extends Base {


	@Column(name = "status", columnDefinition = "BIGINT NOT NULL")
	private Long status;

	@Column(name = "number_injection", columnDefinition = "VARCHAR(255) NOT NULL")
	private String numberInjection;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "injection_calendar_id")
	private InjectionCalendar injectionCalendar;
	
	

}
