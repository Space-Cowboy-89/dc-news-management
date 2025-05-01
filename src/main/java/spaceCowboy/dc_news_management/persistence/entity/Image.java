package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
public class Image extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Lob
    @Column(nullable = false)
    @NotNull
    private byte[] content;

    @Lob
    private String desc;

    @Column(name = "image_code", nullable = false)
    @NotNull
    @Size(min = 30, max=30)
    private String imageCode;

    @OneToMany(mappedBy = "image")
    private List<News> newsList;

    @ManyToMany
    @JoinTable(name = "image_category",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categoryList;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageCategory> imageCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "image")
    private List<NewsText> newsTextList;

    @OneToMany(mappedBy = "image")
    private List<PreviewText> previewTextList;

    @ManyToMany(mappedBy = "imageList")
    private List<PrizeArticle> prizeArticleList;

    @OneToMany(mappedBy = "image")
    private List<ReviewText> reviewTextList;

    @OneToMany(mappedBy = "image")
    private List<Review> reviewList;
}
