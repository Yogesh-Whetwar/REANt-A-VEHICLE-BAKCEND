package com.bms.bookmyshow.controller;

import com.bms.bookmyshow.model.Vendor;
import com.bms.bookmyshow.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendors")
@CrossOrigin(origins = "http://localhost:4200")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // Registering new vendor with KYC & approval pending
    @PostMapping("/register")
    public ResponseEntity<String> registerVendor(@RequestBody Vendor vendor) {
        vendor.setApproved(false); // Initially not approved
        vendorService.saveVendor(vendor);
        return ResponseEntity.ok("Vendor registered successfully. Pending admin approval.");
    }

    // Get all vendors
    @GetMapping("/getAll")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    // Get vendor by ID
    @GetMapping("/get")
    public ResponseEntity<Vendor> getVendorById(@RequestParam int id) {
        Optional<Vendor> vendor = vendorService.getVendorById(id);
        return vendor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete vendor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable int id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok("Vendor deleted successfully.");
    }

    // Update vendor
    @PutMapping("/update/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable int id, @RequestBody Vendor vendor) {
        Vendor updatedVendor = vendorService.updateVendor(id, vendor);
        return ResponseEntity.ok(updatedVendor);
    }

    // Admin: Get all unapproved vendors (for KYC approval)
    @GetMapping("/pending")
    public ResponseEntity<List<Vendor>> getPendingVendors() {
        return ResponseEntity.ok(vendorService.getPendingVendors());
    }

    // Admin: Approve vendor after KYC
    @PutMapping("/approve/{id}")
    public ResponseEntity<String> approveVendor(@PathVariable int id) {
        boolean result = vendorService.approveVendor(id);
        if (result) {
            return ResponseEntity.ok("Vendor approved successfully.");
        } else {
            return ResponseEntity.status(404).body("Vendor not found.");
        }
    }

    // For login check
    @PostMapping("/check")
    public boolean checkVendor(@RequestBody Vendor vendor) {
        return vendorService.checkVendor(vendor);
    }

    // Get vendor profile ID by email
    @GetMapping("/getProfileIdByEmail")
    public ResponseEntity<Integer> getVendorIdByEmail(@RequestParam String email) {
        int id = vendorService.getVendorIdByEmail(email);
        return ResponseEntity.ok(id);
    }
}