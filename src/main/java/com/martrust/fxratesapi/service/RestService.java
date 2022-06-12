package com.martrust.fxratesapi.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RestService {

    @SuppressWarnings("rawtypes")
    public ResponseEntity<Map> queryFromUrl(String url, Map<String, Object> queryParams, Map<String, String> headers) {
        return queryFromUrl(url, queryParams, headers, Map.class);
    }

    public <T> ResponseEntity<T> queryFromUrl(String url, Map<String, Object> queryParams, Map<String, String> headers, Class<T> clzz) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();

            if (!CollectionUtils.isEmpty(headers)) {
                headers.forEach(httpHeaders::add);
            }

            StringBuilder newUrl = new StringBuilder(url);

            if (!CollectionUtils.isEmpty(queryParams)) {
                var params = queryParams.entrySet()
                        .stream()
                        .filter(e -> e.getValue() != null)
                        .map((e) -> e.getKey() + "=" + e.getValue())
                        .collect(Collectors.joining("&"));
                newUrl.append("?").append(params);
            }

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(newUrl.toString(), HttpMethod.GET, new HttpEntity<>(httpHeaders), clzz);
        } catch (Exception e) {
            throw new RuntimeException("Failed. Invalid request");
        }
    }

}
