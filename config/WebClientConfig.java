package com.project.java.validationmicroservice.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    // WebClient bean for interacting with "https://randomuser.me/api/"
    @Bean(name = "randomWebClient")
    public WebClient randomWebClient() {
        // Configure HttpClient for randomWebClient
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(2))
                        .addHandlerLast(new WriteTimeoutHandler(2)));

        // Configure ClientHttpConnector
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        // Build and return WebClient bean
        return WebClient.builder().clientConnector(connector).baseUrl("https://randomuser.me/api/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    // WebClient bean for interacting with "https://api.nationalize.io/"
    @Bean(name = "nationalityWebClient")
    public WebClient nationalityWebClient() {
        // Configure HttpClient for nationalityWebClient
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(1))
                        .addHandlerLast(new WriteTimeoutHandler(1)));

        // Configure ClientHttpConnector
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        // Build and return WebClient bean
        return WebClient.builder().clientConnector(connector).baseUrl("https://api.nationalize.io/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    // WebClient bean for interacting with "https://api.genderize.io/"
    @Bean(name = "genderWebClient")
    public WebClient genderWebClient() {
        // Configure HttpClient for genderWebClient
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(1))
                        .addHandlerLast(new WriteTimeoutHandler(1)));

        // Configure ClientHttpConnector
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        // Build and return WebClient bean
        return WebClient.builder().clientConnector(connector).baseUrl("https://api.genderize.io/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
