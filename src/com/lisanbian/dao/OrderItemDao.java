package com.lisanbian.dao;

import com.lisanbian.pojo.Order;
import com.lisanbian.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    /**
     * 保存订单
     */
    int saveOderItem(OrderItem orderItem);

    /**
     *根据订单号查询订单详细信息
     */
    List<OrderItem> queryOrderItemByOrderId(String order_id);
}
