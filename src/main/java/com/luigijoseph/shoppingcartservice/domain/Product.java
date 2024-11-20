package com.luigijoseph.shoppingcartservice.domain;

public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private String productType;
    private static double TAX_RATE = 0.15;

    public Product(int productId, String productName, double productPrice, int productStock, String productType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productType = productType;
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


    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        if (productPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        if (productStock < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.productStock = productStock;
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

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", productType='" + productType + '\'' +
                '}';
    }

}
