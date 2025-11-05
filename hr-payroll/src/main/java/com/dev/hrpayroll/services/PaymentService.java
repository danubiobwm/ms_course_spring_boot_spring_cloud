package com.dev.hrpayroll.services;

import org.springframework.stereotype.Service;

import com.dev.hrpayroll.entities.Payment;

@Service
public class PaymentService {

  public Payment getPayment(long workerId, int days) {
    // Simulated logic for payment calculation
    return new Payment("John Doe", 100.0, days);
  }

}
