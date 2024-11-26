package org.luigijoseph.shoppingcartservice.domain.repository;

import org.luigijoseph.shoppingcartservice.domain.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long> {

    }
