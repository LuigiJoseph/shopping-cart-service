package com.luigijoseph.shoppingcartservice.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class StoreTests {

    @Test
    public void testAddProductToStore() {
        Store store = new Store(new ArrayList<>());
        Product product = new Product(1, "Laptop", 1000.0, 10, "Electronics");
        store.addProduct(product);

        assertEquals(product, store.getProductById(1));
        assertThrows(NoSuchElementException.class, () -> store.getProductById(2));
    }

    @Test
    public void testSetStock() {
        Store store = new Store(new ArrayList<>());
        Product product = new Product(1, "Laptop", 1000, 10, "Electronics");
        store.addProduct(product);

        Map<String, Integer> stockUpdate = Map.of("Laptop", 20);
        store.setStock(stockUpdate);

        assertEquals(20, product.getProductStock());
        assertThrows(IllegalArgumentException.class, () -> store.setStock(Map.of("Laptop", -10)));
    }

    @Test
    public void testCheckStockAvailability() {
        Store store = new Store(new ArrayList<>());
        Product product = new Product(1, "Laptop", 1000.0, 10, "Electronics");
        store.addProduct(product);

        // Checking that the stock is correctly returned
        assertTrue(store.checkStock(product, 5));  // 5 units should be available
        assertFalse(store.checkStock(product, 15)); // 15 units should be unavailable
    }

    @Test
    public void testUpdateStock() {
        Store store = new Store(new ArrayList<>());
        Product product = new Product(1, "Laptop", 1000.0, 10, "Electronics");
        store.addProduct(product);

        // Decrease stock by 5
        store.updateStock(product, -5);
        assertEquals(5, product.getProductStock());

        // Trying to update stock to a negative value should throw an exception
        assertThrows(IllegalStateException.class, () -> store.updateStock(product, -10));
    }

    @Test
    public void testGetStock() {
        Store store = new Store(new ArrayList<>());
        Product product1 = new Product(1, "Laptop", 1000.0, 10, "Electronics");
        Product product2 = new Product(2, "Phone", 500.0, 20, "Electronics");
        store.addProduct(product1);
        store.addProduct(product2);

        // Test if stock map contains correct data
        Map<String, Integer> stock = store.getStock();
        assertEquals(10, stock.get("Laptop"));
        assertEquals(20, stock.get("Phone"));
    }

    @Test
    public void testNoSuchProduct() {
        Store store = new Store(new ArrayList<>());
        Product product = new Product(1, "Laptop", 1000.0, 10, "Electronics");
        store.addProduct(product);

        // Trying to retrieve a product by non-existing ID
        assertThrows(NoSuchElementException.class, () -> store.getProductById(999));
    }

    @Test
    public void testAddMultipleProducts() {
        Store store = new Store(new ArrayList<>());
        Product product1 = new Product(1, "Laptop", 1000.0, 10, "Electronics");
        Product product2 = new Product(2, "Phone", 500.0, 20, "Electronics");

        // Add multiple products to the store
        store.addProduct(product1);
        store.addProduct(product2);

        // Assert both products are added correctly
        assertEquals(product1, store.getProductById(1));
        assertEquals(product2, store.getProductById(2));
    }

    @Test
    public void testSetStockWithMissingProduct() {
        Store store = new Store(new ArrayList<>());
        Product product = new Product(1, "Laptop", 1000.0, 10, "Electronics");
        store.addProduct(product);

        // Attempt to set stock for a non-existing product
        Map<String, Integer> stockUpdate = Map.of("NonExistentProduct", 5);
        store.setStock(stockUpdate);

        // No change in stock as the product doesn't exist
        assertEquals(10, product.getProductStock());
    }

    @Test
    public void testProductStockCannotBeNegative() {
        Store store = new Store(new ArrayList<>());
        Product product = new Product(1, "Laptop", 1000.0, 10, "Electronics");
        store.addProduct(product);

        // Attempt to set a negative stock for a product directly
        assertThrows(IllegalArgumentException.class, () -> product.setProductStock(-5));
    }

    @Test
    public void testAddProductWithNegativeStock() {
        Store store = new Store(new ArrayList<>());

        // Trying to add a product with negative stock should still work
        // However, if stock is set to negative, it will be caught by the setStock method later.
        Product product = new Product(1, "Laptop", 1000.0, -5, "Electronics");
        store.addProduct(product);

        // Check stock after addition
        assertEquals(-5, product.getProductStock());
    }
}
