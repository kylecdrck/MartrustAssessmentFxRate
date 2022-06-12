package com.martrust.fxratesapi.controller.form;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversionForm {

    @NotNull(message = "Invalid `from` value")
    @Pattern(regexp = "[A-Za-z]{3}", message = "Invalid `from` format")
    String from;

    @NotNull(message = "Invalid `to` value")
    @Pattern(regexp = "[A-Za-z]{3}", message = "Invalid `to` format")
    String to;

    @NotNull(message = "Invalid `amount` value")
    @Pattern(regexp = "\\d+(\\.\\d+)?", message = "Invalid `amount` format")
    String amount;

    @Pattern(regexp = "(\\d+){3}\\-(\\d+){2}\\-(\\d+){2}", message = "Invalid `date` format")
    String date;

    public void setFrom(String from) {
        this.from = from.toUpperCase();
    }

    public void setTo(String to) {
        this.to = to.toUpperCase();
    }
}
