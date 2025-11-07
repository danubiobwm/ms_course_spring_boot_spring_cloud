package com.dev.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dev.hrpayroll.entities.Payment;
import com.dev.hrpayroll.entities.Worker;
import com.dev.hrpayroll.feignclients.WorkerFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    @CircuitBreaker(name = "hr-worker", fallbackMethod = "getPaymentFallback")
    public Payment getPayment(long workerId, int days) {

        Worker worker = workerFeignClient.findById(workerId).getBody();

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

    public Payment getPaymentFallback(long workerId, int days, Throwable t) {
        System.out.println("--- FALLBACK EXECUTADO! Motivo: " + t.getMessage() + " ---");


        Worker fallbackWorker = new Worker("Fallback User (Microservice OFF)", 400.0);
        return new Payment(fallbackWorker.getName(), fallbackWorker.getDailyIncome(), days);
    }
}