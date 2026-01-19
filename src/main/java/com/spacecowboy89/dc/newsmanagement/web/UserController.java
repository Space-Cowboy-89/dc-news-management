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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
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
            description = "It returns a user by a specific user code!",
            parameters = @Parameter(
                    name = "userCode",
                    description = "An user code.")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "User retrieved successfully."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Input user code not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not present.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal software error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping()
    public ResponseEntity<UserDto> getUserByUserCode(@RequestParam(value ="user-code") @NotBlank @Size(min = 20, max = 20) String userCode) {
        log.info("getUserByUserCode endpoint in execution!");

        UserDto userDto = UserMapper.INSTANCE.toUserDto(userService.retrieveUserByUserCode(userCode));

        log.info("getUserByUserCode endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "User by userCode!")
                .body(userDto);
    }


    @Operation(
            summary = "Add new user in the system.",
            description = "It adds new user in the system.",
            parameters = @Parameter(
                    name = "userDto",
                    description = "Parameter allows to insert a User from his values.")
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "add user successfully."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dto parameter not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Value not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping()
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
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
            summary = "Exist an user by an user code. ",
            description = "Service try to find a user by user code!",
            parameters = @Parameter(name = "userCode", description = "It's specific user code of a user.")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User exists by user Code!"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Input not valid.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Value not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "software internal error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )})
    @GetMapping("/exists")
    public ResponseEntity<Boolean> existUserByUserCode(@RequestParam(value = "user-code") @NotBlank @Size(min = 20, max = 20) String userCode) {
        log.info("existUserByUserCode endpoint in execution!");

        boolean ifExist= userService.existUserByUserCode(userCode);
        log.info("existUserByUserCode endpoint executed successfully!");
        return ResponseEntity
                .ok()
                .header("Header", "User exists!")
                .body(ifExist);
    }


}
