package org.luigijoseph.shoppingcartservice.domain.repository;

import org.luigijoseph.shoppingcartservice.domain.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
