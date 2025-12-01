package com.stockmanager.stockmanager_backend.service;

import com.stockmanager.stockmanager_backend.model.Product;
import com.stockmanager.stockmanager_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }
    
    public List<Product> getLowStockProducts() {
        return productRepository.findByQuantityLessThanEqual(10);
    }
    
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setQuantity(productDetails.getQuantity());
        product.setPrice(productDetails.getPrice());
        product.setMinStock(productDetails.getMinStock());
        product.setDescription(productDetails.getDescription());
        return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}