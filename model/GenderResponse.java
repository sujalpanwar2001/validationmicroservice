package com.project.java.validationmicroservice.model;

/**
 * Represents the response model for gender prediction.
 */
public class GenderResponse {

    // The count of occurrences in the dataset
    Integer count;

    // The name associated with the gender prediction
    String name;

    // The predicted gender
    String gender;

    // The probability of the predicted gender
    Float probability;

    /**
     * Default constructor for GenderResponse.
     */
    public GenderResponse() {
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
     * Gets the name associated with the gender prediction.
     *
     * @return the name associated with the gender prediction
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name associated with the gender prediction.
     *
     * @param name the name associated with the gender prediction
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the predicted gender.
     *
     * @return the predicted gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the predicted gender.
     *
     * @param gender the predicted gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the probability of the predicted gender.
     *
     * @return the probability of the predicted gender
     */
    public Float getProbability() {
        return probability;
    }

    /**
     * Sets the probability of the predicted gender.
     *
     * @param probability the probability of the predicted gender
     */
    public void setProbability(Float probability) {
        this.probability = probability;
    }

    /**
     * Returns a string representation of the GenderResponse object.
     *
     * @return a string representation of the GenderResponse object
     */
    @Override
    public String toString() {
        return "GenderResponse{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", probability=" + probability +
                '}';
    }
}
