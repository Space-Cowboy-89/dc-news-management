package com.spacecowboy89.dc.newsmanagement.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//TODO aggiungere in NewsText la constraint "news_text_check_1" in dbeaver
@Entity
@Table(name = "news_text",
uniqueConstraints = {@UniqueConstraint( columnNames = "news_text_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsText extends TextEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "news_text_code", nullable = false)
    @NotNull
    @Size(min=20, max=20)
    private String newsTextCode;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
