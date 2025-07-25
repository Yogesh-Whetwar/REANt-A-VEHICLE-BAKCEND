package com.bms.bookmyshow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
  
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id; 

private String name;

@Column(unique = true, nullable = false)
private String email;

private String password;


}
