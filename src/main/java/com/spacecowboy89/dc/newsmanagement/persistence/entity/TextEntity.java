package com.spacecowboy89.dc.newsmanagement.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class TextEntity extends BaseEntity{

    @Column(nullable = false)
    @NotNull
    private char type;

    @Column(nullable = false,columnDefinition = "TEXT")
    @NotNull
    private String text;

    @Column(name = "order_num",nullable = false)
    @NotNull
    @Min(1)
    @Max(8)
    private short orderNum;
}
