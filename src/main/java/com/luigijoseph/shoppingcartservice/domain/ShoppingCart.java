package com.luigijoseph.shoppingcartservice.domain;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<CartItem> items;
    private double totalPrice;

    public ShoppingCart() {}

    public void emptyCart(){
        items = new ArrayList<>();
    }

    public void deleteItem(CartItem item){
        items.remove(item);
    }

    public void updateQuantity(CartItem item, int quantity){
        item.setQuantity(quantity);
    }

    public void addItem(CartItem item){
        items.add(item);
    }

    public ArrayList<CartItem> getItems(){
        return items;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void payCart(CartItem item){

    }
}
