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
    @NotNull
    @Size(max=20)
    private String name;

    @NotNull
    @Size(max=20)
    private String surname;

    @NotBlank
    @Size(max = 20)
    private String email;

    @NotNull
    @Size(max = 30)
    private String password;
}
