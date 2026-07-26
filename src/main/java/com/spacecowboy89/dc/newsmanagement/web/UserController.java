package com.spacecowboy89.dc.newsmanagement.web;

import com.spacecowboy89.dc.newsmanagement.dto.ErrorResponse;
import com.spacecowboy89.dc.newsmanagement.dto.UserDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import com.spacecowboy89.dc.newsmanagement.service.UserService;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@Validated
@Tag(name = "User", description = "It offers services about user!")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(
            summary = "Retrieve user by an userCode!",
            description = "Retrieve user with a specific userCode!"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "User retrieved successfully."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Input not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("{userCode}")
    public ResponseEntity<UserDto> getByUserCode(@Parameter(description = "user code", example = "xxxxxxxxxxxxxxxxxxxx") @PathVariable @NotBlank @Size(min = 20, max = 20) String userCode) {
        log.info("getUserByUserCode endpoint in execution!");

        UserDto userDto = UserMapper.INSTANCE.toUserDto(userService.retrieveUserByUserCode(userCode));

        log.info("getUserByUserCode endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "User by userCode!")
                .body(userDto);
    }


    @Operation(
            summary = "Add a user in the application .",
            description = "It adds new user in the application."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "add user successfully."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Input not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Input not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping()
    public ResponseEntity<UserDto> add(@Parameter(description = "Dto for user creation.") @RequestBody UserDto userDto) {
        log.info("addUser endpoint in execution!");

        User user = userService.saveUser(UserMapper.INSTANCE.toUser(userDto));
        userDto = UserMapper.INSTANCE.toUserDto(user);

        log.info("addUser endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "Add user!")
                .body(userDto);
    }


    @Operation(
            summary = "Verify a user with a specific user code existence.",
            description = "Verify a user with a specific user code existence."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User present in the application."),
            @ApiResponse(
                    responseCode = "400", description = "Input not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404", description = "User not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )})
    @GetMapping("/exists/{userCode}")
    public ResponseEntity<Boolean> existByUserCode(@Parameter(description = "user code", example = "xxxxxxxxxxxxxxxxxxxx") @PathVariable @NotBlank @Size(min = 20, max = 20) String userCode) {
        log.info("existUserByUserCode endpoint in execution!");

        boolean ifExist = userService.existUserByUserCode(userCode);
        log.info("existUserByUserCode endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "User exists!")
                .body(ifExist);
    }


    @Operation(
            summary = "Retrieve all user in soft-delete.",
            description = "Retrieve all user in soft-delete."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resources retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Input not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Users not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )})
    @GetMapping("/deleted")
    public ResponseEntity<List<UserDto>> getUsersDeleted() {
        log.info("getUsersDeleted endpoint called");

        List<UserDto> userDtos = UserMapper.INSTANCE.toUserDtoList(userService.retrieveUserDeleted());

        log.info("getUsersDeleted endpoint executed successfully.");
        return ResponseEntity
                .ok()
                .header("", "")
                .body(userDtos);
    }
}
