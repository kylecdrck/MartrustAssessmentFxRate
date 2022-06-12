package com.martrust.fxratesapi.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversionFromExchange {

    ConversionFromExchangeQuery query;
    String date;
    Double result;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ConversionFromExchangeQuery {
        String from;
        String to;
        Double amount;
    }
}
