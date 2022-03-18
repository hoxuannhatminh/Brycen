package com.brycen.vn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "news")

public class New extends Base{
	


	@Column(name = "title", columnDefinition = "VARCHAR(255) NOT NULL")
	private String title;

	@Column(name = "content", columnDefinition = "VARCHAR(255) NOT NULL")
	private String content;

	@Column(name = "description", columnDefinition = "VARCHAR(2555) NOT NULL")
	private String description;

}
