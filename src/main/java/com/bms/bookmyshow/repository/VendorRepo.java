package com.bms.bookmyshow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.bookmyshow.model.Vendor;


@Repository
public interface VendorRepo extends JpaRepository<Vendor,Long> {

    Optional<Vendor>findByEmail(String email);
    
} 
