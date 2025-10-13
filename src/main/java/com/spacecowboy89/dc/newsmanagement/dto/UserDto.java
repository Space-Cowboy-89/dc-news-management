package com.spacecowboy89.dc.newsmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    @NotNull
    @Size(max=20)
    private String userCode;
    @NotNull
    @Size(max=20)
    private String name;
    @NotNull
    @Size(max=20)
    private String surname;
    @NotNull
    @Size(max =20)
    private String username;
    @NotNull
    @Size(max=20)
    private String password;
    @NotNull
    @Size(max = 20)
    private String email;
}
