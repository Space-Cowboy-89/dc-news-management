package spaceCowboy.dc_news_management.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table( name = "review_text",
        uniqueConstraints = {@UniqueConstraint(columnNames = "review_text_code")}
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewText extends TextEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "review_text_code")
    @Size(min = 20, max= 20)
    private String reviewTextCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

}
