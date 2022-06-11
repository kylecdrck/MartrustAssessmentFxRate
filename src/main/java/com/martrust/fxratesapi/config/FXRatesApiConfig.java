package com.martrust.fxratesapi.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Data
@Configuration
@EnableEncryptableProperties
@ConfigurationProperties(prefix = "fxrates")
public class FXRatesApiConfig {
    private String apiKey;
}
