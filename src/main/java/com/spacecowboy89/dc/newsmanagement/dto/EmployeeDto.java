package com.spacecowboy89.dc.newsmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {
    @NotBlank
    @Size(max=20)
    private String name;

    @NotBlank
    @Size(min = 20, max=20)
    private String employeeCode;

    @NotBlank
    @Size(max=20)
    private String surname;

    @NotBlank
    @Size(max = 20)
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    @Size(max = 30)
    private String password;
}
