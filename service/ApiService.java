package com.project.java.validationmicroservice.service;

import com.project.java.validationmicroservice.model.GenderResponse;
import com.project.java.validationmicroservice.model.NationalityResponse;
import com.project.java.validationmicroservice.model.RandomResponse;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Service class for making API calls to external services.
 */
public class ApiService {

    /**
     * Calls the Random User API to retrieve random user data.
     *
     * @param webClient the WebClient instance for making HTTP requests
     * @param size      the number of random users to retrieve
     * @return a RandomResponse containing random user data
     */
    public static RandomResponse callApiRandomUser(WebClient webClient, int size) {
        // Construct the URL for the Random User API
        String url = "https://randomuser.me/api/" + "?results=" + size;

        // Use WebClient to make a GET request and retrieve the response as RandomResponse
        RandomResponse randomResponse = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(RandomResponse.class)
                .block();

        return randomResponse;
    }

    /**
     * Calls the Nationalize API to retrieve nationality information for a given name.
     *
     * @param webClient the WebClient instance for making HTTP requests
     * @param name      the name for which nationality information is requested
     * @return a NationalityResponse containing nationality information
     */
    public static NationalityResponse callApiNationality(WebClient webClient, String name) {
        // Construct the URL for the Nationalize API
        String url = "https://api.nationalize.io/" + "?name=" + name;

        // Use WebClient to make a GET request and retrieve the response as NationalityResponse
        NationalityResponse nationalityResponse = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(NationalityResponse.class)
                .block();

        return nationalityResponse;
    }

    /**
     * Calls the Genderize API to retrieve gender information for a given name.
     *
     * @param webClient the WebClient instance for making HTTP requests
     * @param name      the name for which gender information is requested
     * @return a GenderResponse containing gender information
     */
    public static GenderResponse callApiGender(WebClient webClient, String name) {
        // Construct the URL for the Genderize API
        String url = "https://api.genderize.io/" + "?name=" + name;

        // Use WebClient to make a GET request and retrieve the response as GenderResponse
        GenderResponse genderResponse = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(GenderResponse.class)
                .block();

        return genderResponse;
    }
}
