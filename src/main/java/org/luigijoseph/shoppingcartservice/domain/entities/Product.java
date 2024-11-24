package org.luigijoseph.shoppingcartservice.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stock;
    private double price;
    private double tax;
    private String name;

    //Needed for JPA
    public Product() {}


    public Product(Long id, int stock, double price, double tax, String name) {
        this.id = id;
        this.stock = stock;
        this.price = price;
        this.tax = tax;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        if (tax < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.tax = tax;
    }

    public double getPriceWithTax() {
        return price + tax;
    }

    public void setPriceWithTax(double price) {
        this.price = price + tax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + ", tax=" + tax + ", stock=" + stock + "}";
    }


}
