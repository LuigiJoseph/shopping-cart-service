package com.luigijoseph.shoppingcartservice.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ShoppingCart {
    private Map<Product, Integer> items; // Product as key, quantity as value
    private Store store;

    public ShoppingCart(Store store) {
        this.store = store;
        this.items = new HashMap<>();
    }

    public void emptyCart() {
        items.clear();
    }

    public void deleteItem(Product product) {
        items.remove(product);
    }

    public void updateQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            deleteItem(product);
        } else {
            items.put(product, quantity);
        }
    }

    public void addItem(Product product, int quantity) {
        try {
            Product storeProduct = store.getProductById(product.getProductId());
            if (!store.checkStock(storeProduct, quantity)) {
                throw new IllegalStateException("Insufficient stock for product: " + storeProduct.getProductName());
            }
            items.put(storeProduct, items.getOrDefault(storeProduct, 0) + quantity);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Product not found in the store", e);
        }
    }


    public Map<Product, Integer> getItemsInCart() {
        return items;
    }

    public void payCart() {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            store.updateStock(product, -quantity); // Deduct stock from store
        }
        emptyCart();
        System.out.println("Cart payment successful. Thank you for your purchase!");
    }
}
