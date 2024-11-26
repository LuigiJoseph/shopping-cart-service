package org.luigijoseph.shoppingcartservice.domain.controllers.dto;

public class AddProductRequest {
    private Long productId;
    private int quantity;

    public AddProductRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
