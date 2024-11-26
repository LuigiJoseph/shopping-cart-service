package org.luigijoseph.shoppingcartservice.domain.controllers;

import org.luigijoseph.shoppingcartservice.domain.controllers.dto.AddProductRequest;
import org.luigijoseph.shoppingcartservice.domain.entities.ShoppingCart;
import org.luigijoseph.shoppingcartservice.domain.services.ShoppingCartInterface;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;  // For mapping base URLs

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;  // To handle URL path variables
import org.springframework.web.bind.annotation.RequestBody;  // To handle request bodies


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;  // For response wrapping
import org.springframework.http.HttpStatus;     // For HTTP status codes


@RestController
@RequestMapping("/api/cart")  // Base URL for all cart-related endpoints
public class CartController {

    private final ShoppingCartInterface shoppingCartService;

    @Autowired
    public CartController(ShoppingCartInterface shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    //get current mapping of the cart
    @GetMapping
    public ResponseEntity<ShoppingCart> getCart() {
        ShoppingCart cart = shoppingCartService.getCart();
        return ResponseEntity.ok(cart);  // Return the cart with HTTP 200 status
    }
    //List all the products in the cart
//    @GetMapping("/products")
//    public ResponseEntity<Set<Product>> listProductsInCart() {
//        Set<Product> products = shoppingCartService.getCart().getProducts();
//        return ResponseEntity.ok(products);
//    }


    //Adding a product to the cart
    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(@RequestBody AddProductRequest request) {
        try {
            shoppingCartService.addProductToCart(request.getProductId(), request.getQuantity());
            return ResponseEntity.ok("Product added to cart successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long productId) {
        shoppingCartService.removeProductFromCart(productId);
        return ResponseEntity.ok("Product with id " + productId + " removed successfully.");
    }

    @DeleteMapping("clear")
    public ResponseEntity<String> clearCart() {
        shoppingCartService.clearCart();
        return ResponseEntity.ok("Cart cleared successfully.");
    }


    @PostMapping("/checkout")
    public ResponseEntity<String> checkout() {
        double totalAmount = shoppingCartService.checkoutCart(); // Get the total amount (including tax)
        return ResponseEntity.ok("Payment successful! Total amount paid: " + totalAmount);
    }



}