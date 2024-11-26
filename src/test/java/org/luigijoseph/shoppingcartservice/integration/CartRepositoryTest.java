package org.luigijoseph.shoppingcartservice.integration;

import org.junit.jupiter.api.Test;
import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.entities.ShoppingCart;
import org.luigijoseph.shoppingcartservice.domain.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testSaveAndFindCart() {
        // Create a new shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Add a product to the cart
        Product product = new Product(null, 10, 50.0, 5.0, "Sample Product", "A test product");
        cart.addProduct(product);

        // Save the shopping cart
        ShoppingCart savedCart = cartRepository.save(cart);

        // Verify the cart was saved
        assertNotNull(savedCart.getId());
        assertEquals(1, savedCart.getProducts().size());

        // Find the cart by ID
        ShoppingCart foundCart = cartRepository.findById(savedCart.getId()).orElse(null);

        // Verify the retrieved cart matches the saved cart
        assertNotNull(foundCart);
        assertEquals(savedCart.getId(), foundCart.getId());
        assertEquals(1, foundCart.getProducts().size());
    }
}
