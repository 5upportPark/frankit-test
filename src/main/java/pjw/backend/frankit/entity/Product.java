package pjw.backend.frankit.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String detail;
    private Integer price;
    private Integer deliveryFee;
    private ZonedDateTime createdAt;

    public static Product newOne(String name, String detail, Integer price, Integer deliveryFee){
        return Product.builder()
                .name(name)
                .detail(detail)
                .price(price)
                .deliveryFee(deliveryFee)
                .createdAt(ZonedDateTime.now())
                .build();
    }

    public void update(String name, String detail, Integer price, Integer deliveryFee){
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.deliveryFee = deliveryFee;
    }

    @Builder
    public Product(Long id, String name, String detail, Integer price, Integer deliveryFee, ZonedDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.deliveryFee = deliveryFee;
        this.createdAt = createdAt;
    }
}
