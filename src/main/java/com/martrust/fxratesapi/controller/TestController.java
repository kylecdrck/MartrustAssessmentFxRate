package com.martrust.fxratesapi.controller;

import com.martrust.fxratesapi.config.FXRatesApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
    @Autowired
    FXRatesApiConfig rateApiConfig;

    @GetMapping
    public Object get() {
        System.out.println("test");
        return rateApiConfig.getApiKey();
    }
}
