package com.luigijoseph.shoppingcartservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ShoppingCartTest {

    private Store store;
    private Product product1;
    private Product product2;
    private ShoppingCart cart;

    // Initialize store, products, and shopping cart before each test
    @BeforeEach
    public void setUp() {
        store = new Store(new ArrayList<>());
        product1 = new Product(1, "Laptop", 1000.0, 10, "Electronics");
        product2 = new Product(2, "Phone", 500.0, 15, "Electronics");
        store.addProduct(product1);
        store.addProduct(product2);

        cart = new ShoppingCart(store);
    }

    @Test
    public void testAddItemToCart() {
        // Adding product1 to the cart
        cart.addItem(product1, 2);

        // Check that the cart contains the product with correct quantity
        assertEquals(2, cart.getItemsInCart().get(product1));

        // Check that adding more of the same product works
        cart.addItem(product1, 3);
        assertEquals(5, cart.getItemsInCart().get(product1));

        // Check if trying to add more than available stock throws an exception
        assertThrows(IllegalStateException.class, () -> cart.addItem(product1, 20));
    }

    @Test
    public void testRemoveItemFromCart() {
        // Add item to cart first
        cart.addItem(product1, 2);

        // Remove item from cart
        cart.deleteItem(product1);

        // Check that the item is removed
        assertFalse(cart.getItemsInCart().containsKey(product1));
    }

    @Test
    public void testUpdateItemQuantity() {
        // Add item to cart
        cart.addItem(product1, 2);

        // Update quantity of the item
        cart.updateQuantity(product1, 3);

        // Check that the quantity is updated
        assertEquals(3, cart.getItemsInCart().get(product1));

        // If updated quantity is 0 or less, it should remove the item from cart
        cart.updateQuantity(product1, 0);
        assertFalse(cart.getItemsInCart().containsKey(product1));
    }

    @Test
    public void testPayCart() {
        // Add items to cart
        cart.addItem(product1, 2); // 2 laptops
        cart.addItem(product2, 3); // 3 phones

        // Check initial stock
        int initialStockProduct1 = product1.getProductStock();
        int initialStockProduct2 = product2.getProductStock();

        // Pay for the cart (this should reduce the stock)
        cart.payCart();

        // Check updated stock after payment
        assertEquals(initialStockProduct1 - 2, product1.getProductStock());
        assertEquals(initialStockProduct2 - 3, product2.getProductStock());

        // Check if the cart is empty after payment
        assertTrue(cart.getItemsInCart().isEmpty());
    }

    @Test
    public void testAddItemProductNotFound() {
        // Product is not in the store, should throw exception
        Product nonExistentProduct = new Product(999, "Tablet", 400.0, 5, "Electronics");

        assertThrows(IllegalArgumentException.class, () -> cart.addItem(nonExistentProduct, 1));
    }

}