package com.tuanle.loyaltyprogram.controller;

import com.tuanle.loyaltyprogram.dto.RestEnvelope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping(value = "/health-check")
    public ResponseEntity<RestEnvelope> healthCheck(){
        return new ResponseEntity<>(new RestEnvelope("This is Membership Card Reward APIs"), HttpStatus.OK);
    }
}
