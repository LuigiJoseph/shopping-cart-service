package org.luigijoseph.shoppingcartservice.domain.services;

public interface ShoppingCartInterface {
    public void addProductToCart(Long productId, int quantity);

    public void removeProductFromCart(Long productId);

    public double checkoutCart();
}
