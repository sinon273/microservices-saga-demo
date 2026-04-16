package by.denis.productservice.Controller;

import by.denis.productservice.DTO.CreateProductRequest;
import by.denis.productservice.DTO.ProductResponse;
import by.denis.productservice.Service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        log.info("GET /api/products - fetching all products");
        return productService.getAllProducts(page,size);
    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        log.info("GET /api/products/{} - fetching product", id);
        return productService.getProductById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody CreateProductRequest request){
        log.info("POST /api/products - creating product: {}",request.getName());
        return productService.createProduct(request);
    }
}
