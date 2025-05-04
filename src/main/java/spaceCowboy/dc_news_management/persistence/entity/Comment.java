package spaceCowboy.dc_news_management.persistence.entity;

import com.mysql.cj.jdbc.Clob;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull
    private String text;

    @Column(name = "positive_vote", nullable = false)
    @NotNull
    @Min(0)
    private long positiveVote = 0l;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "negativeVote", nullable = false)
    @NotNull
    @Min(0)
    private long negativeVote = 0l;

    @Column(name = "comment_code", nullable = false)
    @NotNull
    @Size(min = 20, max = 20)
    private String commentCode;

    //TODO fare le diverse relazioni

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "news_id")
    private News news;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "preview_id")
    private Preview preview;
}
