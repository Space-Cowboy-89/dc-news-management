package com.spacecowboy89.dc.newsmanagement.persistence.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialEntity extends BaseEntity{
    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
