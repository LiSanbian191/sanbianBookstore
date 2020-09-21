package com.lisanbian.dao;

import com.lisanbian.pojo.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 保存订单
     */
    int saveOrder(Order order);

    /**
     * 查询全部订单
     */
    List<Order> queryOders();

    /**
     *修改订单状态，1表示发货，0表示未发货
     */
    int changeOrderStatus(String orderId,int status);

    /**
     * 根据用户信息查询订单，即查询用户个人订单信息
     */
    List<Order> queryOrdersByUserId(Integer userId);
}
