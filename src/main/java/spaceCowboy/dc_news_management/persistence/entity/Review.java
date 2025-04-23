package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "review",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "review_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review extends VtAndDtEntity{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "review_code")
    private String reviewCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_id")
    private User user;

    //TODO aggiustare
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "news_id")
    private News news;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "comment_id")
    private Comment comment;

    @ManyToMany(mappedBy = )
    tag
}
