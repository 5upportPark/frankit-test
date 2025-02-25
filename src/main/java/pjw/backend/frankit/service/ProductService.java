package pjw.backend.frankit.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pjw.backend.frankit.entity.Product;
import pjw.backend.frankit.exception.DataNotFoundException;
import pjw.backend.frankit.repositoryImpl.ProductRepositoryImpl;
import pjw.backend.frankit.request.ProductRequest;
import pjw.backend.frankit.view.ProductView;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepositoryImpl productRepositoryImpl;
    private static final int PAGE_SIZE = 10;

    public ProductService(ProductRepositoryImpl productRepositoryImpl) {
        this.productRepositoryImpl = productRepositoryImpl;
    }

    @Transactional(readOnly = true)
    public List<ProductView> getProductList(Long cursor){
        Pageable page = PageRequest.of(0, PAGE_SIZE);
        List<Product> result = productRepositoryImpl.findByIdLessThan(cursor, page);
        List<ProductView> productList = result.stream().map(ProductView::from).toList();
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
        productRepositoryImpl.save(product);
        return ProductView.from(product);
    }

    @Transactional
    public void deleteProduct(Long id){
        productRepositoryImpl.deleteById(id);
    }

}
