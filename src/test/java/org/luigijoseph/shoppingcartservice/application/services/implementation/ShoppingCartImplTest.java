package org.luigijoseph.shoppingcartservice.application.services.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.luigijoseph.shoppingcartservice.domain.services.Implementations.ShoppingCartImpl;
import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.entities.Store;
import org.luigijoseph.shoppingcartservice.domain.entities.ShoppingCart;


import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartImplTest {
    private ShoppingCartImpl shoppingCartService;
    private Store store;
    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        store = new Store();
        cart = new ShoppingCart();
        shoppingCartService = new ShoppingCartImpl(store);


        // Add some products to the store
        Product product1 = new Product(1L, 1, 100, 10, "Laptop", "Electronic Device");
        Product product2 = new Product(3L, 1, 100, 10, "Laptop", "Electronic Device");
        store.addProduct(product1);
        store.addProduct(product2);
    }

    @Test
    void testAddProduct() {
        //Act
        shoppingCartService.addProductToCart(1L, 1);

        //Assert
        assertEquals(0, store.getProductById(1L).getStock());

    }

    @Test
    void removeProductFromCart() {
        shoppingCartService.addProductToCart(1L, 1);
        shoppingCartService.removeProductFromCart(1L);

        assertEquals(0, cart.getProducts().size());

        assertEquals(1, store.getProductById(1L).getStock());
    }

    @Test
    void checkOut(){
        shoppingCartService.addProductToCart(1L, 1);
        shoppingCartService.addProductToCart(3L, 1);



        assertEquals(220., shoppingCartService.checkoutCart());

    }
}
