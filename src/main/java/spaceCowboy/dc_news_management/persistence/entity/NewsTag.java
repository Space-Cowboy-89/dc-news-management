package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;

public class NewsTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "news_tag_code")
    @Size(min = 20, max = 20)
    private String newsTagCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("news_id")
    private News news;


    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("tag_id")
    private Tag tag;
}
