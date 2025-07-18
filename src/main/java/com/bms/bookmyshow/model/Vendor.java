package com.bms.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // basic information
    private int id;

    private String name;
    private String email;
    private String password;

    // whether approved by admin
    private boolean approved = false;

    // KYC fields for vendor verification
    private String panNumber;
    private String aadhaarNumber;
    private String licenseNumber;

    // document uploads
    private String panImagePath;
    private String aadhaarImagePath;
    private String licenseImagePath;
}
