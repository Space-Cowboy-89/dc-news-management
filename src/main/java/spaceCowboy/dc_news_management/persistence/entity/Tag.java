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

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "tag_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(min = 4, max = 25)
    private String name;

    @Size(min = 20, max = 20)
    @Column(name = "tag_code", nullable = false)
    @NotNull
    private String tagCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "tag")
    private List<NewsTag> newsTagList = new ArrayList<>();

    @OneToMany(mappedBy = "tag")
    private List<PreviewTag> previewTagList;

    @OneToMany(mappedBy = "tag")
    private List<ReviewTag> reviewTagList;

    @OneToMany(mappedBy = "tag")
    private List<UserTag> userTagList;

    //TODO utilizzare eccezione adatta
    public void checkCategory() {
        if (category != null &&
                category.getId() != null && category.getId().intValue() == 0)
            throw new RuntimeException();
    }
}
