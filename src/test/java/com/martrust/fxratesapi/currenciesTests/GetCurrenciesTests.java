package com.martrust.fxratesapi.currenciesTests;

import com.martrust.fxratesapi.controller.FXRatesController;
import com.martrust.fxratesapi.model.APIResponse;
import com.martrust.fxratesapi.model.Currencies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GetCurrenciesTests {
    @Autowired
    FXRatesController fxRatesController;

    @Test
    public void getCurrenciesNotNull() {
        APIResponse<List<Currencies>> currenciesResponse = fxRatesController.getCurrencies();
        assert (currenciesResponse != null);
    }

    @Test
    public void getCurrenciesSuccess() {
        APIResponse<List<Currencies>> currenciesResponse = fxRatesController.getCurrencies();
        assert (currenciesResponse.getResultMessage().equals("Success."));
    }

    @Test
    public void getCurrenciesDataNotEmpty() {
        APIResponse<List<Currencies>> currenciesResponse = fxRatesController.getCurrencies();
        assert (!currenciesResponse.getResultData().isEmpty());
    }
}
