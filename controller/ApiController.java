package com.project.java.validationmicroservice.controller;

import com.project.java.validationmicroservice.entity.User;
import com.project.java.validationmicroservice.exceptions.RequestParameterValidationException;
import com.project.java.validationmicroservice.model.UserPageResponse;
import com.project.java.validationmicroservice.service.RequestValidationService;
import com.project.java.validationmicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final RequestValidationService validationService;
    private UserService userService;

    @Autowired
    public ApiController(UserService userService, RequestValidationService validationService) {
        this.userService = userService;
        this.validationService = validationService;
    }

    // Endpoint to add users with a specified size
    @PostMapping
    public List<User> addUser(@RequestParam(name = "size", defaultValue = "1") Integer size) {
        // Validate the size parameter
        if (size < 1 || size > 5)
            throw new RequestParameterValidationException(("Size should be in the range 1 to 5"));

        // Fetch users from the external API and add them to the database
        List<User> users = userService.fetchUsersFromApi(size);
        return userService.addUsers(users);
    }

    // Endpoint to get users with optional query parameters
    @GetMapping
    public UserPageResponse getUsers(@RequestParam Map<String, String> queryParams) {
        // Validate the query parameters
        validationService.validateQueryParameters(queryParams);

        // Extract limit and offset parameters
        String limit = queryParams.get("limit");
        String offset = queryParams.get("offset");


        int _limit = Integer.parseInt(limit);
        int _offset = Integer.parseInt(offset);

        // Fetch users from the database based on limit and offset
        List<User> users = userService.fetchUsersFromDatabase(_offset, _limit).getContent();
        long totalUsers = userService.getUsersCount();

        // Extract and apply sorting parameters
        String sortType = queryParams.get("sortType");
        String sortOrder = queryParams.get("sortOrder");
        users = userService.sortUsers(users, sortType, sortOrder);

        // Determine if there are next and previous pages
        boolean hasNextPage = (totalUsers - (_offset + _limit)) > 0;
        boolean hasPreviousPage = _offset > 0;

        // Create PageInfo object for pagination information
        UserPageResponse.PageInfo pageInfo = new UserPageResponse.PageInfo(hasNextPage, hasPreviousPage, totalUsers);

        // Create and return UserPageResponse
        return new UserPageResponse(users, pageInfo);
    }
}
