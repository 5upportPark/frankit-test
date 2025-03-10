package pjw.backend.frankit.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pjw.backend.frankit.converter.OptionTypeConverter;
import pjw.backend.frankit.converter.OptionTypeValueConverter;
import pjw.backend.frankit.enums.OptionType;

import java.util.List;

@Entity
@Table(name = "product_option")
@NoArgsConstructor
@Getter
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String name;
    @Convert(converter = OptionTypeConverter.class)
    private OptionType type;
    @Convert(converter = OptionTypeValueConverter.class)
    private List<String> typeValue;
    private Integer price;

    public static ProductOption newOne(Long productId, String name, OptionType type, List<String> typeValue, Integer price){
        return ProductOption.builder()
                .productId(productId)
                .name(name)
                .type(type)
                .typeValue(typeValue)
                .price(price)
                .build();
    }

    public void update(String name, OptionType type, List<String> typeValue, Integer price){
        this.name = name;
        this.type = type;
        this.typeValue = typeValue;
        this.price = price;
    }

    @Builder
    public ProductOption(Long id, Long productId, String name, OptionType type, List<String> typeValue, Integer price) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.typeValue = typeValue;
        this.price = price;
    }
}
