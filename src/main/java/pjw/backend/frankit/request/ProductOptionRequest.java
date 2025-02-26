package pjw.backend.frankit.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pjw.backend.frankit.enums.OptionType;

import java.util.List;

@Getter
public class ProductOptionRequest {
    private Long id;
    @NotNull(message = "상품을 선택해주세요.")
    private Long productId;
    @NotEmpty(message = "옵션 이름을 입력해주세요.")
    private String name;
    @NotNull(message = "옵션 타입을 선택해주세요.")
    private OptionType type;
    @NotEmpty(message = "옵션 값을 입력해주세요.")
    List<String> typeValue;
    @NotNull(message = "옵션 가격을 입력해주세요.")
    private Integer price;

    public ProductOptionRequest(Long id, Long productId, String name, OptionType type, List<String> typeValue, Integer price) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.typeValue = typeValue;
        this.price = price;
    }
}
