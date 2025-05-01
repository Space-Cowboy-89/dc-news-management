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
@Table(name = "user_tag",
        uniqueConstraints = {@UniqueConstraint(columnNames = "user_tag_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "user_tag_code")
    @Size(min = 20, max = 20)
    private String userTagCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("tag_id")
    private Tag tag;
}
