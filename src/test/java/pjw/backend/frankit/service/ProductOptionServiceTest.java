package pjw.backend.frankit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pjw.backend.frankit.entity.ProductOption;
import pjw.backend.frankit.enums.OptionType;
import pjw.backend.frankit.exception.CountLimitException;
import pjw.backend.frankit.exception.DataNotFoundException;
import pjw.backend.frankit.repositoryImpl.ProductOptionRepositoryImpl;
import pjw.backend.frankit.request.ProductOptionRequest;
import pjw.backend.frankit.view.ProductOptionView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductOptionServiceTest {
    @Mock
    ProductOptionRepositoryImpl productOptionRepositoryImpl;
    @InjectMocks
    ProductOptionService productOptionService;

    final Long productId = 1L;
    List<ProductOption> optionList;
    List<ProductOptionView> optionViewList;
    ProductOptionRequest req;
    ProductOption entity;

    @BeforeEach
    public void 초기화(){
        req = new ProductOptionRequest(null, productId, "옵션 이름", OptionType.CUSTOM, List.of("value1","value2"), 1000);
        entity = new ProductOption(3L, req.getProductId(), req.getName(), req.getType(), req.getTypeValue(), req.getPrice());

        optionList = new ArrayList<>();
        optionViewList = new ArrayList<>();
        for(int i = 1; i <= 2; i++){
            ProductOption productOption = ProductOption.builder()
                    .id((long)i)
                    .productId(productId)
                    .name("옵션 이름"+i)
                    .type(OptionType.CUSTOM)
                    .typeValue(List.of("value1","value2"))
                    .price(1000*i)
                    .build();
            optionList.add(productOption);
            ProductOptionView opt = ProductOptionView.from(entity);
            optionViewList.add(opt);
        }
    }

    @Test
    public void 상품_옵션_조회(){
        when(productOptionRepositoryImpl.findAllByProductId(anyLong())).thenReturn(optionList);

        List<ProductOptionView> result = productOptionService.getProductOptionList(productId);

        assertThat(result).hasSize(2);
    }

    @Test
    public void 상품_옵션_추가(){
        when(productOptionRepositoryImpl.countByProductId(productId)).thenReturn((long)optionList.size());
        when(productOptionRepositoryImpl.save(any(ProductOption.class))).thenReturn(entity);

        ProductOptionView result = productOptionService.addProductOption(req);

        assertThat(result.getId()).isEqualTo(entity.getId());
    }

    @Test
    public void 상품_옵션_추가_실패(){
        optionList.add(entity);
        when(productOptionRepositoryImpl.countByProductId(productId)).thenReturn((long)optionList.size());

        assertThrows(CountLimitException.class, ()-> {
            productOptionService.addProductOption(req);
        });
    }

    @Test
    public void 상품_옵션_수정_실패(){
        assertThrows(DataNotFoundException.class,()->{
            productOptionService.updateProductOption(req);
        });

        req = new ProductOptionRequest(999L, productId, "옵션 이름", OptionType.CUSTOM, List.of("value1","value2"), 1000);
        assertThrows(DataNotFoundException.class,()->{
            productOptionService.updateProductOption(req);
        });
    }

}
