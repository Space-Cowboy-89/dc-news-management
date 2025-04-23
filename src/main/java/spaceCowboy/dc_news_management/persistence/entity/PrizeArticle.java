package spaceCowboy.dc_news_management.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "prize_article",
        uniqueConstraints = {@UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "prize_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrizeArticle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private String name;

    @Lob
    private String desc;

    @Column(name = "prize_code")
    private String prizeCode;


    @ManyToMany()
    @JoinTable(name = "prize_article_contest",
            joinColumns = @JoinColumn(name = "prize_id"),
            inverseJoinColumns = @JoinColumn(name = "contest_id"))
    private List<Contest> contestList;

    @ManyToMany
    @JoinTable(name = "prize_article_image",
            joinColumns = @JoinColumn(name = "prize_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> imageList;

}
