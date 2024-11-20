package com.luigijoseph.shoppingcartservice.app;

import com.luigijoseph.shoppingcartservice.domain.*;

import java.util.ArrayList;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Step 1: Create a single Store instance
        Store store = new Store(new ArrayList<>());

        // Step 2: Populate the store with some initial products
        store.addProduct(new Product(1, "Laptop", 1000.0, 10, "Electronics"));
        store.addProduct(new Product(2, "Phone", 500.0, 20, "Electronics"));
        store.addProduct(new Product(3, "Headphones", 150.0, 15, "Accessories"));

        // Step 3: Display current stock in the store
        System.out.println("Initial Stock in Store:");
        printStock(store.getStock());

        // Step 4: Create a ShoppingCart and pass the store instance
        ShoppingCart cart = new ShoppingCart(store);

        // Step 5: Add items to the cart
        cart.addItem(store.getProductById(1), 2); // Add 2 Laptops
        cart.addItem(store.getProductById(3), 1); // Add 1 Headphones

        // Step 6: Display items in the cart
        System.out.println("\nItems in ShoppingCart:");
        printCart(cart);

        // Step 7: Pay for the cart (update inventory and clear cart)
        System.out.println("\nPaying for the ShoppingCart...");
        cart.payCart();

        // Step 8: Display updated stock in the store
        System.out.println("\nUpdated Stock in Store:");
        printStock(store.getStock());
    }

    private static void printStock(Map<String, Integer> stockMap) {
        stockMap.forEach((productName, quantity) ->
                System.out.println(productName + ": " + quantity + " units"));
    }

    private static void printCart(ShoppingCart cart) {
        for (Map.Entry<Product, Integer> entry : cart.getItemsInCart().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getProductName() + " - Quantity: " + quantity +
                    " - Subtotal: $" + (product.getProductPrice() * quantity));
        }
    }
}
