package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "image",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "image_code"),
                @UniqueConstraint(columnNames = "name")
        })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Lob
    @Column(nullable = false, columnDefinition = "MEDIUMBLOB")
    @NotNull
    private byte[] content;

    @Column(columnDefinition = "TEXT")
    private String desc;

    @Column(name = "image_code", nullable = false)
    @NotNull
    @Size(min = 30, max = 30)
    private String imageCode;

    @OneToMany(mappedBy = "image")
    private List<News> newsList;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageCategory> imageCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "image")
    private List<NewsText> newsTextList;

    @OneToMany(mappedBy = "image")
    private List<PreviewText> previewTextList;

    @OneToMany(mappedBy = "image")
    private List<ReviewText> reviewTextList;

    @OneToMany(mappedBy = "image")
    private List<Review> reviewList;
}
