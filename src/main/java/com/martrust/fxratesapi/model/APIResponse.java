package com.martrust.fxratesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class APIResponse<T> {
    long timestamp = Instant.now().getEpochSecond();
    String resultMessage;
    T resultData;

    public APIResponse(T resultData) {
        this.resultData = resultData;
    }

    public APIResponse(String resultMessage, T resultData) {
        this.resultMessage = resultMessage;
        this.resultData = resultData;
    }
}
