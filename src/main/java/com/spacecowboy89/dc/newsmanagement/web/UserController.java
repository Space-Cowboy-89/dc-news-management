package com.spacecowboy89.dc.newsmanagement.web;

import com.spacecowboy89.dc.newsmanagement.dto.UserDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import com.spacecowboy89.dc.newsmanagement.service.UserService;
import com.spacecowboy89.dc.newsmanagement.utility.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(
            summary = "Retrieve user by userCode!",
            description = "the endpoint return a user by an user code"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User retrieves successfuly"),
            @ApiResponse(responseCode = "400", description = ""),
            @ApiResponse(responseCode = "404", description = "User doesn't present.")
    })
    @GetMapping("/userByUserCode")
    public ResponseEntity<UserDto> getUserByUserCode(@RequestParam @NotBlank @Size(min = 20,max=20) String userCode) {
        log.info("getUserByUserCode in execution!");

        UserDto userDto = UserMapper.INSTANCE.toUserDto(userService.retrieveUserByUserCode(userCode));

        return ResponseEntity
                .ok()
                .header("Header", "User by userCode!")
                .body(userDto);
    }


    @Operation(
            summary = "Add user in db",
            description = "Add user in db"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "add user successfuly"),
            @ApiResponse(responseCode = "400", description = "")
    })
    @PostMapping("/user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        log.info("Add user in execution!");

        User user = userService.saveUser(UserMapper.INSTANCE.toUser(userDto) );
        return ResponseEntity
                .ok()
                .header("Header", "Add user!")
                .body(UserMapper.INSTANCE.toUserDto(user));
    }


    @Operation(
            summary = "Exist user by userCode! ",
            description = "Service try to find a user by user code!"
    )
    @ApiResponses({
           @ApiResponse(responseCode = "200", description ="User exists by user Code!" ),
            @ApiResponse(responseCode = "400", description ="" ),
            @ApiResponse(responseCode = "404", description ="" ),
    })
    @GetMapping("/existsUserByUserCode")
    public ResponseEntity<Boolean> existUserByUserCode(@RequestParam @NotBlank @Size(min = 20,max=20) String userCode) {
        log.info("existUserByUserCode in execution!");
        return ResponseEntity
                .ok()
                .header("Header", "User exists!")
                .body(userService.existUserByUserCode(userCode));
    }


}
