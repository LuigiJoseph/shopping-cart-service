package org.luigijoseph.shoppingcartservice.domain.repository;

import org.luigijoseph.shoppingcartservice.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
