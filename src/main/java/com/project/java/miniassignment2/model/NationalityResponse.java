package com.project.java.miniassignment2.model;

import java.util.List;

/**
 * Represents the response model for nationality prediction.
 */
public class NationalityResponse {

    // The count of occurrences in the dataset
    Integer count;

    // The name associated with the nationality prediction
    String name;

    // List of countries with their probability of nationality
    List<Country> country;

    /**
     * Returns a string representation of the NationalityResponse object.
     *
     * @return a string representation of the NationalityResponse object
     */
    @Override
    public String toString() {
        return "NationalityResponse{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }

    /**
     * Gets the count of occurrences.
     *
     * @return the count of occurrences
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets the count of occurrences.
     *
     * @param count the count of occurrences
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Gets the name associated with the nationality prediction.
     *
     * @return the name associated with the nationality prediction
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name associated with the nationality prediction.
     *
     * @param name the name associated with the nationality prediction
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of countries with their probability of nationality.
     *
     * @return the list of countries with their probability of nationality
     */
    public List<Country> getCountry() {
        return country;
    }

    /**
     * Sets the list of countries with their probability of nationality.
     *
     * @param country the list of countries with their probability of nationality
     */
    public void setCountry(List<Country> country) {
        this.country = country;
    }

    /**
     * Represents a country with its country_id and probability of nationality.
     */
    public static class Country {

        // The country_id representing the country
        String country_id;

        // The probability of the nationality
        Float probability;

        /**
         * Returns a string representation of the Country object.
         *
         * @return a string representation of the Country object
         */
        @Override
        public String toString() {
            return "Country{" +
                    "country_id='" + country_id + '\'' +
                    ", probability=" + probability +
                    '}';
        }

        /**
         * Gets the country_id representing the country.
         *
         * @return the country_id representing the country
         */
        public String getCountry_id() {
            return country_id;
        }

        /**
         * Sets the country_id representing the country.
         *
         * @param country_id the country_id representing the country
         */
        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        /**
         * Gets the probability of the nationality.
         *
         * @return the probability of the nationality
         */
        public Float getProbability() {
            return probability;
        }

        /**
         * Sets the probability of the nationality.
         *
         * @param probability the probability of the nationality
         */
        public void setProbability(Float probability) {
            this.probability = probability;
        }
    }
}
