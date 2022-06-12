package com.martrust.fxratesapi.conversionTest;

import com.martrust.fxratesapi.controller.FXRatesController;
import com.martrust.fxratesapi.controller.form.ConversionForm;
import com.martrust.fxratesapi.model.APIResponse;
import com.martrust.fxratesapi.model.Conversion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetConversionTests {
    @Autowired
    FXRatesController fxRatesController;

    String from = "USD";
    String to = "PHP";
    Double amount = 1.0;
    String date = "2022-06-11";

    @Test
    public void getConversionNotNull() throws Exception {
        APIResponse<Conversion> conversionAPIResponse = fxRatesController.convertCurrencies(new ConversionForm(from, to, String.valueOf(amount), date));
        assert (conversionAPIResponse != null);
    }

    @Test
    public void getConversionSuccess() throws Exception {
        APIResponse<Conversion> conversionAPIResponse = fxRatesController.convertCurrencies(new ConversionForm(from, to, String.valueOf(amount), date));
        assert (conversionAPIResponse.getResultMessage().equals("Success."));
    }

    @Test
    public void getConversionDataNotNull() throws Exception {
        APIResponse<Conversion> conversionAPIResponse = fxRatesController.convertCurrencies(new ConversionForm(from, to, String.valueOf(amount), date));
        assert (conversionAPIResponse.getResultData() != null);
    }

    @Test
    public void getConversionMatchPassedData() throws Exception {
        APIResponse<Conversion> conversionAPIResponse = fxRatesController.convertCurrencies(new ConversionForm(from, to, String.valueOf(amount), date));
        Conversion conversion = conversionAPIResponse.getResultData();
        assert(conversion.getFromCurrency().equals(from));
        assert(conversion.getToCurrency().equals(to));
        assert(conversion.getAmount().equals(amount));
        assert(conversion.getConversionFromDate().equals(date));
    }
}
