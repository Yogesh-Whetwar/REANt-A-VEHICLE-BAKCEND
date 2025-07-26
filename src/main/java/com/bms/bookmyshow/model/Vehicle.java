package com.bms.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Vehicle {
    
@Id
@GeneratedValue(strategy =GenerationType.AUTO)
private Long id;

    private String name;
    private String type;
    private String fuel;
    private String gear;
    private Long price;
    private String description;
    private String city;
    private String vendorEmail;
    private String contact;
    private String imageUrl;
    private String pickupLocation;
    private String vendor;

}
