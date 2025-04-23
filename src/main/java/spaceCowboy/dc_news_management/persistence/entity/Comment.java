package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Lob
    private String text;

    @Column (name = "positive_vote")
    private BigInteger positiveVote;

    @Column (name = "negativeVote")
    private BigInteger negativeVote;

    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @Column( name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column( name = "deleted_at")
    private LocalDateTime deletedAt;

    //TODO fare le diverse relazioni
}
