package com.nagarro.java.miniassignment2.controllertest;

import com.nagarro.java.miniassignment2.controller.ApiController;
import com.nagarro.java.miniassignment2.entity.User;
import com.nagarro.java.miniassignment2.exceptions.GlobalExceptionHandler;
import com.nagarro.java.miniassignment2.exceptions.RequestParameterValidationException;
import com.nagarro.java.miniassignment2.model.UserPageResponse;
import com.nagarro.java.miniassignment2.service.RequestValidationService;
import com.nagarro.java.miniassignment2.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ApiControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private RequestValidationService validationService;

    @InjectMocks
    private ApiController apiController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(apiController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Ensure exception handling is set up
                .build();
    }


    @Test
    void testAddUser() throws Exception {
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userService.fetchUsersFromApi(anyInt())).thenReturn(mockUsers);
        when(userService.addUsers(anyList())).thenReturn(mockUsers);

        mockMvc.perform(post("/api")
                        .param("size", "2"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddUserInvalidSize() throws Exception {
        mockMvc.perform(post("/api")
                        .param("size", "10"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RequestParameterValidationException))
                .andExpect(result -> assertEquals("Size should be in the range 1 to 5",
                        result.getResolvedException().getMessage()));
    }


    @Test
    void testGetUsers() throws Exception {
        // Assuming fetchUsersFromDatabase returns a UserPageResponse

        List<User> userList = Arrays.asList(new User(), new User());
        UserPageResponse userPageResponse = new UserPageResponse(userList, new UserPageResponse.PageInfo(true, false, 10));
        Page<User> userPage = new PageImpl<>(userList);
        when(userService.fetchUsersFromDatabase(anyInt(), anyInt())).thenReturn(userPage);
        when(userService.getUsersCount()).thenReturn((long) 10);
        String sortType = "name";
        String sortOrder = "even";
        when(userService.sortUsers(userList, sortType, sortOrder)).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api")
                        .param("limit", "2")
                        .param("offset", "0")
                        .param("sortType", "name")
                        .param("sortOrder", "even"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageInfo.hasNextPage").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageInfo.hasPreviousPage").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageInfo.total").value(10));


    }


    @Test
    void testGetUsersWithInvalidLimit() throws Exception {
        // Mock the validation service to throw a RequestParameterValidationException
        doThrow(RequestParameterValidationException.class).when(validationService).validateQueryParameters(any());


        // Use assertThrows to verify that the exception is thrown
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api")
                .param("limit", "-2")
                .param("offset", "1")
                .param("sortType", "name")
                .param("sortOrder", "odd");

        assertThrows(Exception.class, () -> mockMvc.perform(requestBuilder));

    }

    @Test
    void testGetUsersWithInvalidOffset() throws Exception {
        // Mock the validation service to throw a RequestParameterValidationException
        doThrow(RequestParameterValidationException.class).when(validationService).validateQueryParameters(any());

        // Use assertThrows to verify that the exception is thrown
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api")
                .param("limit", "2")
                .param("offset", "-1")
                .param("sortType", "name")
                .param("sortOrder", "even");

        assertThrows(Exception.class, () -> mockMvc.perform(requestBuilder));

    }

    @Test
    void testGetUsersWithInvalidSortType() throws Exception {
        // Mock the validation service to throw a RequestParameterValidationException
        doThrow(RequestParameterValidationException.class).when(validationService).validateQueryParameters(any());

        // Use assertThrows to verify that the exception is thrown
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api")
                .param("limit", "2")
                .param("offset", "1")
                .param("sortType", "Invalid")
                .param("sortOrder", "even");

        assertThrows(Exception.class, () -> mockMvc.perform(requestBuilder));

    }

    @Test
    void testGetUsersWithInvalidSortOrder() throws Exception {
        // Mock the validation service to throw a RequestParameterValidationException
        doThrow(RequestParameterValidationException.class).when(validationService).validateQueryParameters(any());

        // Use assertThrows to verify that the exception is thrown
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api")
                .param("limit", "2")
                .param("offset", "1")
                .param("sortType", "name")
                .param("sortOrder", "Invalid");

        assertThrows(Exception.class, () -> mockMvc.perform(requestBuilder));

    }


}
