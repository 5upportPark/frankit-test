package pjw.backend.frankit.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pjw.backend.frankit.entity.Product;
import pjw.backend.frankit.entity.ProductOption;
import pjw.backend.frankit.exception.DataNotFoundException;
import pjw.backend.frankit.repositoryImpl.ProductOptionRepositoryImpl;
import pjw.backend.frankit.repositoryImpl.ProductRepositoryImpl;
import pjw.backend.frankit.request.ProductRequest;
import pjw.backend.frankit.view.ProductOptionView;
import pjw.backend.frankit.view.ProductView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepositoryImpl productRepositoryImpl;
    private final ProductOptionRepositoryImpl productOptionRepositoryImpl;
    private static final int PAGE_SIZE = 10;

    public ProductService(ProductRepositoryImpl productRepositoryImpl, ProductOptionRepositoryImpl productOptionRepositoryImpl) {
        this.productRepositoryImpl = productRepositoryImpl;
        this.productOptionRepositoryImpl = productOptionRepositoryImpl;
    }

    @Transactional(readOnly = true)
    public List<ProductView> getProductList(Long cursor){
        Pageable page = PageRequest.of(0, PAGE_SIZE);
        List<Product> result = productRepositoryImpl.findByIdLessThan(cursor, page);

        Set<Long> productIds = result.stream().map(Product::getId).collect(Collectors.toSet());
        List<ProductOption> optionsList = productOptionRepositoryImpl.findAllByProductIdIn(productIds);

        List<ProductView> productList = result.stream().map(ProductView::from).toList();
        for(ProductView product : productList){ // 상품에 상품 옵션을 필터링해서 넣어준다
            product.changeOptions(
                    optionsList.stream()
                    .filter(opt->opt.getProductId().equals(product.getId()))
                    .map(ProductOptionView::from).toList()
            );
        }
        return productList;
    }

    @Transactional
    public ProductView addProduct(ProductRequest req){
        Product product = Product.newOne(req.getName(), req.getDetail(), req.getPrice(), req.getDeliveryFee());
        productRepositoryImpl.save(product);
        return ProductView.from(product);
    }

    @Transactional
    public ProductView updateProduct(ProductRequest req){
        if(req.getId() == null || req.getId().equals(0L)) throw new DataNotFoundException();
        Product product = productRepositoryImpl.findById(req.getId()).orElseThrow(DataNotFoundException::new);
        product.update(req.getName(), req.getDetail(), req.getPrice(), req.getDeliveryFee());
        product = productRepositoryImpl.save(product);
        return ProductView.from(product);
    }

    @Transactional
    public void deleteProduct(Long id){
        productRepositoryImpl.deleteById(id);
        productOptionRepositoryImpl.deleteByProductId(id);
    }

}
