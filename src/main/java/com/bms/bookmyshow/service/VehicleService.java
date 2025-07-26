package com.bms.bookmyshow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.bookmyshow.model.Vehicle;
import com.bms.bookmyshow.repository.VehicleRepo;

@Service
public class VehicleService {
    

@Autowired
private VehicleRepo vehicleRepo;

public void addVehicle(Vehicle vehicle) {  
    vehicleRepo.save(vehicle);
}

public List<Vehicle> getAll() {
  List<Vehicle>vehicles= vehicleRepo.findAll();
  if(vehicles.isEmpty()){
     throw new RuntimeException("No vehicles present");
  }  
  return vehicles;
}

public void deleteVehicle(Long id) {
   
  Optional<Vehicle>vehicle=vehicleRepo.findById(id);
  if(vehicle.isPresent()){
    vehicleRepo.deleteById(id);
  }
   throw new RuntimeException("Vehicle not found ");
}



}
