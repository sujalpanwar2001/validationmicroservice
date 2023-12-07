package com.project.java.validationmicroservice.sorting;

import com.project.java.validationmicroservice.entity.User;

import java.util.List;

/**
 * Context class that encapsulates a sorting strategy and delegates the sorting operation.
 */
public class UserSortContext {
    private SortingStrategy strategy;

    /**
     * Constructs a UserSortContext with a specified sorting strategy.
     *
     * @param strategy the sorting strategy to use
     */
    public UserSortContext(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Executes the sorting strategy on a list of users.
     *
     * @param users     the list of users to be sorted
     * @param sortOrder the order of sorting (e.g., "Even" or "Odd")
     * @return the sorted list of users
     */
    public List<User> executeStrategy(List<User> users, String sortOrder) {
        return strategy.sort(users, sortOrder);
    }
}
