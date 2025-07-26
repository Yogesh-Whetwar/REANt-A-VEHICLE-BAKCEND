package com.bms.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
 private String customerName;
  private String customerEmail;
  private String customerPhone;
  private Double orderAmount;
  // Generate getters and seters

   

}
