package com.martrust.fxratesapi.controller;

import com.martrust.fxratesapi.controller.form.ConversionForm;
import com.martrust.fxratesapi.model.APIResponse;
import com.martrust.fxratesapi.model.Conversion;
import com.martrust.fxratesapi.model.Currencies;
import com.martrust.fxratesapi.service.FXRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FXRatesController {

    @Autowired
    private FXRatesService service;

    @GetMapping("/currencies")
    public APIResponse<List<Currencies>> getCurrencies() {
        return service.getCurrencies();
    }

    @GetMapping("/currencies/symbols")
    public APIResponse<List<String>> getCurrencySymbols() {
        return service.getCurrencySymbols();
    }

    @GetMapping("/convert")
    public APIResponse<Conversion> convertCurrencies(@Valid ConversionForm form) throws Exception {
        return service.getConversion(form);
    }
}
