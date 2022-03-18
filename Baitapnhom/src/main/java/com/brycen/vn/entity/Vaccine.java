package com.brycen.vn.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccine")

@EnableTransactionManagement

public class Vaccine extends Base{
	
	

	@Column(name = "code", columnDefinition = "VARCHAR(255) NOT NULL ",unique = true)
	private String code;

	@Column(name = "name", columnDefinition = "VARCHAR(255) NOT NULL ")
	private String name;

	@Column(name = "description", columnDefinition = "VARCHAR(255) NOT NULL")
	private String description;

	@Column(name = "expriration_date", columnDefinition = "DATETIME NOT NULL")
	private String exprirationDate;

	@Column(name = "manufacturing_date", columnDefinition = "DATETIME NOT NULL")
	private String manufacturingDate;

	@Column(name = "producer", columnDefinition = "VARCHAR(255) NOT NULL")
	private String producer;

	@OneToMany(mappedBy = "vaccine")
	private List<InjectionCalendar> injectionCalendars;

	@ManyToOne
	@JoinColumn(name = "category_vaccine_id")
	private CategoryVaccine categoryVaccine;
	
	


}
