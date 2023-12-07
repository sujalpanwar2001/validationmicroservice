package com.project.java.validationmicroservice.repository;

import com.project.java.validationmicroservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing User entities in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users
     */
    List<User> findAll();

    /**
     * Counts the total number of users in the database.
     *
     * @return the total number of users
     */
    long count();
}
