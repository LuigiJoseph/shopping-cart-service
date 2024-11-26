package org.luigijoseph.shoppingcartservice.integration;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.luigijoseph.shoppingcartservice.domain.entities.Store;
import org.luigijoseph.shoppingcartservice.domain.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void testSaveAndRetrieveStore() {
        Store store = new Store();
        // Add setup logic
        Store savedStore = storeRepository.save(store);

        Optional<Store> retrievedStore = storeRepository.findById(savedStore.getId());
        assertTrue(retrievedStore.isPresent());
        assertEquals(store.getId(), retrievedStore.get().getId());
    }
}
