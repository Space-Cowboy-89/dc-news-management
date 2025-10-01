package com.spacecowboy89.dc.newsmanagement.web;

import com.spacecowboy89.dc.newsmanagement.dto.UserDto;
import com.spacecowboy89.dc.newsmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @Operation(
            summary = "Retrieve user by userCode!",
            description =  "the endpoint return a user by an user code"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User retrieves successfuly"),
            @ApiResponse(responseCode = "400", description = ""),
            @ApiResponse(responseCode = "404", description = "User doesn't present.")
    })
    @GetMapping("/userByUserCode")
    public ResponseEntity<UserDto> getUserByUserCode(@RequestParam String userCode) {
        log.info("getUserByUserCode in execution!");
        return ResponseEntity
                .ok()
                .header("Header", "User by userCode!")
                .body(userService.retrieveUserByUserCode(userCode));
    }



    @Operation(
            summary = "Add user in db",
            description = "Add user in db"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description="add user successfuly"),
            @ApiResponse(responseCode = "400",description="")
    })
    @PostMapping("/user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        log.info("Add user in execution!");
        return ResponseEntity
                .ok()
                .header("Header", "Add user!")
                .body(userService.saveUser(userDto));
    }



    //TODO creare endpoint existUserByUserCode
    //TODO mergiare develop in feature/newsService
}
