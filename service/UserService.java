package com.project.java.validationmicroservice.service;

import com.project.java.validationmicroservice.entity.User;
import com.project.java.validationmicroservice.exceptions.CustomApiTimeoutException;
import com.project.java.validationmicroservice.exceptions.InvalidSortingParametersException;
import com.project.java.validationmicroservice.model.GenderResponse;
import com.project.java.validationmicroservice.model.NationalityResponse;
import com.project.java.validationmicroservice.model.RandomResponse;
import com.project.java.validationmicroservice.repository.CustomRequest;
import com.project.java.validationmicroservice.repository.UserRepository;
import com.project.java.validationmicroservice.sorting.SortByAgeStrategy;
import com.project.java.validationmicroservice.sorting.SortByNameStrategy;
import com.project.java.validationmicroservice.sorting.SortingStrategy;
import com.project.java.validationmicroservice.sorting.UserSortContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Service class for handling user-related operations.
 */
@Service
public class UserService {

    private final UserRepository userRepo;
    private final WebClient randomWebClient;
    private final WebClient nationalityWebClient;
    private final WebClient genderWebClient;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Autowired
    public UserService(UserRepository userRepo,
                       WebClient randomWebClient,
                       WebClient nationalityWebClient,
                       WebClient genderWebClient) {
        this.userRepo = userRepo;
        this.randomWebClient = randomWebClient;
        this.nationalityWebClient = nationalityWebClient;
        this.genderWebClient = genderWebClient;
    }

    /**
     * Calls the Nationality and Gender APIs asynchronously for a given name.
     *
     * @param name                 the name for which to call the APIs
     * @param nationalityWebClient the WebClient for the Nationality API
     * @param genderWebClient      the WebClient for the Gender API
     * @return a List containing NationalityResponse and GenderResponse
     * @throws CustomApiTimeoutException if API call times out
     */
    public List<Object> callApiNationalityAndApiGender(String name, WebClient nationalityWebClient, WebClient genderWebClient) {
        try {
            CompletableFuture<NationalityResponse> future1 = CompletableFuture.supplyAsync(() -> ApiService.callApiNationality(nationalityWebClient, name), executorService);
            CompletableFuture<GenderResponse> future2 = CompletableFuture.supplyAsync(() -> ApiService.callApiGender(genderWebClient, name), executorService);

            return future1.thenCombine(future2, List::of).join();
        } catch (Exception e) {
            throw new CustomApiTimeoutException("API call timed out");
        }
    }

    /**
     * Fetches random users from the Random User API and validates their nationality and gender.
     *
     * @param size the number of random users to fetch
     * @return a List of validated User objects
     * @throws CustomApiTimeoutException if API call times out
     */
    public List<User> fetchUsersFromApi(int size) {
        try {
            List<User> users = new ArrayList<>();
            RandomResponse randomResponse = ApiService.callApiRandomUser(randomWebClient, size);

            for (RandomResponse.UserData randomResponse1 : randomResponse.getResults()) {
                User user = new User();
                user.setName(randomResponse1.getName().getFirst() + " " + randomResponse1.getName().getLast());
                user.setAge(randomResponse1.getDob().getAge());
                user.setGender(randomResponse1.getGender());
                user.setNationality(randomResponse1.getNat());
                user.setDob(randomResponse1.getDob().getDate());
                long currentTimestamp = System.currentTimeMillis();
                Date currentDate = new Date(currentTimestamp);
                user.setDateCreated(currentDate);
                user.setDateModified(currentDate);

                List<Object> result = callApiNationalityAndApiGender(randomResponse1.getName().getFirst(), nationalityWebClient, genderWebClient);

                NationalityResponse nationality = (NationalityResponse) result.get(0);
                GenderResponse gender = (GenderResponse) result.get(1);

                boolean nationalityVerified = nationality.getCountry().stream()
                        .anyMatch(country -> country.getCountry_id().equals((user.getNationality())));

                boolean genderVerified = gender.getGender() != null && gender.getGender().equalsIgnoreCase(user.getGender());

                if (nationalityVerified && genderVerified) {
                    user.setVerificationStatus("VERIFIED");
                } else {
                    user.setVerificationStatus("TO_BE_VERIFIED");
                }
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            throw new CustomApiTimeoutException("API call timed out");
        }
    }

    /**
     * Adds a list of users to the database.
     *
     * @param users the list of users to add
     * @return the list of added users
     */
    public List<User> addUsers(List<User> users) {
        return userRepo.saveAll(users);
    }

    /**
     * Fetches users from the database based on pagination parameters.
     *
     * @param limit  the maximum number of records to fetch
     * @param offset the number of records to skip
     * @return a Page of User objects
     */
    public Page<User> fetchUsersFromDatabase(int limit, int offset) {
        Pageable pageable = new CustomRequest(limit, offset);
        return userRepo.findAll(pageable);
    }

    /**
     * Retrieves the total count of users in the database.
     *
     * @return the total count of users
     */
    public long getUsersCount() {
        return userRepo.count();
    }

    /**
     * Sorts a list of users based on the specified sorting parameters.
     *
     * @param users     the list of users to sort
     * @param sortType  the type of sorting (e.g., "Name" or "Age")
     * @param sortOrder the order of sorting (e.g., "asc" or "desc")
     * @return the sorted list of users
     * @throws InvalidSortingParametersException if sorting parameters are invalid
     */
    public List<User> sortUsers(List<User> users, String sortType, String sortOrder) {
        SortingStrategy sortingStrategy;

        if ("Name".equalsIgnoreCase(sortType)) {
            sortingStrategy = new SortByNameStrategy();
        } else if ("Age".equalsIgnoreCase(sortType)) {
            sortingStrategy = new SortByAgeStrategy();
        } else {
            // Handle invalid input
            throw new InvalidSortingParametersException("sortType can only be name or age");
        }

        UserSortContext context = new UserSortContext(sortingStrategy);
        return context.executeStrategy(users, sortOrder);
    }

    /**
     * Shuts down the executor service when the bean is destroyed.
     */
    @PreDestroy
    public void shutdownExecutorService() {
        executorService.shutdown();
    }
}
