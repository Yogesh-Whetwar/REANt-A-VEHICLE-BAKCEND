package com.bms.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.bookmyshow.model.Vehicle;

@Repository
public interface VehicleRepo  extends JpaRepository<Vehicle,Long> {
    
}
