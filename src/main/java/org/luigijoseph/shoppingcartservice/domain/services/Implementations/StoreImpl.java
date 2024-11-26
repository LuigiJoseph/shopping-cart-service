package org.luigijoseph.shoppingcartservice.domain.services.Implementations;

import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.exceptions.ProductNotFoundException;
import org.luigijoseph.shoppingcartservice.domain.services.StoreInterface;

import java.util.ArrayList;
import java.util.List;

public class StoreImpl implements StoreInterface {

    private final List<Product> products = new ArrayList<>(); // Assuming store maintains a list of products

    @Override
    public Product searchProductById(Long productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found."));
    }

    @Override
    public List<Product> searchProductsByName(String keyword) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}
