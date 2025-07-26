package com.bms.bookmyshow.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bms.bookmyshow.model.Vendor;
import com.bms.bookmyshow.repository.VendorRepo;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService  {
     
    @Autowired
    private VendorRepo vendorRepository;
 public String saveVendor(
        String name,
        String email,
        Long number,
        String address,
        MultipartFile aadhaar,
        String status
    ) throws IOException {

        if (vendorRepository.findByEmail(email).isPresent()) {
            return "Application already submitted.";
        }

        // Save file 
        System.out.println("VendorService called");
        String uploadDir = "/Users/yogeshwhetwar/Desktop/BOOK-MY-SHOW/uploads/aadhaar/";
        String fileName = UUID.randomUUID() + "_" + aadhaar.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.copy(aadhaar.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Vendor vendor = new Vendor();
        vendor.setName(name);
        vendor.setEmail(email);
        vendor.setNumber(number);
        vendor.setAddress(address);
        vendor.setAadhaar(filePath.toString());
        vendor.setStatus(status);
        vendorRepository.save(vendor);
        return "Form submitted successfully!";
    }
 public List<Vendor> getVendors() {
    // TODO Auto-generated method stub  
    java.util.List<Vendor>vendors=vendorRepository.findAll();
    if(vendors.isEmpty()){
       throw new UnsupportedOperationException("No vendors present");
 }   
 return vendors;
    }
 public void changeStatus(String email,String status) {
    
 Optional<Vendor> vendor=vendorRepository.findByEmail(email);
if(vendor.isPresent()){
    Vendor currentVendor=vendor.get();
    // currentVendor.setEmail(email);
    currentVendor.setStatus(status);
    vendorRepository.save(currentVendor);
}else{
    throw new RuntimeException("Vednor with this email Id is not present: "+email);
}
    
 }
   


}
