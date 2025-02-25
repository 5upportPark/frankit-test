package pjw.backend.frankit.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pjw.backend.frankit.request.ProductOptionRequest;
import pjw.backend.frankit.service.ProductOptionService;
import pjw.backend.frankit.view.ProductOptionView;

import java.util.List;

@RestController
@RequestMapping("/option")
public class ProductOptionController {
    private final ProductOptionService productOptionService;

    public ProductOptionController(ProductOptionService productOptionService) {
        this.productOptionService = productOptionService;
    }

    @GetMapping("/{productId}")
    public List<ProductOptionView> getOptionList(@PathVariable Long productId){
        return productOptionService.getProductOptionList(productId);
    }

    @PostMapping
    public ProductOptionView addProductOption(@RequestBody @Valid ProductOptionRequest req){
        return productOptionService.addProductOption(req);
    }

    @PutMapping
    public ProductOptionView editProductOption(@RequestBody @Valid ProductOptionRequest req){
        return productOptionService.updateProductOption(req);
    }

    @DeleteMapping("/{id}")
    public void deleteProductOption(@PathVariable Long id){
        productOptionService.deleteProductOption(id);
    }
}
