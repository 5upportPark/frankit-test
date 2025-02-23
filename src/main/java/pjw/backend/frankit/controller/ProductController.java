package pjw.backend.frankit.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pjw.backend.frankit.request.ProductRequest;
import pjw.backend.frankit.service.ProductService;
import pjw.backend.frankit.view.ProductView;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductView> getProductList(@RequestParam(defaultValue = "10") Long cursor){
        return productService.getProductList(cursor);
    }

    @PostMapping
    public ProductView addProduct(@RequestBody @Valid ProductRequest req){
        return productService.addProduct(req);
    }

    @PutMapping
    public ProductView editProduct(@RequestBody @Valid ProductRequest req){
        return productService.updateProduct(req);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
