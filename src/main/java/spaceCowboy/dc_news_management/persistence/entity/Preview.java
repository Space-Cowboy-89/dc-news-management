package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "preview",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "previews_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Preview {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private BigInteger id;

        private String title;

        private String summary;

        private LocalDateTime date;

        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(name = "modified_at")
        private LocalDateTime modifiedAt;

        @Column(name = "deleted_at")
        private LocalDateTime deletedAt;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "category_id")
        private Category category;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "employee_id")
        private Employee employee;

        @ManyToMany(mappedBy = "previewList")
        List<Tag> tagList;

        @OneToMany(mappedBy = "preview")
        private List<PreviewText> previewTextList;
}
