package com.spacecowboy89.dc.newsmanagement.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "journalist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Journalist extends CredentialEntity{
    @Id
    @Column(name = "journalist_code")
    private String journalistCode;

    // --------   Relations   --------


    public Journalist(String name, String surname, String email, String username, String password, String journalistCode) {
        super(name, surname, email, username, password);
        this.journalistCode = journalistCode;
    }

    @OneToMany(mappedBy = "journalist")
    private List<Preview> previewList;

    @OneToMany(mappedBy = "journalist")
    private List<News> newsList;

    @OneToMany(mappedBy = "journalist")
    private List<Review> reviewList;
}
