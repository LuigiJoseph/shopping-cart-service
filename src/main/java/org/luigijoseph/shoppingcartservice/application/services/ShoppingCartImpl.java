package org.luigijoseph.shoppingcartservice.application.services;

import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.entities.ShoppingCart;
import org.luigijoseph.shoppingcartservice.domain.entities.Store;

public class ShoppingCartImpl implements ShoppingCartInterface {

    private static final ShoppingCart cart = new ShoppingCart();  // Instance of ShoppingCart entity
    private static final Store store = new Store();                   //A place holder for now, place with repository later


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
