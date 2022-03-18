package com.brycen.vn.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
@Table(name = "users")

public class User extends Base {
	

	@Column(name = "username", columnDefinition = "varchar(30) NOT NULL")
	private String username;

	@Column(name = "password", columnDefinition = "varchar(30) NOT NULL")
	private String password;

	@ManyToMany
	@JoinTable(name = "user_group_relation", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "user_group_id"))
	private List<UserGroup> userGroup = new ArrayList<UserGroup>();
	
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
}
