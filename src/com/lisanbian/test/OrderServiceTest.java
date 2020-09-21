package com.lisanbian.test;

import com.lisanbian.pojo.Cart;
import com.lisanbian.pojo.CartItem;
import com.lisanbian.service.OrderService;
import com.lisanbian.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(100)));
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(100)));
        cart.addItem(new CartItem(2,"计算机组成原理",2,new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();
        String orderid = orderService.createOrder(cart, 2);

        //测试成功


    }

    @Test
    public void showAllOrders() {
    }

    @Test
    public void sendOrder() {
    }

    @Test
    public void showOrderDetail() {
    }

    @Test
    public void showMyOrders() {
    }

    @Test
    public void receiveOrder() {
    }
}