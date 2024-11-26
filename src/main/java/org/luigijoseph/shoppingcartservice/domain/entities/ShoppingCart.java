package org.luigijoseph.shoppingcartservice.domain.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "shopping_cart_id") // Foreign key in Product table
    private Set<Product> products = new HashSet<>();

    public ShoppingCart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public void removeAllProducts() {
        this.products.clear();
    }

    // Add the payForCart() method
    public double payForCart() {
        double total = 0.0;
        for (Product product : this.products) {
            total += product.getPriceWithTax(); // Assuming Product has getPriceWithTax()
        }
        return total;
    }

    @Override
    public String toString() {
        return "ShoppingCart{id=" + id + ", products=" + products + "}";
    }
}
