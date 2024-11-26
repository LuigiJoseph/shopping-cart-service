package org.luigijoseph.shoppingcartservice.domain.services;

import org.luigijoseph.shoppingcartservice.domain.entities.Product;

import java.util.List;

public interface StoreInterface {
    Product searchProductById(Long productId);
    List<Product> searchProductsByName(String keyword);
}
