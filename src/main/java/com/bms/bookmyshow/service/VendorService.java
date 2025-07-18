package com.bms.bookmyshow.service;

import com.bms.bookmyshow.model.Vendor;
import com.bms.bookmyshow.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    // Create new vendor
    public void saveVendor(Vendor vendor) {
        if (vendor.getName() == null || vendor.getEmail() == null || vendor.getPassword() == null) {
            throw new RuntimeException("Vendor details cannot be empty");
        }
        vendorRepository.save(vendor);
    }

    // Get all vendors
    public List<Vendor> getAllVendors() {
        List<Vendor> vendors = vendorRepository.findAll();
        if (vendors.isEmpty()) {
            throw new RuntimeException("No vendors found");
        }
        return vendors;
    }

    // Get vendor by ID
    public Optional<Vendor> getVendorById(int id) {
        return vendorRepository.findById(id);
    }

    // Delete vendor
    public void deleteVendor(int id) {
        if (!vendorRepository.existsById(id)) {
            throw new RuntimeException("Vendor not found with id: " + id);
        }
        vendorRepository.deleteById(id);
    }

    // Update vendor
    public Vendor updateVendor(int id, Vendor vendor) {
        Optional<Vendor> existingVendorOpt = vendorRepository.findById(id);
        if (existingVendorOpt.isPresent()) {
            Vendor existingVendor = existingVendorOpt.get();
            existingVendor.setName(vendor.getName());
            existingVendor.setEmail(vendor.getEmail());
            existingVendor.setPassword(vendor.getPassword());
            existingVendor.setApproved(vendor.isApproved());
            return vendorRepository.save(existingVendor);
        } else {
            throw new RuntimeException("Vendor not found with id: " + id);
        }
    }

    // Get all vendors pending admin approval
    public List<Vendor> getPendingVendors() {
        return vendorRepository.findByApprovedFalse();
    }

    // Admin approves vendor
    public boolean approveVendor(int id) {
        Optional<Vendor> vendorOpt = vendorRepository.findById(id);
        if (vendorOpt.isPresent()) {
            Vendor vendor = vendorOpt.get();
            vendor.setApproved(true);
            vendorRepository.save(vendor);
            return true;
        }
        return false;
    }

    // Check login
    public boolean checkVendor(Vendor vendor) {
        if (vendor.getEmail() == null || vendor.getPassword() == null) {
            throw new RuntimeException("Email and password cannot be null");
        }

        List<Vendor> vendors = vendorRepository.findAll();
        for (Vendor existingVendor : vendors) {
            if (existingVendor.getEmail().equals(vendor.getEmail()) &&
                existingVendor.getPassword().equals(vendor.getPassword()) &&
                existingVendor.isApproved()) {
                return true;
            }
        }
        return false;
    }

    // Get vendor ID by email
    public int getVendorIdByEmail(String email) {
        if (email == null)
            throw new RuntimeException("Email is not provided");
        Optional<Vendor> vendor = vendorRepository.findByEmail(email);
        if (vendor.isPresent()) {
            return vendor.get().getId();
        } else {
            throw new RuntimeException("Email not found in vendor database");
        }
    }
}
