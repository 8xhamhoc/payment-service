package com.infybuzz.controller;

import com.infybuzz.request.PaymentDto;
import com.infybuzz.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/api/payments/deduct")
    public void deductBalance(@RequestBody PaymentDto paymentDto) throws Exception {
        paymentService.deduct(paymentDto);
    }
}
