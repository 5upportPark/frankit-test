package pjw.backend.frankit.view;

import lombok.Builder;
import lombok.Getter;
import pjw.backend.frankit.entity.Product;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public class ProductView {
    private Long id;
    private String name;
    private String detail;
    private Integer price;
    private Integer deliveryFee;
    private ZonedDateTime createdAt;

    private List<ProductOptionView> options;

    public static ProductView from(Product product){
        return ProductView.builder()
                .id(product.getId())
                .name(product.getName())
                .detail(product.getDetail())
                .price(product.getPrice())
                .deliveryFee(product.getDeliveryFee())
                .createdAt(product.getCreatedAt())
                .build();
    }

    public void changeOptions(List<ProductOptionView> options){
        this.options = options;
    }

    private ProductView(){}

    @Builder
    public ProductView(Long id, String name, String detail, Integer price, Integer deliveryFee, ZonedDateTime createdAt, List<ProductOptionView> options) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.deliveryFee = deliveryFee;
        this.createdAt = createdAt;
        this.options = options;
    }
}
