package com.spacecowboy89.dc.newsmanagement.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user",
        uniqueConstraints = {@UniqueConstraint(columnNames = "user_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_code", nullable = false)
    @NotNull
    @Size(min = 20, max = 20)
    private String userCode;

    @OneToMany(mappedBy = "user")
    private List<UserTag> userTagList;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;
}
