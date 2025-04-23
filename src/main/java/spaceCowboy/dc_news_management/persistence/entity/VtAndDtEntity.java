package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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

    private LocalDateTime date;

    @Column(name = "positive_vote")
    private BigInteger positiveVote;

    @Column(name = "negative_vote")
    private BigInteger negativeVote;
}
