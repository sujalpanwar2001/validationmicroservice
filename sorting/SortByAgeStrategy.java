package com.project.java.validationmicroservice.sorting;

import com.project.java.validationmicroservice.entity.User;
import com.project.java.validationmicroservice.exceptions.RequestParameterValidationException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of SortingStrategy for sorting users by age.
 */
public class SortByAgeStrategy implements SortingStrategy {

    /**
     * Sorts a list of users based on their age.
     *
     * @param users     the list of users to sort
     * @param sortOrder the order of sorting (e.g., "Even" or "Odd")
     * @return the sorted list of users
     * @throws RequestParameterValidationException if sortOrder is invalid
     */
    @Override
    public List<User> sort(List<User> users, String sortOrder) {
        Comparator<User> comparator;

        if ("Even".equalsIgnoreCase(sortOrder)) {
            comparator = Comparator.comparingInt(user -> user.getAge() % 2);
        } else if ("Odd".equalsIgnoreCase(sortOrder)) {
            comparator = Comparator.comparingInt(user -> user.getAge() % 2);
            comparator = Collections.reverseOrder(comparator);
        } else {
            // Handle invalid sortOrder
            throw new RequestParameterValidationException("Sort order can only be odd or even");
        }

        return users.stream().sorted(comparator).collect(Collectors.toList());
    }
}
