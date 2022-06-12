package com.martrust.fxratesapi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Comparator;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conversion {

    String fromCurrency;
    String toCurrency;

    Double amount;
    Double convertedAmount;

    String conversionFromDate;
}
