package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment",
        uniqueConstraints = {@UniqueConstraint(columnNames = "comment_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Lob
    private String text;

    @Column(name = "positive_vote")
    @Min(0)
    private BigInteger positiveVote;


    @Column(name = "negativeVote")
    @Min(0)
    private BigInteger negativeVote;

    @Column(name = "comment_code")
    @Size(min = 20,max = 20)
    private String commentCode;

    //TODO fare le diverse relazioni
}
