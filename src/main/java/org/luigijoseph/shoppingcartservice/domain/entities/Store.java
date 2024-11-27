package org.luigijoseph.shoppingcartservice.domain.entities;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import org.luigijoseph.shoppingcartservice.domain.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Component
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    // Default constructor for JPA
    public Store() {
    }

    public Long getId() {
        return id;
    }

    // Inventory management methods

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        if (products.contains(product)) {
            int currentIndex = products.indexOf(product);
            Product existingProduct = products.get(currentIndex);
            existingProduct.setStock(existingProduct.getStock() + quantity);
        } else {
            product.setStock(quantity);
            products.add(product);
        }
    }

    public void removeProduct(Product product) {
        if (!products.contains(product)) {
            throw new IllegalArgumentException("Product not found in the store.");
        }
        products.remove(product);
    }

    public void removeProduct(Product product, int quantity) {
        if (!products.contains(product)) {
            throw new IllegalArgumentException("Product not found in the store.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        int currentIndex = products.indexOf(product);
        Product existingProduct = products.get(currentIndex);

        if (quantity > existingProduct.getStock()) {
            throw new IllegalArgumentException("Quantity to remove exceeds the product's stock.");
        }

        if (existingProduct.getStock() == quantity) {
            products.remove(product);
        } else {
            existingProduct.setStock(existingProduct.getStock() - quantity);
        }
    }

    public void removeAllProducts() {
        this.products.clear();
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found."));
    }

    public Product getProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found."));
    }

    public boolean isProductInStock(Product product) {
        return products.contains(product) && product.getStock() > 0;
    }

    @Override
    public String toString() {
        return "Store{id=" + id + ", products=" + products + "}";
    }



}
