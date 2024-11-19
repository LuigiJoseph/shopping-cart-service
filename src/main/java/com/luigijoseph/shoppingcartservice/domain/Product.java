package com.luigijoseph.shoppingcartservice.domain;

public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String productType;
    private static double TAX_RATE = 0.15;

    public Product(int productId, String productName, double productPrice, int productQuantity, String productType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productType = productType;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        if (productPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        if (productQuantity < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.productQuantity = productQuantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getTAX_RATE() {
        return TAX_RATE;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
