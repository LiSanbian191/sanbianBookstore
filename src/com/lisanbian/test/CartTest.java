package com.lisanbian.test;

import com.lisanbian.pojo.Cart;
import com.lisanbian.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(99.99)));
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(99.99)));
        cart.addItem(new CartItem(2,"计算机组成原理",2,new BigDecimal(99.99)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(99.99)));
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(99.99)));
        cart.addItem(new CartItem(2,"计算机组成原理",2,new BigDecimal(99.99)));

        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(99.99)));
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(99.99)));
        cart.addItem(new CartItem(2,"计算机组成原理",2,new BigDecimal(99.99)));

        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println(cart);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(99.99)));
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(99.99)));
        cart.addItem(new CartItem(2,"计算机组成原理",2,new BigDecimal(99.99)));

        System.out.println(cart);
        cart.deleteItem(1);
        System.out.println(cart);
        cart.updateItem(2,4);
        System.out.println(cart);

    }
}