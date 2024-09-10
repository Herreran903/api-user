package com.api_user.user.infra.user.in;

import com.api_user.user.app.user.dto.UserRequest;
import com.api_user.user.app.user.handler.IUserHandler;
import com.api_user.user.domain.user.exception.UserExceptionMessage;
import com.api_user.user.domain.util.GlobalExceptionMessage;
import com.api_user.user.infra.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@Import(SecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserHandler userHandler;

    @Test
    @WithMockUser(username = "Admin", roles = {"ROLE_ADMIN"})
    void shouldReturnBadRequestIfJsonNoValid() throws Exception {
        String expectedMessage = GlobalExceptionMessage.INVALID_JSON;

        mvc.perform(post("/users/warehouse-assistant/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":,}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage));
    }

    private static String getUserRequestBody(String name, String lastname, String dni, String phone, String birthdate, String email, String password, String role) {
        return String.format("{\"name\":\"%s\",\"lastname\":\"%s\",\"dni\":\"%s\",\"phone\":\"%s\",\"birthdate\":\"%s\",\"email\":\"%s\",\"password\":\"%s\",\"role\":\"%s\"}",
                name, lastname, dni, phone, birthdate, email, password, role);
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ROLE_ADMIN"})
    void shouldReturnBadRequestForInvalidUserName() throws Exception {
        String expectedMessage = GlobalExceptionMessage.INVALID_OBJECT;
        String expectedErrorMessage = UserExceptionMessage.EMPTY_NAME;

        String requestBody = getUserRequestBody("", "Lastname", "0123456789", "1234567890", "1990-01-01", "email@example.com", "password", "USER");

        mvc.perform(post("/users/warehouse-assistant/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage))
                .andExpect(jsonPath("$.errors[0].field").value("name"))
                .andExpect(jsonPath("$.errors[0].message").value(expectedErrorMessage));
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ROLE_ADMIN"})
    void shouldReturnBadRequestForInvalidUserLastname() throws Exception {
        String expectedMessage = GlobalExceptionMessage.INVALID_OBJECT;
        String expectedErrorMessage = UserExceptionMessage.EMPTY_LASTNAME;

        String requestBody = getUserRequestBody("Name", "", "0123456789", "1234567890", "1990-01-01", "email@example.com", "password", "USER");


        mvc.perform(post("/users/warehouse-assistant/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage))
                .andExpect(jsonPath("$.errors[0].field").value("lastname"))
                .andExpect(jsonPath("$.errors[0].message").value(expectedErrorMessage));
    }


    @Test
    @WithMockUser(username = "Admin", roles = {"ROLE_ADMIN"})
    void shouldReturnBadRequestIfBirthdateIsFuture() throws Exception {
        String expectedMessage = GlobalExceptionMessage.INVALID_OBJECT;
        String expectedErrorMessage = UserExceptionMessage.FUTURE_BIRTHDATE;

        String requestBody = getUserRequestBody("Name", "Lastname", "0123456789", "1234567890", "2100-01-01", "email@example.com", "password", "USER");

        mvc.perform(post("/users/warehouse-assistant/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage))
                .andExpect(jsonPath("$.errors[0].field").value("birthdate"))
                .andExpect(jsonPath("$.errors[0].message").value(expectedErrorMessage));
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ROLE_ADMIN"})
    void shouldReturnBadRequestForInvalidEmail() throws Exception {
        String expectedMessage = GlobalExceptionMessage.INVALID_OBJECT;
        String expectedErrorMessage = UserExceptionMessage.INVALID_EMAIL;

        String requestBody = getUserRequestBody("Name", "Lastname", "123456789", "1234567890", "1990-01-01", "invalid-email", "password", "USER");

        mvc.perform(post("/users/warehouse-assistant/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage))
                .andExpect(jsonPath("$.errors[0].field").value("email"))
                .andExpect(jsonPath("$.errors[0].message").value(expectedErrorMessage));
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ROLE_ADMIN"})
    void shouldReturnBadRequestForInvalidDni() throws Exception {
        String expectedMessage = GlobalExceptionMessage.INVALID_OBJECT;
        String expectedErrorMessage = UserExceptionMessage.INVALID_DNI;

        String requestBody = getUserRequestBody("Name", "Lastname", "invalid-dni", "1234567890", "1990-01-01", "email@example.com", "password", "USER");

        mvc.perform(post("/users/warehouse-assistant/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage))
                .andExpect(jsonPath("$.errors[0].field").value("dni"))
                .andExpect(jsonPath("$.errors[0].message").value(expectedErrorMessage));
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ROLE_ADMIN"})
    void shouldReturnBadRequestForInvalidPhone() throws Exception {
        String expectedMessage = GlobalExceptionMessage.INVALID_OBJECT;
        String expectedErrorMessage = UserExceptionMessage.INVALID_PHONE;

        String requestBody = getUserRequestBody("Name", "Lastname", "0123456789", "invalid-phone", "1990-01-01", "email@example.com", "password", "USER");

        mvc.perform(post("/users/warehouse-assistant/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(expectedMessage))
                .andExpect(jsonPath("$.errors[0].field").value("phone"))
                .andExpect(jsonPath("$.errors[0].message").value(expectedErrorMessage));
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ROLE_ADMIN"})
    void shouldReturnCreatedWhenUserIsSuccessfullyCreated() throws Exception {

        String requestBody = getUserRequestBody("Name", "Lastname", "0123456789", "1234567890", "1990-01-01", "email@example.com", "password", "USER");

        mvc.perform(post("/users/warehouse-assistant/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(userHandler, times(1)).createUserWarehouseAssistant(any(UserRequest.class));
    }
}