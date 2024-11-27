package org.luigijoseph.shoppingcartservice.domain.controllers;

import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.entities.Store;
import org.luigijoseph.shoppingcartservice.domain.repository.StoreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listAllProductsInStore() {
        Store store = storeRepository.findById(1L)  // Assuming there's one store with ID 1
                .orElseThrow(() -> new IllegalArgumentException("Store not found."));
        return ResponseEntity.ok(store.getProducts());
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));
        return ResponseEntity.ok(store);
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<Product> searchProductInStore(@PathVariable String productName) {
        Store store = storeRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Store not found."));
        Product product = store.getProductByName(productName);
        return ResponseEntity.ok(product);
    }
}
