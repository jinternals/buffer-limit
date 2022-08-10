package com.jinternals.bufferlimit.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.unit.DataSize;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfiguration {
    @Value("${client.max-in-memory-size}")
    private DataSize dataSize;

    @Bean
    WebClient.Builder builder(){
        return WebClient.builder()
                .codecs(configurer -> configurer .defaultCodecs().maxInMemorySize((int)dataSize.toBytes()))
                .baseUrl("http://localhost:3001")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }
}
