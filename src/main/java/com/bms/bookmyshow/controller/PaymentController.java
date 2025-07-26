package com.bms.bookmyshow.controller;

import org.springframework.web.bind.annotation.*;

import com.bms.bookmyshow.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createOrder")
    public String createOrder(@RequestParam int amount) {
        try {
            return paymentService.createOrder(amount);
        } catch (Exception e) {
            return "{\"error\":\"Order creation failed\"}";
        }
    }
}
