package com.example.entity;

import com.example.constant.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "members")
@Getter @Setter
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_id")
	private Long id;
	
	@Column(name = "member_name", nullable = false)
	private String name;
	
	@Column(name = "member_email", nullable =  false, unique = true)
	private String email;
	
	@Column(name = "member_password", nullable =  false)
	private String password;
	
	@Column(name = "member_zipcode")
	private String zipcode;
	
	@Column(name = "member_address1")
	private String address1;
	
	@Column(name = "member_address2")
	private String address2;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "member_role")
	private Role role;
}
