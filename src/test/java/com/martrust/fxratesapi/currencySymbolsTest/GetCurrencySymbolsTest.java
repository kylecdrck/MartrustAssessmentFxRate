package com.martrust.fxratesapi.currencySymbolsTest;

import com.martrust.fxratesapi.controller.FXRatesController;
import com.martrust.fxratesapi.model.APIResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GetCurrencySymbolsTest {
    @Autowired
    FXRatesController fxRatesController;

    @Test
    public void getCurrencySymbolsNotNull() {
        APIResponse<List<String>> currenciesResponse = fxRatesController.getCurrencySymbols();
        assert (currenciesResponse != null);
    }

    @Test
    public void getCurrencySymbolsSuccess() {
        APIResponse<List<String>> currenciesResponse = fxRatesController.getCurrencySymbols();
        assert (currenciesResponse.getResultMessage().equals("Success."));
    }

    @Test
    public void getCurrencySymbolsDataNotEmpty() {
        APIResponse<List<String>> currenciesResponse = fxRatesController.getCurrencySymbols();
        assert (!currenciesResponse.getResultData().isEmpty());
    }
}
