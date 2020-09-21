package com.lisanbian.service;

import com.lisanbian.pojo.Cart;
import com.lisanbian.pojo.Order;
import com.lisanbian.pojo.OrderItem;

import java.util.List;

public interface OrderService {
        String createOrder(Cart cart, Integer userId);
        List<Order> showAllOrders();
        void sendOrder(String orderId);
        List<OrderItem> showOrderDetail(String orderId);
        List<Order> showMyOrders(Integer userId);

        /**
         * 订单状态，0为待发货，1为已发货，2为已签收
         */
        void receiveOrder(String orderId);
    }

