package spaceCowboy.dc_news_management.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name = "prize_article_contest",
        uniqueConstraints = {@UniqueConstraint(columnNames = "prize_article_contest_code"),})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrizeArticleContest extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "prize_article_contest_code", nullable = false)
    @NotNull
    @Size(min = 20, max =20)
    private String prizeArticleContestCode;

    @ManyToOne(fetch= FetchType.EAGER)
    @MapsId("contest_id")
    private Contest contest;

    @ManyToOne(fetch= FetchType.EAGER)
    @MapsId("prize_article_id")
    private PrizeArticle prizeArticle;
}
