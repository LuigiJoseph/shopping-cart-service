package org.luigijoseph.shoppingcartservice.domain.services;

import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.entities.ShoppingCart;
import org.luigijoseph.shoppingcartservice.domain.entities.Store;

public class ShoppingCartImpl implements ShoppingCartInterface {

    private final ShoppingCart cart;
    private final Store store;


    public ShoppingCartImpl(Store store) {
        this.cart = new ShoppingCart();
        this.store = store;
    }
    @Override
    public void addProductToCart(Long productId, int quantity){
        //Get the product from store
        Product product = store.getProductById(productId);

        //Check stock
        if(quantity > product.getStock()){
            throw new IllegalArgumentException("Quantity exceeds stock");
        }
        // Add product to the cart
        for (int i = 0; i < quantity; i++) {
            cart.addProduct(product);

        }

        product.setStock(product.getStock() - quantity);

    }

    public void removeProductFromCart(Long productId){
        Product product = store.getProductById(productId);

        cart.removeProduct(product);

        // Add the product back to the store's stock
        store.addProduct(product, 1);
    }

    public double checkoutCart(){
        //Calculate price for all items in cart
        double total = cart.payForCart();

        cart.removeAllProducts();

        return total;
    }

}
