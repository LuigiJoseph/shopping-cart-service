package com.luigijoseph.shoppingcartservice.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Store {
    private ArrayList<Product> products;

    public Store(ArrayList<Product> products) {
        this.products = products;
    }

    public Map<String, Integer> getStock() {
        Map<String, Integer> stockMap = new HashMap<>();
        for (Product product : products) {
            stockMap.put(product.getProductName(), product.getProductStock());
        }
        return stockMap;
    }

    public void setStock(Map<String, Integer> stockMap) {
        for (Product product : products) {
            if (stockMap.containsKey(product.getProductName())) {
                int newStock = stockMap.get(product.getProductName());
                if (newStock < 0) {
                    throw new IllegalArgumentException("Stock cannot be negative");
                }
                product.setProductStock(newStock);
            }
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        throw new NoSuchElementException("No product found with ID " + productId);
    }

    public boolean checkStock(Product product, int quantity) {
        return product.getProductStock() >= quantity;
    }

    public void updateStock(Product product, int change) {
        Product storeProduct = getProductById(product.getProductId());
        int newStock = storeProduct.getProductStock() + change;
        if (newStock < 0) {
            throw new IllegalStateException("Not enough stock available for product: " + storeProduct.getProductName());
        }
        storeProduct.setProductStock(newStock);
    }
}
