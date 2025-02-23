package pjw.backend.frankit.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductRequest {
    private Long id;
    @NotEmpty(message = "상품 이름을 입력해주세요.")
    private String name;
    @NotEmpty(message = "상품 설명을 입력해주세요.")
    private String detail;
    @NotNull(message = "가격을 입력해주세요.")
    private Integer price;
    @NotNull(message = "배송비를 입력해주세요.")
    private Integer deliveryFee;

    public ProductRequest(Long id, String name, String detail, Integer price, Integer deliveryFee) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.deliveryFee = deliveryFee;
    }
}
