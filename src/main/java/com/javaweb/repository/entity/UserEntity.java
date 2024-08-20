package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(name ="name",nullable = false, unique = false)
 private String name;
 
 @Column(name = "fullname",nullable = false)
 private String fullnamel;
 
 @Column(name="status",nullable =  false)
 private Long status;
 
 @Column(name = "email")
 private String email;

 @ManyToMany(fetch = FetchType.LAZY)
 @JoinTable(name = "user_role",
           joinColumns = @JoinColumn(name="userid",nullable = false),
           inverseJoinColumns = @JoinColumn(name="roleid" ,nullable = false))
 private List<RoleEntity> roles = new ArrayList<>();
 
 
 
 
public List<RoleEntity> getRoles() {
	return roles;
}

public void setRoles(List<RoleEntity> roles) {
	this.roles = roles;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getFullnamel() {
	return fullnamel;
}

public void setFullnamel(String fullnamel) {
	this.fullnamel = fullnamel;
}

public Long getStatus() {
	return status;
}

public void setStatus(Long status) {
	this.status = status;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}
 
 
}
