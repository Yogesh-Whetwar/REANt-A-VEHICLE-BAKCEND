package com.bms.bookmyshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.bookmyshow.model.Vehicle;
import com.bms.bookmyshow.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin("http://localhost:4200")
public class VehicleController {
    

@Autowired
private VehicleService vehicleService;

@PostMapping("/add")
public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle){
    vehicleService.addVehicle(vehicle);
    return ResponseEntity.ok(vehicle);
}  

@GetMapping("/getall")
public ResponseEntity<List<Vehicle>> getAll(){
    List<Vehicle>vehicles = vehicleService.getAll();
    return ResponseEntity.ok(vehicles);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteVehicle(@PathVariable Long id){
    vehicleService.deleteVehicle(id); 
    return ResponseEntity.ok("Deleted vehicle successfully");
}


}
