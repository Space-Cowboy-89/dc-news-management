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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name  = "preview_text")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PreviewText {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private String id;

    private String text;

    private char type;

    private int order_num;

    @Column(name = "previews_text_code")
    private String previewTextCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn ( name = "preview_id")
    private Preview preview;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "image_id")
    private Image image;
}
