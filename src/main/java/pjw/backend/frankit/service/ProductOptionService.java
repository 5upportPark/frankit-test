package pjw.backend.frankit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pjw.backend.frankit.entity.ProductOption;
import pjw.backend.frankit.exception.DataNotFoundException;
import pjw.backend.frankit.repositoryImpl.ProductOptionRepositoryImpl;
import pjw.backend.frankit.request.ProductOptionRequest;
import pjw.backend.frankit.view.ProductOptionView;

import java.util.List;

@Service
public class ProductOptionService {
    private final ProductOptionRepositoryImpl productOptionRepositoryImpl;

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
        ProductOption option = ProductOption.newOne(req.getProductId(), req.getName(), req.getType(), req.getTypeValue(), req.getPrice());
        productOptionRepositoryImpl.save(option);
        return ProductOptionView.from(option);
    }

    @Transactional
    public ProductOptionView updateProductOption(ProductOptionRequest req){
        if(req.getId() == null || req.getId().equals(0L)) throw new DataNotFoundException();
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
