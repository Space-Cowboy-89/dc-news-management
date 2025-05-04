package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VtAndDtEntity extends BaseEntity {

    @Size(min = 30, max = 30)
    @Column(nullable = false)
    @NotNull
    private String title;

    @Column(nullable = false, columnDefinition ="TEXT")
    @NotNull
    private String summary;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "positive_vote", nullable = false)
    @NotNull
    @Min(0)
    private long positiveVote = 0l;

    @Column(name = "negative_vote", nullable = false)
    @NotNull
    @Min(0)
    private long negativeVote = 0l;
}
