package spaceCowboy.dc_news_management.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contest"
        ,uniqueConstraints = {
        @UniqueConstraint(columnNames = "contest_code")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Lob
    @Column(nullable = false)
    @NotNull
    private String desc;

    @Column(name = "contest_code",nullable = false)
    @NotNull
    @Size(min=20,max=20)
    private String contestCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "contest")
    private List<PrizeArticleContest> prizeArticleContestList;
}
