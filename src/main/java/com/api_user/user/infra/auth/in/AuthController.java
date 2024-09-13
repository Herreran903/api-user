package com.api_user.user.infra.auth.in;

import com.api_user.user.app.auth.dto.AuthRequest;
import com.api_user.user.app.auth.dto.AuthResponse;
import com.api_user.user.app.auth.handler.IAuthHandler;
import com.api_user.user.infra.exception.ExceptionDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.api_user.user.infra.auth.util.AuthSwaggerMessages.*;
import static com.api_user.user.infra.util.SwaggerMessages.*;
import static com.api_user.user.infra.util.Urls.AUTH_URL;


@RestController
@RequestMapping(AUTH_URL)
@Validated
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class AuthController {

    private static final String LOGIN_URL = "/login";

    private final IAuthHandler authHandler;

    @Operation(
            summary = LOGIN_SUMMARY,
            description = LOGIN_DESCRIPTION,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = LOGIN_REQUEST_BODY_DESCRIPTION,
            content = @Content(
                    schema = @Schema(implementation = AuthRequest.class),
                    examples = {
                            @ExampleObject(value = LOGIN_REQUEST_EXAMPLE)
                    }
                )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_200, description = RESPONSE_200_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = CODE_400, description =  RESPONSE_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = CODE_401, description = RESPONSE_401_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = CODE_403, description = RESPONSE_403_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @PostMapping(LOGIN_URL)
    ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authHandler.authenticate(authRequest));
    }
}
