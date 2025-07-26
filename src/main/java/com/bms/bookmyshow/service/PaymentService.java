package com.bms.bookmyshow.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
@Service
public class PaymentService {
    public String createOrder(int amount) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_SWDQwoRK0h2nA6", "O1iKQ5fPpkrROfJ2Tgl24Vd3");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // in paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_123456");

        Order order = razorpay.orders.create(orderRequest);
        return order.toString(); // Send this JSON to frontend
    }
}
