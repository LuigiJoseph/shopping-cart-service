package org.luigijoseph.shoppingcartservice.integration;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveAndRetrieveProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Keyboard");
        product.setPrice(50.0);
        product.setTax(5.0);
        product.setStock(100);

        // Act
        productRepository.save(product);
        Optional<Product> retrievedProduct = productRepository.findById(product.getId());

        // Assert
        assertTrue(retrievedProduct.isPresent());
        assertEquals("Keyboard", retrievedProduct.get().getName());
    }


}
