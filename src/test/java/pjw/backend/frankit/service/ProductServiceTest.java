package pjw.backend.frankit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pjw.backend.frankit.entity.Product;
import pjw.backend.frankit.enums.OptionType;
import pjw.backend.frankit.exception.DataNotFoundException;
import pjw.backend.frankit.repositoryImpl.ProductOptionRepositoryImpl;
import pjw.backend.frankit.repositoryImpl.ProductRepositoryImpl;
import pjw.backend.frankit.request.ProductRequest;
import pjw.backend.frankit.view.ProductOptionView;
import pjw.backend.frankit.view.ProductView;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepositoryImpl productRepositoryImpl;
    @Mock
    ProductOptionRepositoryImpl productOptionRepositoryImpl;
    @InjectMocks
    ProductService productService;
    private static final int PAGE_SIZE = 10;

    final Long id = 1L;
    List<Product> productList;
    List<ProductView> viewList;
    Product entity;
    ProductRequest req;
    List<ProductOptionView> optionViewList;

    @BeforeEach
    public void 초기화(){
        entity = new Product(id, "상품명", "상품 설명설명", 19800, 3500, ZonedDateTime.now());
        req = new ProductRequest(null, "상품명", "상품 설명설명", 19800, 3500);

        productList = new ArrayList<>();
        viewList = new ArrayList<>();
        for(int i = 1; i <= 6; i++){
            Product product = new Product((long)i, "상품명"+i, "상품 설명설명"+i, 19800, 3500, ZonedDateTime.now());
            ProductView view = ProductView.from(product);
            ProductOptionView optionView = new ProductOptionView(2L*i, (long)i, "상품 옵션", OptionType.SELECT, List.of("옵션 값"), 1000);
            view.changeOptions(List.of(optionView));

            productList.add(product);
            viewList.add(view);
        }
    }

    @Test
    public void 상품_조회(){
        when(productRepositoryImpl.findByIdLessThan(anyLong(), any(Pageable.class))).thenReturn(productList);

        List<ProductView> result = productService.getProductList(10L);

        assertThat(result).hasSize(6);
    }

    @Test
    public void 상품_수정(){
        when(productRepositoryImpl.findById(anyLong())).thenReturn(Optional.ofNullable(entity));
        when(productRepositoryImpl.save(any(Product.class))).thenReturn(entity);

        req = new ProductRequest(id, "상품명 변경", "상품 설명설명 변경", 99800, 0);
        ProductView result = productService.updateProduct(req);

        assertEquals(result.getId(), id);
        assertEquals(result.getName(), req.getName());
        assertEquals(result.getDetail(), req.getDetail());
        assertEquals(result.getPrice(), req.getPrice());
    }

    @Test
    public void 상품_수정_실패(){
        when(productRepositoryImpl.findById(anyLong())).thenReturn(Optional.ofNullable(entity));

        assertThrows(DataNotFoundException.class,()->{
            ProductView result = productService.updateProduct(req);
        });
    }

}
