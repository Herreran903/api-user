package com.api_user.user.infra.user.in;

import com.api_user.user.app.user.dto.UserRequest;
import com.api_user.user.app.user.handler.IUserHandler;
import com.api_user.user.infra.exception.ExceptionDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.api_user.user.infra.user.util.UserSwaggerMessages.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final IUserHandler userHandler;

    public UserController(IUserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Operation(
            summary = CREATE_WAREHOUSE_ASSISTANT_SUMMARY,
            description = CREATE_WAREHOUSE_ASSISTANT_DESCRIPTION,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = CREATE_WAREHOUSE_ASSISTANT_REQUEST_BODY_DESCRIPTION,
                    content = @Content(
                            schema = @Schema(implementation = UserRequest.class),
                            examples = {
                                    @ExampleObject(value = USER_REQUEST_EXAMPLE)
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = "400", description = RESPONSE_400_DESCRIPTION,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))
            ),
            @ApiResponse(responseCode = "500", description = RESPONSE_500_DESCRIPTION,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))
            )
    })
    @PostMapping("/warehouse-assistant/register/")
    ResponseEntity<Void> createUser(@Valid @RequestBody UserRequest userRequest) {
        userHandler.createUserWarehouseAssistant(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
