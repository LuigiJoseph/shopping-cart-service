package org.luigijoseph.shoppingcartservice.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private ArrayList<Product> products;

//    Could add this later
//    private Map<Product, Integer> productQuantity = new HashMap<>();

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        if (!products.contains(product)) {
            throw new IllegalArgumentException("Product is not in the cart");
        }
        this.products.remove(product);
    }

    public void removeAllProducts() {
        this.products.clear();
    }

    public double payForCart() {
        double total = 0.0;
        for (Product product : this.products) {
            total += product.getPriceWithTax();
        }
        removeAllProducts();
        return total;
    }

    @Override
    public String toString() {
        return "ShoppingCart{products=" + products + "}";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
