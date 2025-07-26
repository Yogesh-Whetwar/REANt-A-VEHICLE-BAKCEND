package com.bms.bookmyshow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bms.bookmyshow.model.Vendor;
import com.bms.bookmyshow.service.VendorService;

@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "http://localhost:4200")
public class VendorController {
    @Autowired
private VendorService vendorService;

@PostMapping("/submitVendorForm")
public ResponseEntity<String> submitVendorForm(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam Long number,
        @RequestParam String address,
        @RequestParam MultipartFile aadhaar,
        @RequestParam String status
) {   
    System.out.println("Vendor controller called");
    try {
        String result = vendorService.saveVendor(name, email, number, address, aadhaar,"PENDING");
        return ResponseEntity.ok(result);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}  

@GetMapping("/getAllVendors")
public ResponseEntity<List<Vendor>> getVendors(){
    List<Vendor>vendors=vendorService.getVendors();
    return ResponseEntity.ok(vendors);
}  

@PutMapping("/changeStatus")
public ResponseEntity<Map<String,String>> changeStatus(@RequestBody Vendor vendor){  
    System.out.println("changeStatus called; " + vendor.getEmail());
   vendorService.changeStatus(vendor.getEmail(),vendor.getStatus());
    Map<String, String> response = new HashMap<>();
    response.put("message", "Status changed");
   return ResponseEntity.ok(response);
}

}
