package one.digitalinnovation.patterns.service;


import lombok.RequiredArgsConstructor;
import one.digitalinnovation.patterns.domain.Product;
import one.digitalinnovation.patterns.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = findProductById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        validateProduct(existingProduct);
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new RuntimeException("O nome é obrigatório.");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço inválido.");
        }
    }
}
