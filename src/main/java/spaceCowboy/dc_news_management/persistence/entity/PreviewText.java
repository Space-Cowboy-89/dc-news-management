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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "preview_text",
        uniqueConstraints =
                {@UniqueConstraint(columnNames = "preview_text_code"),
                        @UniqueConstraint(columnNames = "preview_text_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PreviewText extends TextEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 20, max = 20)
    @Column(name = "preview_text_code",nullable = false)
    @NotNull
    private String previewTextCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "preview_id", nullable = false)
    @NotNull
    private Preview preview;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;
}
