package com.spacecowboy89.dc.newsmanagement.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "preview",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "preview_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Preview extends VtAndDtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preview_code", nullable = false)
    @NotNull
    @Size(min = 20, max = 20)
    private String previewCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",nullable = false)
    @NotNull
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id",nullable = false)
    @NotNull
    private Employee employee;

    @OneToMany(mappedBy = "preview")
    private List<PreviewTag> previewTagList;

    @OneToMany(mappedBy = "preview")
    private List<PreviewText> previewTextList;

    @OneToMany(mappedBy = "preview")
    private List<PreviewTag> previewTags;

    @OneToMany(mappedBy = "preview")
    private List<Comment> commentList;
}
