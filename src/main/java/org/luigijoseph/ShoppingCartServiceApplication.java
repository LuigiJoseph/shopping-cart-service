package org.luigijoseph.shoppingcartservice.application.services.implementation;

import org.luigijoseph.shoppingcartservice.application.services.ShoppingCartInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingCartServiceApplication extends ShoppingCartInterface {
	void addProductToCart(Long productId, int quantity);{}

	void removeProductFromCart(Long productId);

	double checkoutCart();

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartServiceApplication.class, args);
	}

}
