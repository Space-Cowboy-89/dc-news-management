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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name = "preview_tag",
        uniqueConstraints = {@UniqueConstraint(columnNames = "review_tag_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "review_tag_code")
    @Size(min = 20, max = 20)
    private String reviewTagCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("tag_id")
    private Tag tag;
}
