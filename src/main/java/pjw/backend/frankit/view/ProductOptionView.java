package pjw.backend.frankit.view;

import lombok.Builder;
import lombok.Getter;
import pjw.backend.frankit.entity.ProductOption;
import pjw.backend.frankit.enums.OptionType;

import java.util.List;

@Getter
public class ProductOptionView {
    private Long id;
    private Long productId;
    private String name;
    private OptionType type;
    private List<String> typeValue;
    private Integer price;

    public static ProductOptionView from(ProductOption option){
        return ProductOptionView.builder()
                .id(option.getId())
                .productId(option.getProductId())
                .name(option.getName())
                .type(option.getType())
                .typeValue(option.getTypeValue())
                .price(option.getPrice())
                .build();
    }

    @Builder
    public ProductOptionView(Long id, Long productId, String name, OptionType type, List<String> typeValue, Integer price) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.typeValue = typeValue;
        this.price = price;
    }
}
