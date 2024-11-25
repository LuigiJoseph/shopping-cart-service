package org.luigijoseph.shoppingcartservice.infrastructure.persistence;

import org.luigijoseph.shoppingcartservice.domain.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
