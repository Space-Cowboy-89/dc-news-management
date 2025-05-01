package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "review",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "review_code")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review extends VtAndDtEntity{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "review_code", nullable = false)
    @NotNull
    @Size(min = 20, max=20)
    private String reviewCode;

    @Column(nullable = false)
    @NotNull
    @Min(0)
    @Max(10)
    private float vote;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id",nullable = false)
    @NotNull
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "review")
    private List<Comment> commentList;

    //guardare

    @ManyToMany(mappedBy = "reviewList")
    private List<Tag> tagList;

    @OneToMany(mappedBy = "review")
    private List<ReviewText> reviewTextList;

    @OneToMany(mappedBy = "review")
    private List<ReviewTag> reviewTagList;
}
