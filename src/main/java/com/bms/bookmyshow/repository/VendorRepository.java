package com.bms.bookmyshow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.bookmyshow.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    // for login
    Optional<Vendor> findByEmail(String email);

    // for admin - list of unapproved vendors
    List<Vendor> findByApprovedFalse();
}