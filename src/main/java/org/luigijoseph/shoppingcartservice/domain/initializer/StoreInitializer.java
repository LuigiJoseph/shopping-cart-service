package org.luigijoseph.shoppingcartservice.domain.initializer;

import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.entities.Store;
import org.luigijoseph.shoppingcartservice.domain.repository.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreInitializer implements CommandLineRunner {

    private final StoreRepository storeRepository;

    public StoreInitializer(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Store store = new Store();

        List<Product> products = List.of(
                new Product(null, 50, 599.99, 29.99, "Smartphone", "Latest model with AMOLED display and 128GB storage"),
                new Product(null, 30, 1099.99, 54.99, "Laptop", "High-performance laptop with 16GB RAM and 512GB SSD"),
                new Product(null, 20, 299.99, 14.99, "Smartwatch", "Water-resistant smartwatch with heart rate monitor")
                // Add more products...
        );

        for (Product product : products) {
            store.addProduct(product);
        }

        storeRepository.save(store);
    }
}
