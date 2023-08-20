package com.devs.customerserviceapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${address.service.url}")
    private String addressServiceUrl;

    public WebClient webClient() {
        WebClient webClient = WebClient.builder().baseUrl(addressServiceUrl).build();
        return webClient;
    }

}
