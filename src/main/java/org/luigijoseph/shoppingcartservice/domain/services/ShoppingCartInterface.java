package org.luigijoseph.shoppingcartservice.domain.services;

import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.entities.ShoppingCart;

import java.util.List;

public interface ShoppingCartInterface {
    public void addProductToCart(Long productId, int quantity);

    public void removeProductFromCart(Long productId);

    public double checkoutCart();

    public ShoppingCart getCart();

    public void clearCart();

    public Product searchProductById(Long productId);

    public List<Product> searchProductsByName(String keyword);

}
