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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;



@Entity
@Table(name = "preview_tag",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = "preview_tag_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PreviewTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "preview_tag_code", nullable = false)
    @NotNull
    @Size(min = 20, max = 20)
    private String previewTagCode;

    @Column(name = "created_at",nullable = false)
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("preview_id")
    @Column(nullable = false)
    @NotNull
    private Preview preview;

    @MapsId("tag_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @Column(nullable = false)
    @NotNull
    private Tag tag;
}