package spaceCowboy.dc_news_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    private GeneralEnums.FPartPreRev type;

    @Lob
    private String text;

    @Column(name = "order_num")
    @Min(1)
    @Max(8)
    private int orderNum;
}
