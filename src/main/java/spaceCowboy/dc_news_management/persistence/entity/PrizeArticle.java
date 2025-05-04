package spaceCowboy.dc_news_management.persistence.entity;


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

import java.util.List;

@Entity
@Table(name = "prize_article",
        uniqueConstraints = {@UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "prize_article_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrizeArticle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(min = 30, max = 30)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull
    private String desc;

    @Column(name = "prize_article_code", nullable = false)
    @NotNull
    @Size(min = 20, max = 20)
    private String prizeArticleCode;


    @OneToMany(mappedBy = "prizeArticle")
    private List<PrizeArticleContest> prizeArticleContestList;
}
