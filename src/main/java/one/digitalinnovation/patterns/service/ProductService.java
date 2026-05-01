package one.digitalinnovation.patterns.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.domain.Product;
import one.digitalinnovation.patterns.dto.ProductRequest;
import one.digitalinnovation.patterns.dto.ProductResponse;
import one.digitalinnovation.patterns.exception.ResourceNotFoundException;
import one.digitalinnovation.patterns.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    public ProductResponse findProductById(Long id) {
        return ProductResponse.from(getProductOrThrow(id));
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        return ProductResponse.from(productRepository.save(product));
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = getProductOrThrow(id);
        product.setName(request.name());
        product.setPrice(request.price());
        return ProductResponse.from(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        productRepository.delete(getProductOrThrow(id));
    }

    public Product getProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }
}