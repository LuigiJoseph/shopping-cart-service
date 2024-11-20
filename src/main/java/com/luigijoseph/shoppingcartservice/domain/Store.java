package com.luigijoseph.shoppingcartservice.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Store {
    private ArrayList<Product> products;

    public Store() {}

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
                throw new IllegalArgumentException();
            }
        }
    }
}

    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        throw new NoSuchElementException("No product found with ID " + productId);
    }

}
