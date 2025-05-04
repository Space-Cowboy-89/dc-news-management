package spaceCowboy.dc_news_management.persistence.entity;


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
@Table(name = "news",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "news_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class News extends  VtAndDtEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "news_code", nullable = false)
    @NotNull
    @Size(min = 20, max = 20)
    private String newsCode;

    //private GeneralEnums.FPartNews type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id",nullable = false)
    @NotNull
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",nullable = false)
    @NotNull
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "news")
    private List<NewsText> newsTextList;

    @OneToMany(mappedBy = "news")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "news")
    private List<NewsTag> newsTagList;
}
