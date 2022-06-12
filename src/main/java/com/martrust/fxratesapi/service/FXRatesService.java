package com.martrust.fxratesapi.service;

import com.martrust.fxratesapi.config.FXRatesApiConfig;
import com.martrust.fxratesapi.controller.form.ConversionForm;
import com.martrust.fxratesapi.model.APIResponse;
import com.martrust.fxratesapi.model.Conversion;
import com.martrust.fxratesapi.model.ConversionFromExchange;
import com.martrust.fxratesapi.model.Currencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FXRatesService {
    @Autowired
    private FXRatesApiConfig rateApiConfig;

    @Autowired
    private RestService restService;

    private Set<String> recentCurrencySymbols;

    public APIResponse<List<Currencies>> getCurrencies() {
        return new APIResponse<>("Success.", getCurrenciesFromExchangeAPI());
    }

    public APIResponse<List<String>> getCurrencySymbols() {
        List<String> currencySymbols = getCurrenciesFromExchangeAPI().stream().map(Currencies::getSymbol).collect(Collectors.toList());
        return new APIResponse<>("Success.", currencySymbols);
    }

    public APIResponse<Conversion> getConversion(ConversionForm form) throws Exception {
        loadCurrentCurrencies();

        validateCurrencySymbol(form.getFrom());
        validateCurrencySymbol(form.getTo());
        validateDate(form.getDate());

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("to", form.getTo());
        queryParams.put("from", form.getFrom());
        queryParams.put("amount", form.getAmount());
        queryParams.put("date", form.getDate());

        var conversionResponse = restService
                .queryFromUrl(rateApiConfig.getBaseUrl() + "convert",
                        queryParams,
                        Map.of("apiKey", rateApiConfig.getApiKey()), ConversionFromExchange.class);

        Conversion conv = null;
        if (conversionResponse != null && conversionResponse.getBody() != null) {
            ConversionFromExchange cex = conversionResponse.getBody();

            conv = new Conversion(cex.getQuery().getFrom(),
                    cex.getQuery().getTo(),
                    cex.getQuery().getAmount(),
                    cex.getResult(),
                    cex.getDate());
        }

        return new APIResponse<>("Success.", conv);
    }

    private List<Currencies> getCurrenciesFromExchangeAPI() {
        var currenciesResponse = restService
                .queryFromUrl(rateApiConfig.getBaseUrl() + "symbols",
                        Collections.emptyMap(),
                        Map.of("apiKey", rateApiConfig.getApiKey()));

        if (currenciesResponse != null && currenciesResponse.getBody() != null) {
            var symbols = (Map<?, ?>) currenciesResponse.getBody().get("symbols");
            var symbolList = symbols.entrySet().stream()
                    .map(e -> new Currencies((String) e.getKey(), (String) e.getValue()))
                    .sorted()
                    .collect(Collectors.toList());
            updateRecentCurrencies(symbolList);
            return symbolList;
        }
        return Collections.emptyList();
    }

    private void validateCurrencySymbol(String symbol) throws Exception {
        if (!recentCurrencySymbols.contains(symbol))
            throw new Exception("Failed. Invalid currency symbol of (" + symbol + ")");
    }

    private void validateDate(String date) throws Exception {
        if (date != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            try {
                LocalDate.parse(date, dateFormatter);
            } catch (DateTimeParseException e) {
                throw new Exception("Failed. Invalid `date` value (" + date + ")");
            }
        }
    }

    /**
     * To reduce API call limit consumption, update the recent current symbols set everytime the {@code getCurrenciesFromExchangeAPI} is called
     *
     * @param currencies List of currencies from Exchange API
     */
    private void updateRecentCurrencies(List<Currencies> currencies) {
        this.recentCurrencySymbols = currencies.stream().map(Currencies::getSymbol).collect(Collectors.toSet());
    }

    /**
     * Load the recent list of Currencies from the {@code getCurrenciesFromExchangeAPI}
     */
    private void loadCurrentCurrencies() {
        if (recentCurrencySymbols == null || recentCurrencySymbols.isEmpty()) {
            getCurrenciesFromExchangeAPI();
        }
    }
}
