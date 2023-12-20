package com.project.java.miniassignment2.sorting;

import com.project.java.miniassignment2.entity.User;

import java.util.List;

/**
 * Interface defining a sorting strategy for a list of users.
 */
public interface SortingStrategy {

    /**
     * Sorts a list of users based on a specific strategy.
     *
     * @param users     the list of users to sort
     * @param sortOrder the order of sorting (e.g., "Even" or "Odd")
     * @return the sorted list of users
     */
    List<User> sort(List<User> users, String sortOrder);
}
