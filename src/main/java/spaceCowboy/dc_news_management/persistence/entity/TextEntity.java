package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spaceCowboy.dc_news_management.utility.enums.GeneralEnums;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class TextEntity extends BaseEntity{

    @Column(nullable = false)
    @NotNull
    private GeneralEnums.FPartPreRev type;

    @Lob
    @Column(nullable = false)
    @NotNull
    private String text;

    @Column(name = "order_num",nullable = false)
    @NotNull
    @Min(1)
    @Max(8)
    private int orderNum;
}
