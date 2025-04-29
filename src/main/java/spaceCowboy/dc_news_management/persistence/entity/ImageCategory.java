package spaceCowboy.dc_news_management.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name = "image_category",
uniqueConstraints = {@UniqueConstraint(columnNames = "image_id"),
        @UniqueConstraint(columnNames = "category_id")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageCategory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("image_id")
    private Image image;

    @MapsId("category_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
