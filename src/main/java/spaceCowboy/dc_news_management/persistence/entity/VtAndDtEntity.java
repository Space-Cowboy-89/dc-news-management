package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Min;
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

    @Size(min=15,max=30)
    private String title;

    @Lob
    private String summary;

    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "positive_vote")
    @Min(0)
    private BigInteger positiveVote = BigInteger.ZERO;

    @Min(0)
    @Column(name = "negative_vote")
    private BigInteger negativeVote= BigInteger.ZERO;
}
