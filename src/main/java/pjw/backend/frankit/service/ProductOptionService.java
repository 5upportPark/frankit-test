package pjw.backend.frankit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pjw.backend.frankit.entity.ProductOption;
import pjw.backend.frankit.exception.CountLimitException;
import pjw.backend.frankit.exception.DataNotFoundException;
import pjw.backend.frankit.repositoryImpl.ProductOptionRepositoryImpl;
import pjw.backend.frankit.request.ProductOptionRequest;
import pjw.backend.frankit.view.ProductOptionView;

import java.util.List;

@Service
public class ProductOptionService {
    private final ProductOptionRepositoryImpl productOptionRepositoryImpl;
    private static final int OPTION_MAX_COUNT = 3;

    public ProductOptionService(ProductOptionRepositoryImpl productOptionRepositoryImpl) {
        this.productOptionRepositoryImpl = productOptionRepositoryImpl;
    }

    @Transactional(readOnly = true)
    public List<ProductOptionView> getProductOptionList(Long productId){
        List<ProductOption> optionList = productOptionRepositoryImpl.findAllByProductId(productId);
        return optionList.stream().map(ProductOptionView::from).toList();
    }

    @Transactional
    public ProductOptionView addProductOption(ProductOptionRequest req){
        long optionCount = productOptionRepositoryImpl.countByProductId(req.getProductId());
        if(optionCount >= OPTION_MAX_COUNT)
            throw new CountLimitException(String.format("옵션은 %d개 까지만 설정 가능합니다.", OPTION_MAX_COUNT));

        ProductOption option = ProductOption.newOne(req.getProductId(), req.getName(), req.getType(), req.getTypeValue(), req.getPrice());
        option = productOptionRepositoryImpl.save(option);
        return ProductOptionView.from(option);
    }

    @Transactional
    public ProductOptionView updateProductOption(ProductOptionRequest req){
        if(req.getId() == null || req.getId().equals(0L))
            throw new DataNotFoundException();

        ProductOption option = productOptionRepositoryImpl.findById(req.getId()).orElseThrow(DataNotFoundException::new);
        option.update(req.getName(), req.getType(), req.getTypeValue(), req.getPrice());
        productOptionRepositoryImpl.save(option);
        return ProductOptionView.from(option);
    }

    @Transactional
    public void deleteProductOption(Long id){
        productOptionRepositoryImpl.deleteById(id);
    }
}
