package by.denis.productservice.Service;

import by.denis.productservice.DTO.CreateProductRequest;
import by.denis.productservice.DTO.ProductResponse;
import by.denis.productservice.Entity.ProductEntity;
import by.denis.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductResponse> getAllProducts(int page,int size){
        log.debug("Fetching all products, page={}, size={}",page,size);
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        return productPage.map(this::toResponse);
    }
    public ProductResponse getProductById(Long id){
        log.debug("Fetching product with id: {}", id);
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return toResponse(product);
    }

    @Transactional
    public ProductResponse createProduct(CreateProductRequest request){
        log.info("Creating new product: {}", request.getName());

        ProductEntity product = new ProductEntity();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        ProductEntity saved = productRepository.save(product);
        log.info("Product created with id: {}", saved.getId());

        return toResponse(saved);
    }
    private ProductResponse toResponse(ProductEntity entity){
        ProductResponse response = new ProductResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setPrice(entity.getPrice());
        return response;
    }

}
